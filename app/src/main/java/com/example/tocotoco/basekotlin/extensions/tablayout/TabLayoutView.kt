package com.example.tocotoco.basekotlin.extensions.tablayout

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.tocotoco.R
import timber.log.Timber

class TabLayoutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : HorizontalScrollView(context, attrs, defStyle) {

    private lateinit var tabStrip: LinearLayout
    private var selectedTab = 0
    private lateinit var tabSelectedListener: (Int) -> Unit

    init {
        addTabStrip(context)
    }

    /**
     * Adds LinearLayout to the parent view & configures LayoutParams.
     * @param context using to initialize LinearLayout.
     */
    private fun addTabStrip(context: Context) {
        tabStrip = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            ).apply {
                gravity = Gravity.CENTER
            }
        }

        addView(tabStrip)
    }

    fun setData(pageTitleList: List<String>) {
        if (this::tabStrip.isInitialized) {
            tabStrip.removeAllViews()
            selectedTab = 0
        }
        pageTitleList.forEachIndexed { index, pageTitle ->
            TextView(context).apply {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 15.0f)
//        val typeface = ResourcesCompat.getFont(context, R.font.noto_san_jp)
                setTypeface(typeface, Typeface.BOLD)
                text = pageTitle
                layoutParams =
                    LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                if (index == selectedTab) {
                    setTextColor(Color.WHITE)
                    setBackgroundResource(R.drawable.bg_tab_layout)
                } else {
                    setTextColor(Color.BLACK)
                    setBackgroundResource(R.drawable.bg_tab_layout)
                }

                (layoutParams as MarginLayoutParams).apply {
                    leftMargin =
                        if (index == 0) DimensUtils.dpToPx(
                            context,
                            TAB_FIRST_LAST_MARGIN
                        ) else DimensUtils.dpToPx(context, TAB_MARGIN)
                    rightMargin =
                        if (index == pageTitleList.size - 1) DimensUtils.dpToPx(
                            context,
                            TAB_FIRST_LAST_MARGIN
                        ) else DimensUtils.dpToPx(context, TAB_MARGIN)
                    setPadding(32, 16, 32, 16)
                }

                setOnClickListener {
                    selectedTab = index
                    tabSelectedListener.invoke(index)
                    updateUITab()
                    scrollTabView(index)
                }
            }.run {
                tabStrip.addView(this)
            }
        }
    }

    private fun updateUITab() {
        tabStrip.children.forEachIndexed { index, view ->
            (view as TextView).run {
                if (index == selectedTab) {
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                    setBackgroundResource(R.drawable.bg_tab_layout)
                } else {
                    setTextColor(ContextCompat.getColor(context, R.color.brown))
                    setBackgroundResource(R.drawable.bg_tab_layout_unselect)
                }
            }
        }
    }

    private fun scrollTabView(position: Int) {
        Timber.d("TabLayoutView scroll to: $position")
        var previousPosition = 0
        var nextPosition = 0

        if (position == tabStrip.childCount - 1) {
            smoothScrollTo(right, 0)
        }

        if (position - 1 >= 0) {
            for (i in 0 until (position - 1)) {
                previousPosition += tabStrip.getChildAt(i).width + DimensUtils.dpToPx(
                    context,
                    TAB_MARGIN * 2
                )
            }
        }

        if (position + 1 < tabStrip.childCount) {
            for (i in 0 until position + 1) {
                nextPosition += tabStrip.getChildAt(i).width + DimensUtils.dpToPx(
                    context,
                    TAB_MARGIN * 2
                )
            }
        }

        val currentPosition = (0 until position).sumOf {
            tabStrip.getChildAt(it).width + DimensUtils.dpToPx(context, TAB_MARGIN * 2)
        }

        Timber.d("TabLayoutView scrollX: $scrollX")
        Timber.d("TabLayoutView right: $right")
        Timber.d("TabLayoutView previousPosition: $previousPosition")
        Timber.d("TabLayoutView nextPosition: $nextPosition")
        Timber.d("TabLayoutView currentPosition: $currentPosition")

        when {
            previousPosition < scrollX -> {
                Timber.d("TabLayoutView case 1")
                scrollTo(previousPosition, 0)
            }
            currentPosition > right -> {
                Timber.d("TabLayoutView case 2")
                scrollTo(previousPosition, 0)
            }
            nextPosition > right -> {
                Timber.d("TabLayoutView case 3")
                scrollTo(currentPosition, 0)
            }
        }
        Timber.d("TabLayoutView -------------------------")
    }

    fun addTabSelectedListener(listener: (Int) -> Unit) {
        tabSelectedListener = listener
    }

    fun onPagerSelectedAt(index: Int) {
        selectedTab = index
//    tabSelectedListener.invoke(index)
        updateUITab()
        scrollTabView(index)
    }

    private companion object {
        const val TAB_MARGIN = 4
        const val TAB_FIRST_LAST_MARGIN = 32
    }
}
