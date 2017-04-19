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

package com.fondesa.lyra.coder.base;

import com.fondesa.lyra.sharedtest.BundleTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link IntArrayCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class IntArrayCoderTest extends BundleTestCase {
    private IntArrayCoder mCoder = new IntArrayCoder();

    @Test
    public void testSerializeIntArray() {
        int[] expected = generateArrayAndFill();
        mCoder.serialize(bundle(), randomKey(), expected);
        assertEquals(expected, bundle().getIntArray(randomKey()));
    }

    @Test
    public void testDeserializeIntArray() {
        int[] expected = generateArrayAndFill();
        bundle().putIntArray(randomKey(), expected);
        assertEquals(expected, mCoder.deserialize(bundle(), randomKey()));
    }

    private int[] generateArrayAndFill() {
        int[] array = new int[300];
        Arrays.fill(array, 9);
        return array;
    }
}