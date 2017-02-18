package com.fondesa.quicksavestate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by antoniolig on 17/02/17.
 */
public class QuickSaveState implements Application.ActivityLifecycleCallbacks {

    private SaveStateProcessor mProcessor;

    public QuickSaveState(@NonNull Application application) {
        mProcessor = new SaveStateProcessor();
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mProcessor.restoreState(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        mProcessor.saveState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
