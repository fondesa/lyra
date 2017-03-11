package com.fondesa.quicksavestate.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fondesa.quicksavestate.QuickSaveState;
import com.fondesa.quicksavestate.annotation.SaveState;

/**
 * Created by antoniolig on 11/03/17.
 */
public class TestSaveStateActivity extends Activity {
    @SaveState
    String a;

    @SaveState
    private int b;

    @SaveState
    public Long c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuickSaveState.restoreState(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        QuickSaveState.saveState(this, outState);
    }

}
