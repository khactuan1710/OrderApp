package com.example.tocotoco.home.homefragment.productfragment

import android.content.Intent
import android.os.Bundle
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentListProductBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.product_detail.ProductDetailActivity
import com.example.tocotoco.model.ProductsByCategoryResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class ListProductFragment : BaseFragment(R.layout.fragment_list_product) {

    override val binding: FragmentListProductBinding by viewBinding()
    override val viewModel: BaseViewModel
        get() = TODO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getString(ID_CATEGORY)?.let { it1 -> getProductList(it1.toInt()) }
        }
    }

    private val listProductAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ListProductAdapter(onItemClick = {
            val intent = Intent(requireActivity(), ProductDetailActivity::class.java)
            intent.putExtra("idProduct", it)
            startActivity(intent)
        })
    }

    override fun setupViews() {
        setupRecyclerView()
    }


    private fun getProductList(id: Int) {
        DialogUtils.showProgressDialog(requireActivity())
        if (NetworkUtils.isConnect(requireActivity())) {
            NetWorkController.getListProductByCategory(object :
                TCCCallback<ProductsByCategoryResult>() {
                override fun onViettelSuccess(
                    call: Call<ProductsByCategoryResult>?,
                    response: Response<ProductsByCategoryResult>?
                ) {
                    listProductAdapter.submitList(response?.body()?.results)
                    DialogUtils.dismissProgressDialog()
                }

                override fun onViettelFailure(call: Call<ProductsByCategoryResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }
            }, id)
        }
    }

    override fun bindViewModel() {
        //No TODO here
    }

    private fun setupRecyclerView() = binding.rvProduct.run {
        adapter = listProductAdapter
    }


    companion object {
        private var ID_CATEGORY: String = ""

        @JvmStatic
        fun newInstance(category: String) = ListProductFragment().apply {
            arguments = Bundle().apply {
                putString(ID_CATEGORY, category)
            }
        }
    }
}