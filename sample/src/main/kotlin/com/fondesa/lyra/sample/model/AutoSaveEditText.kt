package com.fondesa.lyra.sample.model

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