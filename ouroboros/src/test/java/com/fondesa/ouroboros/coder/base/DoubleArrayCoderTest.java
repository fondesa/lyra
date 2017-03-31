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
 * Unit test class for {@link DoubleArrayCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class DoubleArrayCoderTest {
    @Rule
    public final CoderRule<DoubleArrayCoder> mCoderRule = new CoderRule<>(DoubleArrayCoder.class);

    @Test
    public void testSerializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getDoubleArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeDoubleArray() {
        double[] expected = generateArrayAndFill();
        mCoderRule.bundle().putDoubleArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private double[] generateArrayAndFill() {
        double[] array = new double[300];
        Arrays.fill(array, 9.0);
        return array;
    }
}