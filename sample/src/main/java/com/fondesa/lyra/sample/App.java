package com.fondesa.lyra.sample;

import android.app.Application;
import android.os.Build;

import com.fondesa.lyra.Lyra;
import com.fondesa.lyra.coder.gson.DefaultGsonCoderRetriever;
import com.fondesa.lyra.field.DefaultFieldsRetriever;

/**
 * Created by antoniolig on 17/02/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Lyra.Builder builder = Lyra.with(this)
                .coderRetriever(new DefaultGsonCoderRetriever())
                .fieldsRetriever(new DefaultFieldsRetriever());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder.autoSaveActivities();
        }
        builder.build();
    }
}
