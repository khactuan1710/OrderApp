package com.example.tocotoco.home.activityhome

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tocotoco.R
import com.example.tocotoco.basekotlin.base.BaseActivity
import com.example.tocotoco.basekotlin.base.BaseViewModel
import com.example.tocotoco.basekotlin.extensions.viewBinding
import com.example.tocotoco.databinding.ActivityHomeBinding
import com.example.tocotoco.dialog.DialogUtils
import com.example.tocotoco.feature.order.OrderActivity
import com.example.tocotoco.feature.orderStatus.OrderStatusActivity
import com.example.tocotoco.home.favoritefragment.FavoriteFragment
import com.example.tocotoco.home.homefragment.HomeFragment
import com.example.tocotoco.home.notificationfragment.NotificationFragment
import com.example.tocotoco.home.orderfragment.OrderListFragment
import com.example.tocotoco.model.CartInfoResult
import com.example.tocotoco.model.SessionIdResult
import com.example.tocotoco.model.UserCurrentResult
import com.example.tocotoco.network.NetWorkController
import com.example.tocotoco.network.TCCCallback
import com.example.tocotoco.util.NetworkUtils
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class HomeActivity : BaseActivity(R.layout.activity_home) {
    override val binding by viewBinding<ActivityHomeBinding>()
    override val viewModel: BaseViewModel
        get() = TODO("Not yet implemented")
    private var receiver: MyBroadcastReceiver? = null
    private val sharedPref by lazy {
        getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
    }

    private val token by lazy {
        sharedPref?.getString(getString(R.string.preference_key_token), "")
    }

    private val sessionId by lazy {
        sharedPref.getInt(getString(R.string.session_id), 0)
    }


    override fun setupViews() {
        setupBottomNav()
        getIntentId()
        getOrderIcon()

        receiver = MyBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.my.app.onMessageReceived")
        registerReceiver(receiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        getCartList()
        getOrderIcon()
        binding.imgCart.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("tokenToOrder", token)
            startActivity(intent)
        }
    }




    fun getCartList() = binding.run {
        DialogUtils.showProgressDialog(this@HomeActivity)
        var secId = 0;
        if (NetworkUtils.isConnect(this@HomeActivity)) {
            NetWorkController.getUserShoppingSession(object : TCCCallback<SessionIdResult>() {
                override fun onTCTCSuccess(
                    call: Call<SessionIdResult>?,
                    response: Response<SessionIdResult>?
                ) {
                    if(response?.body()?.isSuccess == true) {
                        secId = response?.body()?.result?.id!!
                        NetWorkController.getCartInfo(
                            object : TCCCallback<CartInfoResult>() {
                                override fun onTCTCSuccess(
                                    call: Call<CartInfoResult>?,
                                    response: Response<CartInfoResult>?
                                ) {
                                    val itemCart = response?.body()?.results?.totalCategory
                                    frameLayout.isVisible = !itemCart.equals("0", true) && itemCart != null
                                    imgCart.isVisible = !itemCart.equals("0", true)
                                    tvNumber.text = itemCart
                                    DialogUtils.dismissProgressDialog()
                                }

                                override fun onTCTCFailure(call: Call<CartInfoResult>?) {
                                    Timber.tag(call.toString())
                                    DialogUtils.dismissProgressDialog()
                                }
                            }, token, secId
                        )
                    }else {
                        NetWorkController.getCartInfo(
                            object : TCCCallback<CartInfoResult>() {
                                override fun onTCTCSuccess(
                                    call: Call<CartInfoResult>?,
                                    response: Response<CartInfoResult>?
                                ) {
                                    val itemCart = response?.body()?.results?.totalCategory
                                    frameLayout.isVisible = !itemCart.equals("0", true) && itemCart != null
                                    imgCart.isVisible = !itemCart.equals("0", true)
                                    tvNumber.text = itemCart
                                    DialogUtils.dismissProgressDialog()
                                }

                                override fun onTCTCFailure(call: Call<CartInfoResult>?) {
                                    Timber.tag(call.toString())
                                    DialogUtils.dismissProgressDialog()
                                }
                            }, token, sessionId
                        )
                    }

                }

                override fun onTCTCFailure(call: Call<SessionIdResult>?) {
                }
            }, token)

        }
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val extras = intent.extras
            val state = extras!!.getString("extra")
            getOrderIcon()
        }
    }
     private fun getOrderIcon() = binding.run {
        DialogUtils.showProgressDialog(this@HomeActivity)
        if (NetworkUtils.isConnect(this@HomeActivity)) {
            NetWorkController.getUserCurrentOrder(
                object : TCCCallback<UserCurrentResult>() {
                    override fun onTCTCSuccess(
                        call: Call<UserCurrentResult>?,
                        response: Response<UserCurrentResult>?
                    ) {
                        if (response != null) {
                            if(response?.body()?.isSuccess == true) {
                                frameLayout2?.isVisible = response.isSuccessful
                                frameLayout2?.setOnClickListener {
                                    val intent =
                                        Intent(this@HomeActivity, OrderStatusActivity::class.java)
                                    response.body()?.results.apply {
                                        intent.putExtra("orderId", this?.orderId)
                                        intent.putExtra("total", this?.total)
                                        intent.putExtra("paymentId", this?.paymentId)
                                        intent.putExtra("status", this?.status)
                                        intent.putExtra("provider", this?.provider)
                                        intent.putExtra("address", this?.address)
                                        intent.putExtra("phoneNumber", this?.phoneNumber)
                                    }
                                    startActivity(intent)
                                }
                            }else {
                                frameLayout2?.isVisible = false;
                            }

                        }
                        DialogUtils.dismissProgressDialog()
                    }

                    override fun onTCTCFailure(call: Call<UserCurrentResult>?) {
                        Timber.tag(call.toString())
                        DialogUtils.dismissProgressDialog()
                    }
                }, token
            )
        }
    }

    private fun getIntentId() = binding.run {
        val intent = intent
        intent?.let {
            if (it.getBooleanExtra("goToFavorite", false)) {
                bottomNav.selectedItemId = R.id.favorite
            } else if (it.getBooleanExtra("goToOrder", false)) {
                bottomNav.selectedItemId = R.id.cart
            } else {
                replaceFragment(HomeFragment())
            }
        }

    }

    private fun setupBottomNav() = binding.run {
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.favorite -> replaceFragment(FavoriteFragment())
                R.id.cart -> replaceFragment(OrderListFragment())
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