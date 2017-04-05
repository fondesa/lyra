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

package com.fondesa.ouroboros.sharedtest;

import android.os.Bundle;

import org.junit.Before;

import io.github.benas.randombeans.EnhancedRandomBuilder;

/**
 * Class used to run a test suite accessing to a {@link Bundle}.
 * This class provides an empty {@link Bundle} before each test method and a random generated key.
 */
public class BundleTestCase {
    private Bundle mBundle;
    private String mRandomKey;

    @Before
    public void setUp() {
        // Random key using UUID.
        mBundle = new Bundle();
        mRandomKey = EnhancedRandomBuilder.aNewEnhancedRandom().nextObject(String.class);
    }

    /**
     * Bundle to use in test methods.
     *
     * @return the {@link Bundle} instance created before the test method.
     */
    protected Bundle bundle() {
        return mBundle;
    }

    /**
     * @return random key used to write/read from/into a {@link Bundle}
     */
    protected String randomKey() {
        return mRandomKey;
    }
}