package com.fondesa.quicksavestate.coder.base.rule;

import android.os.Bundle;

import org.junit.rules.ExternalResource;

/**
 * Created by antoniolig on 24/02/17.
 */
public abstract class CoderRule<Coder> extends ExternalResource {
    public Coder coder;
    public Bundle bundle;

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

    protected abstract Coder initCoder();
}