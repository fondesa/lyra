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

import com.fondesa.ouroboros.common.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link ByteArrayCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class ByteArrayCoderTest {
    @Rule
    public final CoderRule<ByteArrayCoder> mCoderRule = new CoderRule<>(ByteArrayCoder.class);

    @Test
    public void testSerializeByteArray() {
        byte[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getByteArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeByteArray() {
        byte[] expected = generateArrayAndFill();
        mCoderRule.bundle().putByteArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private byte[] generateArrayAndFill() {
        byte[] array = new byte[300];
        Arrays.fill(array, (byte) 2);
        return array;
    }
}