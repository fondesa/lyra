package com.fondesa.lyra.sample

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