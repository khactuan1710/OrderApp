package com.example.tocotoco.home.notificationfragment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.core.view.isVisible
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentNotificationBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.product_detail.ProductDetailActivity
import com.example.tocotoco.model.NotifiResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

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
        getListNotification()
    }


    private fun getListNotification() {
        DialogUtils.showProgressDialog(requireActivity())
        if (NetworkUtils.isConnect(requireActivity())) {
            NetWorkController.getPromoteNotifications(object :
                TCCCallback<NotifiResult>() {
                @SuppressLint("SetTextI18n")
                override fun onTCTCSuccess(
                    call: Call<NotifiResult>?,
                    response: Response<NotifiResult>?
                ) {
                    if (response?.body()?.result != null) {
                        promoteAdapter.submitList(response.body()?.result)
                    }
                }

                override fun onTCTCFailure(call: Call<NotifiResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }
            })
            NetWorkController.getNewsNotifications(object :
                TCCCallback<NotifiResult>() {
                @SuppressLint("SetTextI18n")
                override fun onTCTCSuccess(
                    call: Call<NotifiResult>?,
                    response: Response<NotifiResult>?
                ) {
                    if (response?.body()?.result != null) {
                        newsAdapter.submitList(response.body()?.result)
                    }

                }

                override fun onTCTCFailure(call: Call<NotifiResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }
            })
            DialogUtils.dismissProgressDialog()
            binding.root.isVisible = true
        }
    }


    private fun setupRecyclerView() = binding.run {
        rvKhuyenMai.adapter = promoteAdapter
        rvTintuc.adapter = newsAdapter
    }

    override fun bindViewModel() {
        //No TODO here
    }

}