package com.example.tocotoco.home.favoritefragment

import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseFragment
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    override val binding: FragmentFavoriteBinding by viewBinding()
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")

    private val listProductAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ListProductFavoriteAdapter()
    }

    override fun setupViews() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.rvProductFavorite.run {
        adapter = listProductAdapter
    }

    override fun bindViewModel() {
        //No TODO here
    }
}