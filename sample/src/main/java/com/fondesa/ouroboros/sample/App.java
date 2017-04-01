package com.fondesa.ouroboros.sample;

import android.app.Application;
import android.os.Build;

import com.fondesa.ouroboros.Ouroboros;
import com.fondesa.ouroboros.coder.gson.GsonCoderRetriever;
import com.fondesa.ouroboros.field.DefaultFieldsRetriever;

/**
 * Created by antoniolig on 17/02/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Ouroboros.Builder builder = Ouroboros.with(this)
                .coderRetriever(new GsonCoderRetriever())
                .fieldsRetriever(new DefaultFieldsRetriever());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder.autoSaveActivities();
        }
        builder.build();
    }
}
