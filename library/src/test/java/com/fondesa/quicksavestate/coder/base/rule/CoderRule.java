package com.fondesa.quicksavestate.coder.base.rule;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.junit.rules.ExternalResource;

/**
 * Created by antoniolig on 24/02/17.
 */
public class CoderRule<Coder> extends ExternalResource {
    public Coder coder;
    private Class<? extends Coder> coderClass;
    public Bundle bundle;

    public CoderRule(@NonNull Class<? extends Coder> coderClass) {
        this.coderClass = coderClass;
    }

    @Override
    protected void before() throws Throwable {
        coder = initCoder();
        bundle = new Bundle();
    }

    @Override
    protected void after() {
        coder = null;
        bundle = null;
    }

    protected Coder initCoder() {
        Coder coder = null;
        try {
            coder = coderClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coder;
    }
}