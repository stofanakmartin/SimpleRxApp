package com.martinstofanak.simplerxapp.android;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import timber.log.Timber;

/**
 * Add header comment
 */
public class SimpleRxApp extends Application {

    private ObjectGraph mObjectGraph;


    public static SimpleRxApp get(Context context) {
        return (SimpleRxApp) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mObjectGraph = ObjectGraph.create(getModules(this));
        mObjectGraph.inject(this);
    }


    public ObjectGraph getApplicationGraph() {
        return mObjectGraph;
    }


    private Object[] getModules(Application app) {
        return new Object[]{
                new SimpleRxModule(app)
        };
    }
}
