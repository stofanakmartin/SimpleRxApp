package com.martinstofanak.simplerxapp.android.data;

import android.app.Application;
import android.net.Uri;

import com.martinstofanak.simplerxapp.android.data.api.ApiModule;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Add header comment
 */
@Module(
        includes = {
                ApiModule.class
        },
        library = true,
        complete = false
)
public class DataModule {

    static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10MB


    @Provides
    @Singleton OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app);
    }


    static OkHttpClient createOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();

        // You can install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(app.getCacheDir(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch (IOException e) {
            Timber.e(e, "Unable to install disk cache.");
        }

        return client;
    }


    @Provides
    @Singleton Picasso providePicasso(Application app, OkHttpClient client) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(client))
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                        Timber.e("Failed to load image: " + uri);
                    }
                })
                .build();
    }


}
