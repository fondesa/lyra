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

package com.fondesa.ouroboros.coder.gson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.fondesa.ouroboros.annotation.SaveState;
import com.fondesa.ouroboros.coder.CoderRetriever;
import com.fondesa.ouroboros.coder.StateCoder;
import com.fondesa.ouroboros.coder.gson.base.GsonCoder;
import com.fondesa.ouroboros.coder.utils.StateCoderUtils;
import com.fondesa.ouroboros.exception.CoderNotFoundException;
import com.google.gson.Gson;

import java.lang.reflect.Constructor;

/**
 * Created by antoniolig on 01/04/17.
 */
public class GsonCoderRetriever implements CoderRetriever {
    private ArrayMap<Class<?>, StateCoder<?>> mCachedCoders;
    private Gson mGson;

    public GsonCoderRetriever() {
        this(new Gson());
    }

    public GsonCoderRetriever(@NonNull Gson gson) {
        mCachedCoders = new ArrayMap<>();
        mGson = gson;
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
            boolean isGsonCoder = GsonCoder.class.isAssignableFrom(stateSDClass);
            // A custom coder won't be cached to support multiple implementations for the same class.
            try {
                Constructor<? extends StateCoder> constructor;
                if (isGsonCoder) {
                    constructor = stateSDClass.getConstructor(Gson.class);
                } else {
                    constructor = stateSDClass.getConstructor();
                }
                boolean accessible = constructor.isAccessible();
                // If the constructor can't be accessed, it will be modified in accessible and will return inaccessible after.
                if (!accessible) {
                    constructor.setAccessible(true);
                }
                // Creates the instance.
                if (isGsonCoder) {
                    stateCoder = constructor.newInstance(mGson);
                } else {
                    stateCoder = constructor.newInstance();
                }
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
