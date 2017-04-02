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
 * Unit test class for {@link ByteCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class ByteCoderTest extends BundleTestCase {
    private ByteCoder mCoder = new ByteCoder();

    @Test
    public void testSerializeBytePrimitive() {
        byte expectedValue = 2;
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, bundle().getByte(randomKey()));
    }

    @Test
    public void testSerializeByteObject() {
        Byte expectedValue = 2;
        mCoder.serialize(bundle(), randomKey(), expectedValue);
        assertEquals(expectedValue, ((Byte) bundle().getByte(randomKey())));
    }

    @Test
    public void testDeserializeBytePrimitive() {
        byte expectedValue = 2;
        bundle().putByte(randomKey(), expectedValue);
        assertEquals((Byte) expectedValue, mCoder.deserialize(bundle(), randomKey()));
    }

    @Test
    public void testDeserializeByteObject() {
        Byte expectedValue = 2;
        bundle().putByte(randomKey(), expectedValue);
        assertEquals(expectedValue, mCoder.deserialize(bundle(), randomKey()));
    }
}