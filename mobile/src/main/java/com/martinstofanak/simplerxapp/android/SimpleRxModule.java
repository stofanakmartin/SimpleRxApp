package com.martinstofanak.simplerxapp.android;

import android.app.Application;

import com.martinstofanak.simplerxapp.android.data.DataModule;
import com.martinstofanak.simplerxapp.android.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Add header comment
 */
@Module(
        includes = {
                UiModule.class,
                DataModule.class
        },
        injects = {
                SimpleRxApp.class
        }
)
public class SimpleRxModule {

    private final Application mApp;


    public SimpleRxModule(Application app) {
        mApp = app;
    }


    @Provides @Singleton Application provideApplication() {
        return mApp;
    }
}
