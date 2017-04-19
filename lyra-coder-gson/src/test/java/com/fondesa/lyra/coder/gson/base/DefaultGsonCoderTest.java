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

package com.fondesa.lyra.coder.gson.base;

import com.fondesa.lyra.sharedtest.BundleTestCase;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Unit test class for {@link DefaultGsonCoder}.
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultGsonCoderTest extends BundleTestCase {
    private Gson mGson = new Gson();
    private DefaultGsonCoder<GsonPersonModel> mCoder = new DefaultGsonCoder<>(mGson);

    @Test
    public void testSerializeDeserializeGson() {
        GsonPersonModel expectedValue = new GsonPersonModel();
        expectedValue.name = "Giorgio";
        expectedValue.age = 22;

        mCoder.serialize(bundle(), randomKey(), expectedValue);
        GsonPersonModel obtainedValue = mCoder.deserialize(bundle(), randomKey());
        assertEquals(expectedValue.name, obtainedValue.name);
        assertEquals(expectedValue.age, obtainedValue.age);
    }

    public static class GsonPersonModel {
        public String name;
        public int age;
    }
}
