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

package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.common.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link FloatArrayCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class FloatArrayCoderTest {
    @Rule
    public final CoderRule<FloatArrayCoder> mCoderRule = new CoderRule<>(FloatArrayCoder.class);

    @Test
    public void testSerializeFloatArray() {
        float[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getFloatArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeFloatArray() {
        float[] expected = generateArrayAndFill();
        mCoderRule.bundle().putFloatArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private float[] generateArrayAndFill() {
        float[] array = new float[300];
        Arrays.fill(array, 9.0f);
        return array;
    }
}