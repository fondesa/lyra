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

package com.fondesa.lyra.coder.utils

import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import com.fondesa.lyra.coder.StateCoder
import com.fondesa.lyra.coder.base.*
import com.fondesa.lyra.exception.CoderNotFoundException
import java.io.Serializable

/**
 * Utilities class to get a [StateCoder] from a class.
 * Only default coders are used in this class.
 */
object StateCoderUtils {

    class BooleanCiao : StateCoder<Boolean> {
        override fun serialize(state: Bundle, key: String, fieldValue: Boolean) {
            TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        }

        override fun deserialize(state: Bundle, key: String): Boolean {
            TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        }

    }

    /**
     * Get the default [StateCoder] from a class.
     * If the class is equal to the coder class or the coder class extends/implements the class
     * passed as parameter, the class is supported.
     * In the other cases, you have to implement your own [StateCoder].
     *
     * @param cls class that can be serialized/deserialized with a [StateCoder]
     * @return coder related to the class passed as parameter
     * @throws CoderNotFoundException if the coder wasn't found
     */
    @Throws(CoderNotFoundException::class)
    fun <T : Any> getBasicCoderForClass(cls: Class<*>): StateCoder<T> = when (cls) {
        Boolean::class.java -> BooleanCiao()
        BooleanArray::class.java -> BooleanArrayCoder()
        Byte::class.java -> ByteCoder()
        ByteArray::class.java -> ByteArrayCoder()
        Char::class.java -> CharCoder()
        CharArray::class.java -> CharArrayCoder()
        CharSequence::class.java -> CharSequenceCoder()
        Array<CharSequence>::class.java -> CharSequenceArrayCoder()
        Double::class.java -> DoubleCoder()
        DoubleArray::class.java -> DoubleArrayCoder()
        Float::class.java -> FloatCoder()
        FloatArray::class.java -> FloatArrayCoder()
        IBinder::class.java -> IBinderCoder()
        Int::class.java -> IntCoder()
        IntArray::class.java -> IntArrayCoder()
        Long::class.java -> LongCoder()
        LongArray::class.java -> LongArrayCoder()
        Parcelable::class.java -> ParcelableCoder()
        Array<Parcelable>::class.java -> ParcelableArrayCoder()
        Serializable::class.java -> SerializableCoder()
        Short::class.java -> ShortCoder()
        ShortArray::class.java -> ShortArrayCoder()
        String::class.java -> StringCoder()
        Array<String>::class.java -> StringArrayCoder()

        else -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                when (cls) {
                    Size::class.java -> SizeCoder()
                    SizeF::class.java -> SizeFCoder()
                    else -> getBasicCoderForInheritedClass(cls)
                }
            } else {
                getBasicCoderForInheritedClass(cls)
            }
        }
    }

    /**
     * Get the [StateCoder] if the class passed as parameter is a subclass of the supported ones.
     *
     * @param cls class that can be serialized/deserialized with a [StateCoder]
     * @return coder related to the class passed as parameter
     * @throws CoderNotFoundException if the coder wasn't found
     */
    @Throws(CoderNotFoundException::class)
    private fun getBasicCoderForInheritedClass(cls: Class<*>): StateCoder<out Any> {
        if (CharSequence::class.java.isAssignableFrom(cls))
            return CharSequenceCoder()

        if (Array<CharSequence>::class.java.isAssignableFrom(cls))
            return CharSequenceArrayCoder()

        if (IBinder::class.java.isAssignableFrom(cls))
            return IBinderCoder()

        if (Parcelable::class.java.isAssignableFrom(cls))
            return ParcelableCoder()

        if (Array<Parcelable>::class.java.isAssignableFrom(cls))
            return ParcelableArrayCoder()

        if (Serializable::class.java.isAssignableFrom(cls))
            return SerializableCoder()

        throw CoderNotFoundException("You have to specify a custom ${StateCoder::class.java.name} " +
                "for an object of type ${cls.name}")
    }
}