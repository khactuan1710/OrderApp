package com.example.tocotoco.home.orderfragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentOrderListBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.login.LoginActivity
import com.example.tocotoco.feature.product_detail.ProductDetailActivity
import com.example.tocotoco.model.UserOrderResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class OrderListFragment : BaseFragment(R.layout.fragment_order_list) {
    override val binding: FragmentOrderListBinding by viewBinding()

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    private val sharedPref by lazy {
        requireContext().getSharedPreferences(
            requireContext().getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
    }

    private val listOrderAdapter by lazy(LazyThreadSafetyMode.NONE) {
        OrderListAdapter(onItemClick = {
            val intent = Intent(requireActivity(), ProductDetailActivity::class.java)
            intent.putExtra("idProduct", it)
            startActivity(intent)
        })
    }

    override fun setupViews() {
        setupRecyclerView()
        getOrderList()
        setupListener()
    }

    private fun setupListener() = binding.run {
        btnLogin.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.putExtra("fromFavorite", true)
            startActivity(intent)
        }
    }

    private fun getOrderList() = binding.run {
        binding.root.isVisible = false
        val keyToken =
            sharedPref?.getString(requireContext().getString(R.string.preference_key_token), "")
        if (keyToken.isNullOrEmpty()) {
            layoutProduct.isVisible = false
            layoutNullProduct.isVisible = true
            btnLogin.isVisible = true
            tvDesc.text = getString(R.string.fragment_favorite_null_account)
            binding.root.isVisible = true
        } else {

            DialogUtils.showProgressDialog(requireActivity())
            if (NetworkUtils.isConnect(requireActivity())) {
                NetWorkController.getUserOrder(object :
                    TCCCallback<UserOrderResult>() {
                    @SuppressLint("SetTextI18n")
                    override fun onTCTCSuccess(
                        call: Call<UserOrderResult>?,
                        response: Response<UserOrderResult>?
                    ) {
                        if (!response?.body()?.results.isNullOrEmpty()) {
                            listOrderAdapter.submitList(response?.body()?.results)
                            layoutNullProduct.isVisible = false
                            layoutProduct.isVisible = true
                        } else {
                            layoutProduct.isVisible = false
                            layoutNullProduct.isVisible = true
                            tvDesc.text = "Không có danh sách sản phẩm yêu thích"
                            btnLogin.isVisible = false
                        }
                        DialogUtils.dismissProgressDialog()
                        binding.root.isVisible = true
                    }

                    override fun onTCTCFailure(call: Call<UserOrderResult>?) {
                        Timber.tag(call.toString())
                        DialogUtils.dismissProgressDialog()
                    }
                }, keyToken)
                //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTIsInVzZXJuYW1lIjoiZGlldW5uMjAxMiIsInBhc3N3b3JkIjoiNWY0ZGNjM2I1YWE3NjVkNjFkODMyN2RlYjg4MmNmOTkiLCJpYXQiOjE2Njg1NjQ2Mzh9.okSWemdE9V38FgsZpTEw4YrpRjjdqnqi4N949p57ZYU
            }
        }
    }

    private fun setupRecyclerView() = binding.rvProductFavorite.run {
        adapter = listOrderAdapter
    }


    override fun bindViewModel() {
        //No TODO here
    }

}