package com.example.tocotoco.home.favoritefragment

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentFavoriteBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.login.LoginActivity
import com.example.tocotoco.model.FavoriteProductsResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    override val binding: FragmentFavoriteBinding by viewBinding()
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    private val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
    val keyToken = sharedPref?.getString(getString(R.string.preference_key_token), "")

    private val listProductAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ListProductFavoriteAdapter()
    }

    override fun setupViews() {
        setupRecyclerView()
        getFavoriteProductList()
        setupListener()
    }

    private fun setupListener() = binding.run {
        btnLogin.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.putExtra("fromFavorite", true)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment) = binding.run {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun getFavoriteProductList() {
        DialogUtils.showProgressDialog(requireActivity())
        if (NetworkUtils.isConnect(requireActivity())) {
            NetWorkController.getListProductByFavorite(object :
                TCCCallback<FavoriteProductsResult>() {
                override fun onViettelSuccess(
                    call: Call<FavoriteProductsResult>?,
                    response: Response<FavoriteProductsResult>?
                ) {
                    if (!response?.body()?.results.isNullOrEmpty()){
                        listProductAdapter.submitList(response?.body()?.results)
                        binding.layoutNullProduct.isVisible = false
                        binding.layoutProduct.isVisible = true
                    } else {
                        binding.layoutProduct.isVisible = true
                        binding.layoutNullProduct.isVisible = true
                    }
                    DialogUtils.dismissProgressDialog()
                }

                override fun onViettelFailure(call: Call<FavoriteProductsResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }
            }, keyToken)
        }
    }

    private fun setupRecyclerView() = binding.rvProductFavorite.run {
        adapter = listProductAdapter
    }

    override fun bindViewModel() {
        //No TODO here
    }
}