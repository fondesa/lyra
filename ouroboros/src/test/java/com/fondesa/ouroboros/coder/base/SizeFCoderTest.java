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


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.SizeF;

import com.fondesa.ouroboros.common.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link SizeFCoder}.
 */
@RunWith(RobolectricTestRunner.class)
@Config(minSdk = Build.VERSION_CODES.LOLLIPOP)
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SizeFCoderTest {
    @Rule
    public final CoderRule<SizeFCoder> mCoderRule = new CoderRule<>(SizeFCoder.class);

    @Test
    public void testSerializeSizeF() {
        SizeF expectedValue = new SizeF(9, 5);
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getSizeF(mCoderRule.randomKey()));

    }

    @Test
    public void testDeserializeSizeF() {
        SizeF expectedValue = new SizeF(9, 5);
        mCoderRule.bundle().putSizeF(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}