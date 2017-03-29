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

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link CharCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class CharCoderTest {
    @Rule
    public final CoderRule<CharCoder> mCoderRule = new CoderRule<>(CharCoder.class);

    @Test
    public void testSerializeCharPrimitive() {
        char expectedValue = 'x';
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getChar(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeCharObject() {
        Character expectedValue = 'x';
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, ((Character) mCoderRule.bundle().getChar(mCoderRule.randomKey())));
    }

    @Test
    public void testDeserializeCharPrimitive() {
        char expectedValue = 'x';
        mCoderRule.bundle().putChar(mCoderRule.randomKey(), expectedValue);
        assertEquals((Character) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeCharObject() {
        Character expectedValue = 'x';
        mCoderRule.bundle().putChar(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}