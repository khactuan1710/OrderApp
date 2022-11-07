package com.example.tocotoco.home.homefragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentHomeBinding
import com.example.tocotoco.home.activityhome.SpacesItemDecoration
import com.example.tocotoco.home.homefragment.`object`.Category
import com.google.android.gms.location.LocationServices
import java.util.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val binding by viewBinding<FragmentHomeBinding>()

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun setupViews() {
        getLocation()
        setupRecyclerView()
        requestPermissionLocation()
    }

    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var firstPagePosition = 0

    private fun setupTabLayoutWithViewPager(list: List<Category>?, serialStoreId: Int) {
        list?.let {
            binding.tabLayout.setData(list.map { it.name.orEmpty() })
            viewPagerAdapter = ViewPagerAdapter(this, list, serialStoreId)
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
                        binding.viewPager.setCurrentItem(it, true)
                    }
                }
            }
            binding.viewPager.isVisible = true
            binding.tabLayout.isVisible = true
        } ?: kotlin.run {
        }
    }

    private fun setupRecyclerView() = binding.run {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.dimen_16dp)
        rvProduct.addItemDecoration(SpacesItemDecoration(spacingInPixels))
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
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                if (location != null) {
                    val address = geocoder.getFromLocation(
                        location.latitude,
                        location.longitude,
                        1
                    )[0].getAddressLine(0)
                    binding.tvLocation.text = address
                }
            }
    }

    private fun requestPermissionLocation() = binding.run {

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.e("TAG", "setupViews ACCESS_FINE_LOCATION: ")
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Log.e("TAG", "setupViews ACCESS_COARSE_LOCATION: ")
                }
                else -> {
                    Log.e("TAG", "setupViews no location: ")
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