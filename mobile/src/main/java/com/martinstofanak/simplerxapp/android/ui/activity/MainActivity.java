package com.martinstofanak.simplerxapp.android.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.martinstofanak.simplerxapp.android.R;
import com.martinstofanak.simplerxapp.android.ui.fragment.WeatherFragment;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Add header comment
 */
public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_PLAY_SERVICES = 1000;
    private Bundle mSavedInstanceState;
    private Dialog mPlayServicesErrorDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);
        ButterKnife.inject(this);

        mSavedInstanceState = savedInstanceState;

        if (checkPlayServices()) {
            renderView(savedInstanceState);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayServicesErrorDialog != null)
            mPlayServicesErrorDialog.dismiss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_PLAY_SERVICES:
                if (resultCode == RESULT_OK)
                    renderView(mSavedInstanceState);
                else
                    checkPlayServices();
                break;
        }
    }


    private void renderView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            inflateTodayFragment();
        }
    }


    private boolean checkPlayServices() {
        final int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            Timber.d("Google play services not available");
            mPlayServicesErrorDialog = GooglePlayServicesUtil
                    .getErrorDialog(result, this, REQUEST_CODE_PLAY_SERVICES);
            mPlayServicesErrorDialog.setCancelable(false);
            mPlayServicesErrorDialog.setCanceledOnTouchOutside(false);
            mPlayServicesErrorDialog.show();
            return false;
        }
        return true;
    }


    private void inflateFragment(int id, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(id, fragment, tag).commit();
    }


    private void inflateTodayFragment() {
        WeatherFragment fragment = WeatherFragment.newInstance();
        inflateFragment(R.id.container, fragment, WeatherFragment.TAG);
    }


}
