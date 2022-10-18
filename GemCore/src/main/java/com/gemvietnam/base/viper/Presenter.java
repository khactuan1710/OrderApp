package com.gemvietnam.base.viper;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.gemvietnam.base.viper.interfaces.ContainerView;
import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.IView;
import com.gemvietnam.eventbus.EventBusWrapper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Base implements for presenters
 * Created by neo on 14/03/2016.
 */
public abstract class Presenter<V extends IView, I extends IInteractor>
    implements IPresenter<V, I> {
  protected ContainerView mContainerView;
  protected V mView;
  protected I mInteractor;

  @SuppressWarnings("unchecked")
  public Presenter(ContainerView containerView) {
    mContainerView = containerView;
    mInteractor = onCreateInteractor();
    mView = onCreateView();

    mView.setPresenter(this);
  }

  public Presenter(ContainerView containerView, boolean standardized) {
    mContainerView = containerView;
    mInteractor = onCreateInteractor();
    mView = onCreateView(standardized);

    mView.setPresenter(this);
  }

  @SuppressWarnings("unchecked")
  protected Presenter() {
    mInteractor = onCreateInteractor();
    mView = onCreateView();

    mView.setPresenter(this);
  }

  public Activity getViewContext() {
    return mView.getViewContext();
  }

  @Override
  public V getView() {
    return mView;
  }

  @Override
  public I getInteractor() {
    return mInteractor;
  }

  @Override
  public Fragment getFragment() {
    return getView() instanceof Fragment ? (Fragment) getView() : null;
  }

  @Override
  public Fragment getFragment(Bundle bundle) {
    Fragment fragment;
    if (getView() instanceof Fragment) {
      fragment = (Fragment) getView();
      fragment.setArguments(bundle);
    } else {
      fragment = null;
    }
    return fragment;
  }

  @Override
  public void presentView() {
    mContainerView.presentView(mView);
  }

  @Override
  public void pushView() {
    mContainerView.pushView(mView);
  }

  @Override
  public void pushView(Bundle bundle) {
    mContainerView.pushView(mView, bundle);
  }

  @Override
  public void ngaPushView() {
    mContainerView.ngaView(mView);
  }

  @Override
  public void replaceView() {
    mContainerView.replaceView(mView);
  }

  @Override
  public void pushChildView(int frameId, FragmentManager childFragmentManager) {
    if (getFragment() != null) {
      mContainerView.pushChildView(mView, frameId, childFragmentManager);
    }
  }

  @Override
  public void loadChildView(int frameId, FragmentManager childFragmentManager) {
    if (getFragment() != null) {
      mContainerView.loadChildView(mView, frameId, childFragmentManager);
    }
  }

  public void addView() {
    mContainerView.addView(mView);
  }

  // Event bus

  @Override
  public void registerEventBus() {
    EventBusWrapper.register(this);
  }

  @Override
  public void unregisterEventBus() {
    EventBusWrapper.unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.BACKGROUND)
  public void onMessageEvent(NoneEvent event) {
    // Default event handler
  }


  public interface NoneEvent {
    // Default event
  }

  @Override
  public void back() {
    mView.getBaseActivity().hideKeyboard();
    mContainerView.back();
  }

  @Override
  public void onFragmentDisplay() {
  }

  @Override
  public boolean isViewShowing() {
    return ((ViewFragment) mView).isShowing();
  }

  public V onCreateView(boolean standardized){
    return onCreateView();
  }
}
