package com.vha.qwer.vm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.vha.qwer.MyApp;

public class VMFactory {

    private ViewModelProvider mFragmentProvider;

    private ViewModelProvider mActivityProvider;

    private static ViewModelProvider mAppProvider;

    public <T extends ViewModel> T getFragmentViewModel(@NonNull Fragment fragment, @NonNull Class<T> model) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(fragment);
        }
        return mFragmentProvider.get(model);
    }

    public <T extends ViewModel> T getActivityViewModel(@NonNull AppCompatActivity activity, @NonNull Class<T> model) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(activity);
        }
        return mActivityProvider.get(model);
    }

    public static <T extends ViewModel> T getAppViewModel(@NonNull Class<T> model) {
        if (mAppProvider == null) {
            mAppProvider = new ViewModelProvider(MyApp.getViewModelOwner());
        }
        return mAppProvider.get(model);
    }
}
