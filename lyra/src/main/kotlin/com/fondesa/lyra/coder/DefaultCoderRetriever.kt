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

package com.fondesa.lyra.coder

import android.os.Bundle
import android.support.v4.util.ArrayMap

import com.fondesa.lyra.Lyra
import com.fondesa.lyra.annotation.SaveState
import com.fondesa.lyra.coder.utils.StateCoderUtils
import com.fondesa.lyra.exception.CoderNotFoundException
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible

/**
 * Default implementation of [CoderRetriever] that handles the cache for coders
 * supported by default and creates the custom coders with reflection.
 * The reflection works only if there's a constructor without arguments in the custom [StateCoder] class.
 * If you want to handle different constructors, you have to implement your own [CoderRetriever]
 * and set it with [Lyra.Builder.coderRetriever].
 */
open class DefaultCoderRetriever : CoderRetriever {
    private val cachedCoders by lazy { ArrayMap<Class<*>, StateCoder<out Any>>() }


    /**
     * Retrieve a [StateCoder] from an annotation and the annotated class.
     *
     * @param saveState           annotation obtained from the annotated field
     * @param annotatedFieldClass java class of the annotate field
     * @return not null coder used to serialize and deserialize the state [Bundle]
     * @throws CoderNotFoundException if the coder can't be found or is unsupported
     */
    @Throws(CoderNotFoundException::class)
    override fun getCoder(saveState: SaveState, annotatedFieldClass: Class<*>): StateCoder<out Any> {
        val stateCoder: StateCoder<out Any>
        // Get the coder class from the annotation.
        val stateSDClass = saveState.value
        if (stateSDClass == StateCoder::class) {
            // Get the coder from cache, if present.
            val cachedCoder = cachedCoders[annotatedFieldClass]
            if (cachedCoder != null)
                return cachedCoder

            // Get the coder if it's supported by default or throw a new CoderNotFoundException.
            stateCoder = StateCoderUtils.getBasicCoderForClass(annotatedFieldClass)
            // Put the coder in cache.
            cachedCoders.put(annotatedFieldClass, stateCoder)
        } else {
            // A custom coder won't be cached to support multiple implementations for the same class.
            val constructor = stateSDClass.primaryConstructor
            if (constructor == null || constructor.parameters.isEmpty())
                throw RuntimeException("No valid constructor available for class ${stateSDClass::class.qualifiedName}")

            val accessible = constructor.isAccessible
            // If the constructor can't be accessed, it will be modified in accessible and will return inaccessible after.
            if (!accessible) {
                constructor.isAccessible = true
            }
            // Creates the instance.
            stateCoder = constructor.call()
            if (!accessible) {
                constructor.isAccessible = false
            }
        }
        return stateCoder
    }
}