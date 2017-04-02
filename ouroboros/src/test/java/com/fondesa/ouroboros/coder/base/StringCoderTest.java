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

package com.fondesa.ouroboros.coder.base;

import com.fondesa.ouroboros.sharedtest.BundleTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link StringCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class StringCoderTest extends BundleTestCase {
    private StringCoder mCoder = new StringCoder();

    @Test
    public void testSerializeString() {
        String expectedValue = "test";
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, bundle().getString(randomKey()));
    }

    @Test
    public void testDeserializeString() {
        String expectedValue = "test";
        bundle().putString(randomKey(), expectedValue);
        assertEquals(expectedValue, mCoder.deserialize(bundle(), randomKey()));
    }
}