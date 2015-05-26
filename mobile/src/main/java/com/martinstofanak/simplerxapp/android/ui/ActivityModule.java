package com.martinstofanak.simplerxapp.android.ui;

import android.content.Context;

import com.martinstofanak.simplerxapp.android.ui.activity.BaseActivity;
import com.martinstofanak.simplerxapp.android.ui.activity.MainActivity;
import com.martinstofanak.simplerxapp.android.ui.fragment.WeatherFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Add header comment
 */
@Module(
        injects = {
                MainActivity.class,
                WeatherFragment.class
        },
        addsTo = UiModule.class,
        complete = false,
        library = true
)
public class ActivityModule {

    private final BaseActivity mBaseActivity;


    public ActivityModule(BaseActivity baseActivity) {
        mBaseActivity = baseActivity;
    }


    @Provides
    public Context providesContext() {
        return mBaseActivity;
    }
}
