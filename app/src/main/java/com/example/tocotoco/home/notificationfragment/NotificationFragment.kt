package com.example.tocotoco.home.notificationfragment

import android.content.Intent
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentNotificationBinding
import com.example.tocotoco.feature.product_detail.ProductDetailActivity

class NotificationFragment : BaseFragment(R.layout.fragment_notification){
    override val binding: FragmentNotificationBinding by viewBinding()

    private val promoteAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NotificationAdapter(onItemClick = {
            val intent = Intent(requireActivity(), ProductDetailActivity::class.java)
            intent.putExtra("idProduct", it)
            startActivity(intent)
        })
    }

    private val newsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        NotificationAdapter(onItemClick = {
            val intent = Intent(requireActivity(), ProductDetailActivity::class.java)
            intent.putExtra("idProduct", it)
            startActivity(intent)
        })
    }
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.run {
        rvKhuyenMai.adapter = promoteAdapter
        rvTintuc.adapter = newsAdapter
    }

    override fun bindViewModel() {
        //No TODO here
    }

}