package com.fondesa.lyra.sample

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.fondesa.lyra.Lyra
import com.fondesa.lyra.annotation.SaveState
import com.fondesa.lyra.coder.gson.base.DefaultGsonCoder
import com.fondesa.lyra.sample.model.AutoSaveEditText
import com.fondesa.lyra.sample.model.Model
import com.fondesa.lyra.sample.model.ParcelableModel
import java.util.*

class MainActivity : BaseMainActivity() {
    private lateinit var textView: TextView

    @SaveState(DefaultGsonCoder::class)
    private var text: String? = null

    @SaveState
    internal var model: ParcelableModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = initView()
        val rootViewParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(rootView, rootViewParams)

        restoreState(savedInstanceState)

        printInfo()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Lyra.instance().saveState(this, outState)
        }
    }

    private fun initView(): View {
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        val defaultPadding = resources.getDimensionPixelSize(R.dimen.default_inner_padding)
        root.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding)

        textView = TextView(this)
        root.addView(textView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))

        val button = Button(this)
        button.setText(R.string.change_value)
        button.setOnClickListener {
            text = if (text == null) "first" else null
            randomByte = if (randomByte.toInt() == 0) 1 else 0

            if (modelList == null) {
                modelList = ArrayList<Model>()
                modelList!!.add(Model(1, "first"))
                modelList!!.add(Model(2, "second"))
                modelList!!.add(Model(3, "third"))
            } else {
                modelList = null
            }
            model = if (model == null) ParcelableModel(5, "fifth") else null
            printInfo()
        }
        val buttonParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        buttonParams.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL

        root.addView(button, buttonParams)

        val autoSaveEditText = AutoSaveEditText(this)
        autoSaveEditText.id = R.id.auto_save_edit_text
        root.addView(autoSaveEditText, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        return root
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Lyra.instance().restoreState(this, savedInstanceState)
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