package com.example.tocotoco.home.homefragment.productfragment

import androidx.core.os.bundleOf
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentListProductBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.home.activityhome.SpacesItemDecoration
import com.example.tocotoco.model.CategoriesResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class ListProductFragment : BaseFragment(R.layout.fragment_list_product) {

    private var idCategory: String = ""

    override val binding: FragmentListProductBinding by viewBinding()
    override val viewModel: BaseViewModel
        get() = TODO()


    override fun setupViews() {
        getProductList(idCategory.toInt())
        setupRecyclerView()
    }

    private fun getProductList(id : Int) {
        DialogUtils.showProgressDialog(requireActivity())
        if (NetworkUtils.isConnect(requireActivity())) {
            NetWorkController.getListCategories(object : TCCCallback<CategoriesResult>() {
                override fun onViettelSuccess(
                    call: Call<CategoriesResult>?,
                    response: Response<CategoriesResult>?
                ) {

                }

                override fun onViettelFailure(call: Call<CategoriesResult>?) {
                    Timber.tag(call.toString())
                    DialogUtils.dismissProgressDialog()
                }

            },id)
        }
    }

    override fun bindViewModel() {
        //No TODO here
    }

    private fun setupRecyclerView() = binding.run {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.dimen_16dp)
        rvProduct.addItemDecoration(SpacesItemDecoration(spacingInPixels))

    }


    companion object {
        @JvmStatic
        fun newInstance(category: String) = ListProductFragment().apply {
            arguments = bundleOf(
                idCategory to category,
            )
        }
    }
}