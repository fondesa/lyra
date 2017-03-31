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

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link BooleanCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class BooleanCoderTest {
    @Rule
    public final CoderRule<BooleanCoder> mCoderRule = new CoderRule<>(BooleanCoder.class);

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSerializeBooleanPrimitive() {
        boolean expectedValue = true;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getBoolean(mCoderRule.randomKey()));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testSerializeBooleanObject() {
        Boolean expectedValue = true;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, ((Boolean) mCoderRule.bundle().getBoolean(mCoderRule.randomKey())));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDeserializeBooleanPrimitive() {
        boolean expectedValue = true;
        mCoderRule.bundle().putBoolean(mCoderRule.randomKey(), expectedValue);
        assertEquals((Boolean) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testDeserializeBooleanObject() {
        Boolean expectedValue = true;
        mCoderRule.bundle().putBoolean(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}