package com.example.tocotoco.basekotlin.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity(@LayoutRes layoutRes: Int) : AppCompatActivity(layoutRes) {
  protected abstract val binding: ViewBinding
  protected abstract val viewModel: BaseViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupViews()
    bindViewModel()
  }

  // ---------------------------------- SETUP VIEWS ----------------------------------

  @MainThread
  protected abstract fun setupViews()

  // ---------------------------------- BIND VIEW MODEL ----------------------------------

  @MainThread
  protected abstract fun bindViewModel()
}
