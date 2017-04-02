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

import android.os.Parcelable;

import com.fondesa.ouroboros.common.TestModels;
import com.fondesa.ouroboros.sharedtest.BundleTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link ParcelableArrayCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class ParcelableArrayCoderTest extends BundleTestCase {
    private ParcelableArrayCoder mCoder = new ParcelableArrayCoder();

    @Test
    public void testSerializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        mCoder.serialize(bundle(), randomKey(), expected);
        assertEquals(expected, bundle().getParcelableArray(randomKey()));
    }

    @Test
    public void testDeserializeParcelableArray() {
        Parcelable[] expected = generateArrayAndFill();
        bundle().putParcelableArray(randomKey(), expected);
        assertEquals(expected, mCoder.deserialize(bundle(), randomKey()));
    }

    private Parcelable[] generateArrayAndFill() {
        TestModels.ImplementedParcelable[] array = new TestModels.ImplementedParcelable[300];
        Arrays.fill(array, TestModels.ImplementedParcelable.getDefault());
        return array;
    }
}