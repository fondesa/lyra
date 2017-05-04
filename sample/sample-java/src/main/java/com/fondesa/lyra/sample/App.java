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

package com.fondesa.lyra.sample;

import android.app.Application;
import android.os.Build;

import com.fondesa.lyra.Lyra;
import com.fondesa.lyra.coder.gson.DefaultGsonCoderRetriever;
import com.fondesa.lyra.field.DefaultFieldsRetriever;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Lyra.Builder builder = Lyra.with(this)
                .coderRetriever(new DefaultGsonCoderRetriever())
                .fieldsRetriever(new DefaultFieldsRetriever());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder.autoSaveActivities();
        }
        builder.build();
    }
}