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

package com.fondesa.quicksavestate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.coder.utils.StateCoderUtils;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

import java.lang.reflect.Constructor;

/**
 * Default implementation of {@link CoderRetriever} that handles the cache for coders
 * supported by default and creates the custom coders with reflection.
 * The reflection works only if there's a constructor without arguments in the custom {@link StateCoder} class.
 * If you want to handle different constructors, you have to implement your own {@link CoderRetriever}
 * and set it with {@link QuickSaveState.Builder#coderRetriever(CoderRetriever)}.
 */
public class DefaultCoderRetriever implements CoderRetriever {
    private ArrayMap<Class<?>, StateCoder<?>> mCachedCoders;

    /**
     * Creates a new instance of {@link DefaultCoderRetriever} and initialize the cache map.
     */
    public DefaultCoderRetriever() {
        mCachedCoders = new ArrayMap<>();
    }

    /**
     * Retrieve a {@link StateCoder} from an annotation and the annotated class.
     *
     * @param saveState           annotation obtained from the annotated field
     * @param annotatedFieldClass java class of the annotate field
     * @return not null coder used to serialize and deserialize the state {@link Bundle}
     * @throws CoderNotFoundException if the coder can't be found or is unsupported
     */
    @NonNull
    @Override
    public StateCoder getCoder(@NonNull SaveState saveState, @NonNull Class<?> annotatedFieldClass) throws CoderNotFoundException {
        StateCoder stateCoder;
        // Get the coder class from the annotation.
        final Class<? extends StateCoder> stateSDClass = saveState.value();
        if (stateSDClass == StateCoder.class) {
            // Get the coder from cache, if present.
            stateCoder = mCachedCoders.get(annotatedFieldClass);
            if (stateCoder != null)
                return stateCoder;

            // Get the coder if it's supported by default or throw a new CoderNotFoundException.
            stateCoder = StateCoderUtils.getBasicCoderForClass(annotatedFieldClass);
            // Put the coder in cache.
            mCachedCoders.put(annotatedFieldClass, stateCoder);
        } else {
            // A custom coder won't be cached to support multiple implementations for the same class.
            try {
                Constructor<? extends StateCoder> constructor = stateSDClass.getConstructor();
                boolean accessible = constructor.isAccessible();
                // If the constructor can't be accessed, it will be modified in accessible and will return inaccessible after.
                if (!accessible) {
                    constructor.setAccessible(true);
                }
                // Creates the instance.
                stateCoder = constructor.newInstance();
                if (!accessible) {
                    constructor.setAccessible(false);
                }
            } catch (Exception e) {
                throw new RuntimeException("Cannot instantiate a " + StateCoder.class.getSimpleName() +
                        " of class " + stateSDClass.getName());
            }
        }
        return stateCoder;
    }
}