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

package com.fondesa.lyra.sample.widget

import android.content.Context
import android.os.Parcelable
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet

import com.fondesa.lyra.Lyra
import com.fondesa.lyra.annotation.SaveState

class AutoSaveEditText : AppCompatEditText {
    @SaveState
    internal var insertedText: CharSequence? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSaveInstanceState(): Parcelable {
        insertedText = text
        return Lyra.instance().saveState(this, super.onSaveInstanceState())
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        super.onRestoreInstanceState(Lyra.instance().restoreState(this, state))
        setText(insertedText)
    }
}