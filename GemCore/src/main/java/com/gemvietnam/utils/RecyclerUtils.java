package com.gemvietnam.utils;


import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

/**
 * Collection Utils
 * Created by neo on 7/20/2016.
 */
public class RecyclerUtils {

  private RecyclerUtils() {

  }

  public static void setupVerticalRecyclerView(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    if (mRecyclerView != null) {
      mRecyclerView.setLayoutManager(layoutManager);
      mRecyclerView.setClipToPadding(false);
      mRecyclerView.setHasFixedSize(false);
      //batv add
      mRecyclerView.setNestedScrollingEnabled(false);
      mRecyclerView.scrollTo(0, 0);
    }
  }

  public static void setupHorizontalRecyclerView(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context,
        LinearLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }
  public static void setMaxHeight(final RecyclerView view, final int maxItem) {
    setMaxHeight(view, 1, maxItem);
  }
  public static void setMaxHeight(final RecyclerView view, final int minItem, final int maxItem) {
    view.post(new Runnable() {
      @Override
      public void run() {
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if (layoutManager.getItemCount() <= minItem) {
          return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutManager.getHeight() * maxItem;
        view.setLayoutParams(layoutParams);
      }
    });
  }
  public static void setupStaggeredVerticalRecyclerView(RecyclerView mRecyclerView, int spanCount) {
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  /**
   * Common Setup for grid recycler view
   */
  public static void setupGridRecyclerView(Context context, RecyclerView mRecyclerView, int spanCount) {
    LinearLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupGrid(Context context, RecyclerView mRecyclerView, int spanCount) {
//    LinearLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
    GridLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
    layoutManager.setAutoMeasureEnabled(true);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupGridHorizontalRecyclerView(Context context, RecyclerView mRecyclerView, int spanCount) {
    LinearLayoutManager layoutManager = new GridLayoutManager(context, spanCount, LinearLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }
}
