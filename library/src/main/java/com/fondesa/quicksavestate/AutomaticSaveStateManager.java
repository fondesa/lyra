package com.fondesa.quicksavestate;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by antoniolig on 01/03/17.
 */
@RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class AutomaticSaveStateManager implements Application.ActivityLifecycleCallbacks {
    private boolean mAutoSaveActivities;
    private boolean mAutoSaveSupportFragments;

    public AutomaticSaveStateManager(@NonNull Application application,
                                     boolean autoSaveActivities,
                                     boolean autoSaveSupportFragments) {

        mAutoSaveActivities = autoSaveActivities;
        mAutoSaveSupportFragments = autoSaveSupportFragments;
        application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (mAutoSaveActivities) {
            QuickSaveState.restoreState(activity, savedInstanceState);
        }

        if (mAutoSaveSupportFragments && activity instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) activity;
            fragmentActivity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentAutomaticSaveState(), true);
        }
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
        if (mAutoSaveActivities) {
            QuickSaveState.saveState(activity, outState);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) { /* empty */ }

    private static final class FragmentAutomaticSaveState extends FragmentManager.FragmentLifecycleCallbacks {
        @Override
        public void onFragmentCreated(FragmentManager fm, android.support.v4.app.Fragment f, Bundle savedInstanceState) {
            QuickSaveState.restoreState(f, savedInstanceState);
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fm, android.support.v4.app.Fragment f, Bundle outState) {
            QuickSaveState.saveState(f, outState);
        }
    }
}