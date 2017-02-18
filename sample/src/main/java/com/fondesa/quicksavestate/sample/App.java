package com.fondesa.quicksavestate.sample;

import android.app.Application;

import com.fondesa.quicksavestate.QuickSaveState;

/**
 * Created by antoniolig on 17/02/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        new QuickSaveState(this);
    }
}
