package com.fondesa.quicksavestate.sample;

import android.support.v7.app.AppCompatActivity;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.sample.model.Model;

import java.util.ArrayList;

/**
 * Created by antoniolig on 24/02/17.
 */
public class BaseMainActivity extends AppCompatActivity {
    @SaveState
    Byte mByte;

    @SaveState
    byte mPrimitiveByte;

    @SaveState
    ArrayList<Model> mModelList;
}