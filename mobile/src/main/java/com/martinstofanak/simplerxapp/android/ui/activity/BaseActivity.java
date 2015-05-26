package com.martinstofanak.simplerxapp.android.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.martinstofanak.simplerxapp.android.SimpleRxApp;
import com.martinstofanak.simplerxapp.android.ui.ActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Add header comment
 */
public class BaseActivity extends AppCompatActivity {

    private ObjectGraph mActivityGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleRxApp app = SimpleRxApp.get(this);
        mActivityGraph = app.getApplicationGraph().plus(getModules().toArray());
        mActivityGraph.inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityGraph = null;
    }


    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }


    public void inject(Object object) {
        mActivityGraph.inject(object);
    }
}
