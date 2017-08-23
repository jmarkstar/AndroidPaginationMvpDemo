package com.jmarkstar.carlist_core.domain.interactor.executor;

import android.os.Handler;
import android.os.Looper;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 22/08/2017.
 */
public class MainThreadImpl implements MainThread {

  private Handler mHandler;

  @Inject public MainThreadImpl() {
    this.mHandler = new Handler(Looper.getMainLooper());
  }

  @Override public void post(Runnable runnable) {
    mHandler.post(runnable);
  }
}