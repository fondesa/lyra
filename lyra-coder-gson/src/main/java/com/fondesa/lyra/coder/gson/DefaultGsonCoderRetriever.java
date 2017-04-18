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

package com.fondesa.lyra.coder.gson;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.fondesa.lyra.annotation.SaveState;
import com.fondesa.lyra.coder.DefaultCoderRetriever;
import com.fondesa.lyra.coder.StateCoder;
import com.fondesa.lyra.coder.gson.base.GsonCoder;
import com.fondesa.lyra.exception.CoderNotFoundException;
import com.google.gson.Gson;

import java.lang.reflect.Constructor;

/**
 * Default implementation to retrieve a {@link GsonCoder}.
 * This implementation is based on {@link DefaultCoderRetriever} adding {@link Gson} capabilities.
 */
public class DefaultGsonCoderRetriever extends DefaultCoderRetriever {
    private Gson mGson;

    /**
     * Creates a new instance of {@link DefaultGsonCoderRetriever} using a base {@link Gson} instance.
     */
    public DefaultGsonCoderRetriever() {
        this(new Gson());
    }

    /**
     * Creates a new instance of {@link DefaultGsonCoderRetriever} with a given {@link Gson} instance.
     * This constructor is useful if you want to pass a custom instance with adapters or serializers.
     *
     * @param gson instance of {@link Gson} that will be used in the serialization/deserialization process
     */
    public DefaultGsonCoderRetriever(@NonNull Gson gson) {
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
        if (GsonCoder.class.isAssignableFrom(stateSDClass)) {
            try {
                Constructor<? extends StateCoder> constructor;
                // The constructor must have one argument of Gson type.
                constructor = stateSDClass.getConstructor(Gson.class);
                boolean accessible = constructor.isAccessible();
                // If the constructor can't be accessed, it will be modified in accessible and will return inaccessible after.
                if (!accessible) {
                    constructor.setAccessible(true);
                }
                // Creates the instance.
                stateCoder = constructor.newInstance(mGson);
                if (!accessible) {
                    constructor.setAccessible(false);
                }
            } catch (Exception e) {
                throw new RuntimeException("You must provide an instance of " + GsonCoder.class.getName());
            }
        } else {
            // Use default implementation.
            stateCoder = super.getCoder(saveState, annotatedFieldClass);
        }
        return stateCoder;
    }
}