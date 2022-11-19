package com.example.tocotoco.home.activityhome

import android.view.View
import android.view.View.OnApplyWindowInsetsListener
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseActivity
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.ActivityHomeBinding
import com.example.tocotoco.home.cartfragment.CartFragment
import com.example.tocotoco.home.favoritefragment.FavoriteFragment
import com.example.tocotoco.home.homefragment.HomeFragment
import com.example.tocotoco.home.notificationfragment.NotificationFragment

class HomeActivity : BaseActivity(R.layout.activity_home) {
    override val binding by viewBinding<ActivityHomeBinding>()
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")


    override fun setupViews() {
        replaceFragment(HomeFragment())
        setupBottomNav()
    }

    private fun setupBottomNav() = binding.run {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.favorite -> replaceFragment(FavoriteFragment())
                R.id.cart -> replaceFragment(CartFragment())
                R.id.notification -> replaceFragment(NotificationFragment())
                else -> {}
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) = binding.run {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }


    override fun bindViewModel() {
        //No TODO here
    }

}