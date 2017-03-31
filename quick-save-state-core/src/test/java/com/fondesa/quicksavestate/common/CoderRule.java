/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fondesa.quicksavestate.common;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.quicksavestate.coder.StateCoder;

import org.junit.rules.ExternalResource;

import java.util.UUID;

/**
 * Rule used to initialize a {@link StateCoder} before a test method.
 * This coder can be used to serialize/deserialize a value into/from a {@link Bundle}.
 */
public class CoderRule<Coder> extends ExternalResource {
    private Coder mCoder;
    private Bundle mBundle;

    private final Class<? extends Coder> mCoderClass;
    private final String mRandomKey;

    /**
     * Creates an instance of {@link CoderRule} from a coder class.
     * The initialization will be done through reflection so a provided empty constructor
     * in the {@link StateCoder} is needed.
     *
     * @param coderClass coder to initialize
     */
    public CoderRule(@NonNull Class<? extends Coder> coderClass) {
        mCoderClass = coderClass;
        // Random key using UUID.
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

    /**
     * @return {@link Bundle} in/from which a value will be serialized/deserialized
     */
    public Bundle bundle() {
        return mBundle;
    }

    /**
     * @return instance of {@link StateCoder} created before the test method
     */
    public Coder coder() {
        return mCoder;
    }

    /**
     * @return key that can be used to insert/retrieve a value into/from the {@link Bundle}
     */
    public String randomKey() {
        return mRandomKey;
    }
}