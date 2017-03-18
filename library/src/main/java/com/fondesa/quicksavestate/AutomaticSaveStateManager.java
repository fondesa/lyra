package com.fondesa.quicksavestate;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

/**
 * Created by antoniolig on 01/03/17.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AutomaticSaveStateManager implements Application.ActivityLifecycleCallbacks {
    private Listener mListener;

    public AutomaticSaveStateManager(@NonNull Listener listener) {
        mListener = listener;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mListener.onRestoreState(activity, savedInstanceState);
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
        mListener.onSaveState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) { /* empty */ }

    public interface Listener {
        void onSaveState(@NonNull Object holder, @NonNull Bundle outState);

        void onRestoreState(@NonNull Object holder, @Nullable Bundle savedState);
    }
}