package com.example.tocotoco.basekotlin.extensions

import android.app.Activity
import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

//
// Fragment
//



//
// Activity
//

/**
 * Create [ViewBinding] property delegate for this [Activity].
 * @param bind a lambda function that creates a [ViewBinding] instance from [Activity]'s contentView, eg: `T::bind` static method can be used.
 */
@Suppress("unused")
@MainThread
fun <T : ViewBinding> Activity.viewBinding(bind: (View) -> T): ActivityViewBindingDelegate<T> =
  ActivityViewBindingDelegate.from(viewBindingBind = bind)

/**
 * Create [ViewBinding] property delegate for this [Activity].
 */
@Suppress("unused")
@MainThread
inline fun <reified T : ViewBinding> Activity.viewBinding(): ActivityViewBindingDelegate<T> =
  ActivityViewBindingDelegate.from(viewBindingClazz = T::class.java)


@MainThread
inline fun <reified T : ViewBinding> Fragment.viewBinding(
  noinline onDestroyView: (T.() -> Unit)? = null
): FragmentViewBindingDelegate<T> = FragmentViewBindingDelegate.from(
  fragment = this,
  viewBindingClazz = T::class.java,
  onDestroyView = onDestroyView
)