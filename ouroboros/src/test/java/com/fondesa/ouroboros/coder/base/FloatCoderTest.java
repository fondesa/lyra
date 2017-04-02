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
 * Unit test class for {@link FloatCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class FloatCoderTest extends BundleTestCase {
    private FloatCoder mCoder = new FloatCoder();

    @Test
    public void testSerializeFloatPrimitive() {
        float expectedValue = 9.0f;
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, bundle().getFloat(randomKey()), 0);
    }

    @Test
    public void testSerializeFloatObject() {
        Float expectedValue = 9.0f;
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, bundle().getFloat(randomKey()));
    }

    @Test
    public void testDeserializeFloatPrimitive() {
        float expectedValue = 9.0f;
        bundle().putFloat(randomKey(), expectedValue);
        assertEquals(expectedValue, mCoder.deserialize(bundle(), randomKey()), 0);
    }

    @Test
    public void testDeserializeFloatObject() {
        Float expectedValue = 9.0f;
        bundle().putFloat(randomKey(), expectedValue);
        assertEquals(expectedValue, mCoder.deserialize(bundle(), randomKey()));
    }
}