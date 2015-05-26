package com.martinstofanak.simplerxapp.android.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.martinstofanak.simplerxapp.android.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Add header comment
 */
public class TodayImageStatView extends LinearLayout {

    @InjectView(R.id.today_stat_view_icon) ImageView mIconView;
    @InjectView(R.id.today_stat_view_text) TextView mTextView;

    private int mIconResId;
    private String mTextValue;


    public TodayImageStatView(Context context) {
        this(context, null);
    }


    public TodayImageStatView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.view_today_weather_stat, this, true);
        ButterKnife.inject(this, view);

        if (attrs == null)
            return;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TodayImageStatView);
        mIconResId = a.getResourceId(R.styleable.TodayImageStatView_iconResId, 0);
        mTextValue = a.getString(R.styleable.TodayImageStatView_textValue);
        a.recycle();

        setDefaultValues(mIconResId, mTextValue);
    }


    public void bindView(int iconResId, String text) {
        mIconView.setImageResource(iconResId);
        mTextView.setText(text);
    }


    public void bindView(String text) {
        mIconView.setImageResource(mIconResId);
        mTextView.setText(text);
    }


    private void setDefaultValues(int iconResId, String text) {
        if (mIconResId != 0)
            mIconView.setImageResource(mIconResId);
        if (mTextValue != null)
            mTextView.setText(mTextValue);
    }
}
