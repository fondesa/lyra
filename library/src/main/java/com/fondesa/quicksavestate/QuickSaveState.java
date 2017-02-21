package com.fondesa.quicksavestate;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by antoniolig on 17/02/17.
 */
public class QuickSaveState implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = QuickSaveState.class.getSimpleName();

    private SaveStateProcessor mProcessor;

    private static QuickSaveState instance;

    public static void init(@NonNull Application application) {
        if (instance != null) {
            Log.w(TAG, "The instance is initialized. You have called init() multiple times.");
        }
        instance = new QuickSaveState(application);
    }

    public static void saveState(@NonNull Object stateHolder, @NonNull Bundle state) {
        if (instance == null) {
            throw getNullInstanceException();
        }
        instance.mProcessor.saveState(stateHolder, state);
    }

    public static void restoreState(@NonNull Object stateHolder, @Nullable Bundle state) {
        if (instance == null) {
            throw getNullInstanceException();
        }
        instance.mProcessor.restoreState(stateHolder, state);
    }

    private static NullPointerException getNullInstanceException() {
        return new NullPointerException("Instance not initialized. You have to call " +
                QuickSaveState.class.getSimpleName() + "init() in your application.");
    }

    private QuickSaveState(@NonNull Application application) {
        mProcessor = new SaveStateProcessor();
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        restoreState(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(Activity activity) { /* empty */ }

    @Override
    public void onActivityResumed(Activity activity) { /* empty */ }

    @Override
    public void onActivityPaused(Activity activity) { /* empty */ }

    @Override
    public void onActivityStopped(Activity activity) { /* empty */ }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        saveState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) { /* empty */ }
}