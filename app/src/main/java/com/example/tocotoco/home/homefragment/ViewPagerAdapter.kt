package com.example.tocotoco.home.homefragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tocotoco.home.homefragment.`object`.Category

internal class ViewPagerAdapter(
  fragment: Fragment,
  private val list: List<Category>,
  private val currentSerialStoreId: Int?
) : FragmentStateAdapter(
  fragment.childFragmentManager,
  fragment.viewLifecycleOwner.lifecycle
) {
  override fun getItemCount(): Int = list.size

  override fun createFragment(position: Int): Fragment

}
