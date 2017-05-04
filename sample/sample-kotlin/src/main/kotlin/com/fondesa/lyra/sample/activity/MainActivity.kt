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

import android.view.ViewGroup
import com.fondesa.lyra.sample.model.Model

class MainActivity : BaseMainActivity() {
    private lateinit var textView: android.widget.TextView

    @com.fondesa.lyra.annotation.SaveState(com.fondesa.lyra.coder.gson.base.DefaultGsonCoder::class)
    private var text: String? = null

    @com.fondesa.lyra.annotation.SaveState
    internal var model: com.fondesa.lyra.sample.model.ParcelableModel? = null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = initView()
        val rootViewParams = android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(rootView, rootViewParams)

        restoreState(savedInstanceState)

        printInfo()
    }

    override fun onSaveInstanceState(outState: android.os.Bundle) {
        super.onSaveInstanceState(outState)
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            com.fondesa.lyra.Lyra.instance().saveState(this, outState)
        }
    }

    private fun initView(): android.view.View {
        val root = android.widget.LinearLayout(this)
        root.orientation = android.widget.LinearLayout.VERTICAL
        val defaultPadding = resources.getDimensionPixelSize(com.fondesa.lyra.sample.R.dimen.default_inner_padding)
        root.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding)

        textView = android.widget.TextView(this)
        root.addView(textView, android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))

        val button = android.widget.Button(this)
        button.setText(com.fondesa.lyra.sample.R.string.change_value)
        button.setOnClickListener {
            text = if (text == null) "first" else null
            randomByte = if (randomByte.toInt() == 0) 1 else 0

            if (modelList == null) {
                modelList = java.util.ArrayList<Model>()
                modelList!!.add(com.fondesa.lyra.sample.model.Model(1, "first"))
                modelList!!.add(com.fondesa.lyra.sample.model.Model(2, "second"))
                modelList!!.add(com.fondesa.lyra.sample.model.Model(3, "third"))
            } else {
                modelList = null
            }
            model = if (model == null) com.fondesa.lyra.sample.model.ParcelableModel(5, "fifth") else null
            printInfo()
        }
        val buttonParams = android.widget.LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        buttonParams.gravity = android.view.Gravity.BOTTOM or android.view.Gravity.CENTER_HORIZONTAL

        root.addView(button, buttonParams)

        val autoSaveEditText = com.fondesa.lyra.sample.widget.AutoSaveEditText(this)
        autoSaveEditText.id = com.fondesa.lyra.sample.R.id.auto_save_edit_text
        root.addView(autoSaveEditText, android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        return root
    }

    private fun restoreState(savedInstanceState: android.os.Bundle?) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            com.fondesa.lyra.Lyra.instance().restoreState(this, savedInstanceState)
        }
    }

    private fun printInfo() {
        textView.text = null

        textView.append("String: $text\n")
        textView.append("Byte: $randomByte\n")

        textView.append("Model list: ")
        if (modelList != null) {
            var text = ""
            for (i in 0..modelList!!.size - 1) {
                if (i != 0)
                    text += " - "

                val m = modelList!![i]
                text += "${m.id}: ${m.value}"
            }
            textView.append(text)
        } else {
            textView.append("null")
        }

        textView.append("\n")

        textView.append("Parcelable model: ")
        if (model != null) {
            textView.append("${model!!.id}: ${model!!.value}")
        } else {
            textView.append("null")
        }
    }
}