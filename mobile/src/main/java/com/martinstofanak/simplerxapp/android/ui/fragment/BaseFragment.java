package com.martinstofanak.simplerxapp.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.martinstofanak.simplerxapp.android.ui.activity.BaseActivity;

/**
 * Add header comment
 */
public class BaseFragment extends Fragment {


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((BaseActivity) getActivity()).inject(this);
    }
}
