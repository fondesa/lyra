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

package com.fondesa.lyra.sample.activity

import android.support.v7.app.AppCompatActivity
import com.fondesa.lyra.annotation.SaveState
import com.fondesa.lyra.sample.model.Model
import java.util.*

open class BaseMainActivity : AppCompatActivity() {
    @SaveState
    internal var randomByte: Byte = 0

    @SaveState
    var modelList: ArrayList<Model>? = null
        protected set
}