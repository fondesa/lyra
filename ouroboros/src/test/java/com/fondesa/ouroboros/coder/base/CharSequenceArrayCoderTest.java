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

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link CharSequenceArrayCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class CharSequenceArrayCoderTest extends BundleTestCase {
    private CharSequenceArrayCoder mCoder = new CharSequenceArrayCoder();

    @Test
    public void testSerializeCharSequenceArray() {
        CharSequence[] expected = generateArrayAndFill();
        mCoder.serialize(bundle(), randomKey(), expected);
        assertEquals(expected, bundle().getCharSequenceArray(randomKey()));
    }

    @Test
    public void testDeserializeCharSequenceArray() {
        CharSequence[] expected = generateArrayAndFill();
        bundle().putCharSequenceArray(randomKey(), expected);
        assertEquals(expected, mCoder.deserialize(bundle(), randomKey()));
    }

    private CharSequence[] generateArrayAndFill() {
        CharSequence[] array = new CharSequence[300];
        Arrays.fill(array, "test");
        return array;
    }
}