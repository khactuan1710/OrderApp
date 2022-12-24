package com.example.tocotoco.home.search

import android.content.Intent
import androidx.core.widget.doOnTextChanged
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseActivity
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentSearchBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.product_detail.ProductDetailActivity
import com.example.tocotoco.model.ProductsResult
import com.example.tocotoco.model.ProductsResult.ProductsResultModel
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class SearchActivity : BaseActivity(R.layout.fragment_search) {
    override val binding: FragmentSearchBinding by viewBinding()
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    private val listProductAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchProductAdapter(onItemClick = {
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("idProduct", it)
            startActivity(intent)
        })
    }

    var list: List<ProductsResultModel>? = null

    override fun setupViews() {
        setupRecyclerView()
        getProductList()
        binding.edtSearch.doOnTextChanged { text, _, _, _ ->
            searchProduct(text.toString())
            if (text.isNullOrEmpty()){
                binding.rvSearch.scrollToPosition(0)
            }
        }
        binding.icBack.setOnClickListener {
            finish()
        }
    }

    private fun searchProduct(name: String) {
        if (!list.isNullOrEmpty()) {
            listProductAdapter.submitList(list!!.filter {
                it.productName.contains(name, ignoreCase = true)
            })
        }
    }

    private fun getProductList() {
        DialogUtils.showProgressDialog(this)
        if (NetworkUtils.isConnect(this)) {
            NetWorkController.getListProduct(object :
                TCCCallback<ProductsResult>() {
                override fun onTCTCSuccess(
                    call: Call<ProductsResult>?,
                    response: Response<ProductsResult>?
                ) {
                    list = response?.body()?.results
                    DialogUtils.dismissProgressDialog()
                }

                override fun onTCTCFailure(call: Call<ProductsResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }
            })
        }
    }

    private fun setupRecyclerView() = binding.rvSearch.run {
        adapter = listProductAdapter
    }

    override fun bindViewModel() {
        //No TODO here
    }

}