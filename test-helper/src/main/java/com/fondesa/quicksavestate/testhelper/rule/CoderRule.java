package com.fondesa.quicksavestate.testhelper.rule;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.junit.rules.ExternalResource;

import java.util.UUID;

/**
 * Created by antoniolig on 24/02/17.
 */
public class CoderRule<Coder> extends ExternalResource {
    public Coder coder;
    private Class<? extends Coder> coderClass;
    public Bundle bundle;
    public final String randomKey;

    public CoderRule(@NonNull Class<? extends Coder> coderClass) {
        this.coderClass = coderClass;
        randomKey = UUID.randomUUID().toString();
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

    private Coder initCoder() {
        Coder coder = null;
        try {
            coder = coderClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coder;
    }
}