package com.fondesa.quicksavestate.sample;

import android.app.Application;
import android.os.Build;

import com.fondesa.quicksavestate.coder.DefaultCoderRetriever;
import com.fondesa.quicksavestate.field.DefaultFieldsRetriever;
import com.fondesa.quicksavestate.QuickSaveState;

/**
 * Created by antoniolig on 17/02/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        QuickSaveState.Builder builder = QuickSaveState.with(this)
                .coderRetriever(new DefaultCoderRetriever())
                .fieldsRetriever(new DefaultFieldsRetriever());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder.autoSaveActivities();
        }
        builder.build();
    }
}
