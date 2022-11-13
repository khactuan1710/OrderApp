package com.example.tocotoco.home.homefragment.productfragment

import androidx.core.os.bundleOf
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentListProductBinding

class ListProductFragment : BaseFragment(R.layout.fragment_list_product) {

    private lateinit var titleName: String

    override val binding: FragmentListProductBinding by viewBinding()
    override val viewModel: BaseViewModel
        get() = TODO()


    override fun setupViews() {
        binding.tv.text = titleName
    }

    override fun bindViewModel() {
        TODO()
    }


    companion object {
        @JvmStatic
        fun newInstance(category: String) = ListProductFragment().apply {
            arguments = bundleOf(
                titleName to category,
            )
        }
    }
}