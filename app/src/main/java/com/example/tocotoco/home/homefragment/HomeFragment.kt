package com.example.tocotoco.home.homefragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentHomeBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.account.AccountActivity
import com.example.tocotoco.home.search.SearchActivity
import com.example.tocotoco.model.CategoriesResult
import com.example.tocotoco.model.UserInfoResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.util.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val binding by viewBinding<FragmentHomeBinding>()

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    private var firstPagePosition = 0

    private val sharedPref by lazy {
        requireContext().getSharedPreferences(
            requireContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
    }

    private val token by lazy {
        sharedPref?.getString(requireContext().getString(R.string.preference_key_token), "")
    }

    private val editor by lazy {
        sharedPref.edit()
    }

    override fun setupViews() {
        requestPermissionLocation()
    }

    override fun onResume() {
        super.onResume()
        getUserInfo()
        getLocation()
        setupClickListener()
        getCategoryList()
        binding.linearLayoutCompat.setOnClickListener {
            startActivity(Intent(requireContext(),SearchActivity::class.java))
        }
    }

    private var viewPagerAdapter: ViewPagerAdapter? = null

    private fun getCategoryList() {
        DialogUtils.showProgressDialog(requireActivity())
        if (NetworkUtils.isConnect(requireActivity())) {
            NetWorkController.getListCategories(object : TCCCallback<CategoriesResult>() {
                override fun onTCTCSuccess(
                    call: Call<CategoriesResult>?,
                    response: Response<CategoriesResult>?
                ) {
                    setupTabLayoutWithViewPager(response?.body()?.result)
                    DialogUtils.dismissProgressDialog()
                    binding.root.isVisible = true
                }

                override fun onTCTCFailure(call: Call<CategoriesResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }

            })
        }
    }

    private fun setupClickListener() = binding.run {
        imgAccount.setOnClickListener {
            startActivity(Intent(requireActivity(), AccountActivity::class.java))
        }
    }

    private fun getUserInfo() = binding.run {
        if (NetworkUtils.isConnect(requireActivity())) {
            NetWorkController.getUserInfo(object : TCCCallback<UserInfoResult>() {
                override fun onTCTCSuccess(
                    call: Call<UserInfoResult>?,
                    response: Response<UserInfoResult>?
                ) {
                    if (response?.body()?.results?.name != null) {
                        imgAccount.isVisible = false
                        imgName.text = response.body()!!.results.name.substring(0, 1)
                            .uppercase(Locale.getDefault())
                        imgName.isVisible = true
                    } else {
                        imgAccount.isVisible = true
                        imgName.isVisible = false
                    }
                }

                override fun onTCTCFailure(call: Call<UserInfoResult>?) {
                    imgAccount.isVisible = true
                    imgName.isVisible = false
                }

            }, token)
        }
    }

    private fun setupTabLayoutWithViewPager(list: List<CategoriesResult.CategoriesResultModel>?) {
        list?.let {
            binding.tabLayout.setData(list.map { it.name })
            viewPagerAdapter = ViewPagerAdapter(this, list)
            viewPagerAdapter?.let {
                if (list.isNotEmpty()) {
                    binding.viewPager.offscreenPageLimit = list.size
                }
                binding.viewPager.adapter = it
            }
            binding.tabLayout.post {
                if (isAdded) {
                    if (firstPagePosition == 0) {
                        binding.tabLayout.onPagerSelectedAt(firstPagePosition)
                        binding.viewPager.currentItem = firstPagePosition
                    } else {
                        binding.tabLayout.onPagerSelectedAt(binding.viewPager.currentItem)
                    }
                    binding.tabLayout.addTabSelectedListener {
                        binding.viewPager.setCurrentItem(it, false)
                    }
                }
            }
            val callback = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    binding.tabLayout.onPagerSelectedAt(position)
                }

                override fun onPageSelected(position: Int) {
                    binding.tabLayout.onPagerSelectedAt(position)
                    firstPagePosition = position
                }
            }
            binding.viewPager.registerOnPageChangeCallback(callback)
            binding.viewPager.isVisible = true
            binding.tabLayout.isVisible = true
        } ?: kotlin.run {
        }
    }


    private fun getLocation() = binding.run {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLocation()
            return@run
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (activity != null) {
                        val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                        if (location != null) {
                            val address = geocoder.getFromLocation(
                                location.latitude,
                                location.longitude,
                                1
                            )[0].getAddressLine(0)
                            binding.tvLocation.text = address
                            editor.putString(requireContext().getString(R.string.address), address)
                            editor.apply()
                        }
                    }
                }
        }
    }

    private fun requestPermissionLocation() = binding.run {

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Timber.tag("setupViews ACCESS_FINE_LOCATION: ")
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Timber.tag("setupViews ACCESS_COARSE_LOCATION: ")
                }
                else -> {
                    Timber.tag("setupViews no location: ")
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun bindViewModel() {
        //No TODO here
    }

}