package com.example.tocotoco.home.orderfragment

import android.content.Intent
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentOrderListBinding
import com.example.tocotoco.feature.product_detail.ProductDetailActivity
import com.example.tocotoco.home.favoritefragment.ListProductFavoriteAdapter

class OrderListFragment : BaseFragment(R.layout.fragment_order_list) {
    override val binding: FragmentOrderListBinding by viewBinding()

    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    private val listOrderAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ListProductFavoriteAdapter(onItemClick = {
            val intent = Intent(requireActivity(), ProductDetailActivity::class.java)
            intent.putExtra("idProduct", it)
            startActivity(intent)
        })
    }

    override fun setupViews() {
        TODO("Not yet implemented")
    }



    override fun bindViewModel() {
        //No TODO here
    }

}