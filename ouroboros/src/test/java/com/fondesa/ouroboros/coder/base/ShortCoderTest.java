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
 * Unit test class for {@link ShortCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class ShortCoderTest {
    @Rule
    public final CoderRule<ShortCoder> mCoderRule = new CoderRule<>(ShortCoder.class);

    @Test
    public void testSerializeShortPrimitive() {
        short expectedValue = 9;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getShort(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeShortObject() {
        Short expectedValue = 9;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, (Short) mCoderRule.bundle().getShort(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeShortPrimitive() {
        short expectedValue = 9;
        mCoderRule.bundle().putShort(mCoderRule.randomKey(), expectedValue);
        assertEquals((Short) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeShortObject() {
        Short expectedValue = 9;
        mCoderRule.bundle().putShort(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}