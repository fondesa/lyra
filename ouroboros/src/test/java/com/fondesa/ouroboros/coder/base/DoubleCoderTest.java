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
 * Unit test class for {@link DoubleCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class DoubleCoderTest extends BundleTestCase {
    private DoubleCoder mCoder = new DoubleCoder();

    @Test
    public void testSerializeDoublePrimitive() {
        double expectedValue = 9.0;
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, bundle().getDouble(randomKey()), 0);
    }

    @Test
    public void testSerializeDoubleObject() {
        Double expectedValue = 9.0;
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, bundle().getDouble(randomKey()));
    }

    @Test
    public void testDeserializeDoublePrimitive() {
        double expectedValue = 9.0;
        bundle().putDouble(randomKey(), expectedValue);
        assertEquals(expectedValue, mCoder.deserialize(bundle(), randomKey()), 0);
    }

    @Test
    public void testDeserializeDoubleObject() {
        Double expectedValue = 9.0;
        bundle().putDouble(randomKey(), expectedValue);
        assertEquals(expectedValue, mCoder.deserialize(bundle(), randomKey()));
    }
}