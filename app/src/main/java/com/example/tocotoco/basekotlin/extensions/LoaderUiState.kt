package com.example.tocotoco.basekotlin.extensions

import kotlin.contracts.ExperimentalContracts

/**
 * Ui state that exposed to view.
 */
@OptIn(ExperimentalContracts::class)
data class LoaderUiState<out Content : Any>(
  /**
   * Is fetching in-progress
   */
  val isLoading: Boolean,
  /**
   * Is refreshing in-progress
   */
  val isRefreshing: Boolean,
  /**
   * Content (should be a `data class` or `value class`, or should implement [Any.equals] and [Any.hashCode])
   */
  val content: Content?,
) {
  /**
   * Returns `true` if [content] is not `null`
   */
  inline val hasContent: Boolean get() = content != null

  companion object Factory {
    /**
     * Initial ui state with [content], [isLoading] is true, [error] is null, [isRefreshing] is false.
     */
    fun <Content : Any> initial(content: Content?): LoaderUiState<Content> = LoaderUiState(
      content = content,
      isLoading = true,
      isRefreshing = false,
    )
  }
}
