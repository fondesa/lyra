package com.fondesa.quicksavestate.coder;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.junit.rules.ExternalResource;

import java.util.UUID;

/**
 * Created by antoniolig on 24/02/17.
 */
public class CoderRule<Coder> extends ExternalResource {
    private Coder mCoder;
    private Bundle mBundle;

    private final Class<? extends Coder> mCoderClass;
    private final String mRandomKey;

    public CoderRule(@NonNull Class<? extends Coder> coderClass) {
        mCoderClass = coderClass;
        mRandomKey = UUID.randomUUID().toString();
    }

    @Override
    protected void before() throws Throwable {
        mCoder = initCoder();
        mBundle = new Bundle();
    }

    @Override
    protected void after() {
        mCoder = null;
        mBundle = null;
    }

    private Coder initCoder() {
        Coder coder = null;
        try {
            coder = mCoderClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coder;
    }

    public Bundle bundle() {
        return mBundle;
    }

    public Coder coder() {
        return mCoder;
    }

    public String randomKey() {
        return mRandomKey;
    }
}