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

import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;

import com.fondesa.quicksavestate.common.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link IBinderCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class IBinderCoderTest {
    @Rule
    public final CoderRule<IBinderCoder> mCoderRule = new CoderRule<>(IBinderCoder.class);

    @Test
    public void testSerializeIBinder() {
        IBinder expectedValue = new Binder();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, BundleCompat.getBinder(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeIBinder() {
        IBinder expectedValue = new Binder();
        BundleCompat.putBinder(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}