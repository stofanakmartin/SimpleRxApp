package com.martinstofanak.simplerxapp.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.martinstofanak.simplerxapp.android.R;
import com.martinstofanak.simplerxapp.android.data.api.ApiModule;
import com.martinstofanak.simplerxapp.android.data.api.model.AddressResult;
import com.martinstofanak.simplerxapp.android.data.api.model.WeatherItem;
import com.martinstofanak.simplerxapp.android.data.api.model.WeatherResponse;
import com.martinstofanak.simplerxapp.android.data.api.model.WeatherResult;
import com.martinstofanak.simplerxapp.android.data.api.provider.WeatherProvider;
import com.martinstofanak.simplerxapp.android.data.exception.LocationFetchFailedException;
import com.martinstofanak.simplerxapp.android.data.exception.WeatherSimpleRxException;
import com.martinstofanak.simplerxapp.android.ui.view.TodayImageStatView;
import com.martinstofanak.simplerxapp.android.utils.UnitUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import timber.log.Timber;

/**
 * Add header comment
 */
public class WeatherFragment extends BaseFragment {

    public static final String TAG = WeatherFragment.class.toString();

    @InjectView(R.id.today_weather_location) TextView mLocationView;
    @InjectView(R.id.today_weather_state_image) ImageView mWeatherStateImage;
    @InjectView(R.id.today_weather_state_general) TextView mWeatherGeneralView;
    @InjectView(R.id.today_weather_humidity) TodayImageStatView mHumidityView;
    @InjectView(R.id.today_weather_precipitation) TodayImageStatView mPrecipitationView;
    @InjectView(R.id.today_weather_pressure) TodayImageStatView mPressureView;
    @InjectView(R.id.today_weather_wind_speed) TodayImageStatView mWindSpeedView;
    @InjectView(R.id.today_weather_direction) TodayImageStatView mWindDirectionView;
    @InjectView(R.id.today_content_view) LinearLayout mContentView;
    @InjectView(R.id.today_message) TextView mMessageView;
    @Inject WeatherProvider mWeatherProvider;
    private Subscription mWeatherSub;


    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }


    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }


    @Override public void onDetach() {
        super.onDetach();
        mWeatherSub.unsubscribe();
    }


    private void loadData() {
        if (!ApiModule.isNetworkAvailable(getActivity())) {
            showMessage(R.string.message_error_no_connection);
            return;
        }

        mWeatherSub = mWeatherProvider.loadWeather().subscribe(new Observer<WeatherResult>() {
            @Override public void onCompleted() {
                Timber.d("COMPLETED");
            }


            @Override public void onError(Throwable e) {
                Timber.e("ON ERROR: " + e.getMessage());

                if (e instanceof LocationFetchFailedException) {
                    showMessage(R.string.message_error_location);
                } else if (e instanceof WeatherSimpleRxException) {
                    showMessage(R.string.message_error_weather);
                }
            }


            @Override public void onNext(WeatherResult weatherResult) {
                Timber.d("ON NEXT");
                renderView(weatherResult);
            }
        });
    }


    private void renderView(WeatherResult weatherResult) {
        final AddressResult addressResult = weatherResult.getAddressResult();
        mLocationView.setText(String.format("%s, %s", addressResult.getCity(), addressResult.getCountryCode()));

        final WeatherResponse weatherResponse = weatherResult.getWeatherResponse();
        final WeatherItem weather = weatherResponse.getWeather().get(0);

        final float temp = weatherResponse.getMain().getTemp();
        final String tempFormatted = UnitUtils.formatTemperature(temp);
        final String humidity = UnitUtils.formatHumidity(weatherResponse.getMain().getHumidity());
        final String precipitation = UnitUtils.formatRainAmount(getRain(weatherResponse));
        final String pressure = UnitUtils.formatPressure(weatherResponse.getMain().getPressure());
        final String windSpeed = UnitUtils.formatWindSpeed(weatherResponse.getWind().getSpeed());
        final String windDirection = weatherResponse.getWind().getDirection();
        final String iconUrl
                = String.format("%s/%s.png", ApiModule.OPEN_WEATHER_MAP_ICON_URL, weather.getIcon());
        final String general = String.format("%s | %s", tempFormatted, weather.getMain());

        Picasso.with(getActivity()).load(iconUrl).into(mWeatherStateImage);
        mWeatherGeneralView.setText(general);
        mHumidityView.bindView(humidity);
        mPrecipitationView.bindView(precipitation);
        mPressureView.bindView(pressure);
        mWindSpeedView.bindView(windSpeed);
        mWindDirectionView.bindView(windDirection);

        showContent();
    }


    private float getRain(WeatherResponse weather) {
        return weather.getRain() != null
                ? weather.getRain().getPrecipitation()
                : 0.0f;
    }


    private void showMessage(int messageResId) {
        mContentView.setVisibility(View.GONE);
        mMessageView.setVisibility(View.VISIBLE);
        mMessageView.setText(messageResId);
    }

    private void showContent() {
        mContentView.setVisibility(View.VISIBLE);
        mMessageView.setVisibility(View.GONE);
    }
}
