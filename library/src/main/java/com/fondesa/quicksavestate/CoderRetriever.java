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

import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.coder.StateCoder;
import com.fondesa.quicksavestate.exception.CoderNotFoundException;

/**
 * Interface that manages the way in which a {@link StateCoder} is retrieved from an annotation.
 * <br>
 * The default implementation is: {@link DefaultCoderRetriever}.
 * You can implement your own {@link CoderRetriever} and set it in your singleton instance
 * with {@link QuickSaveState.Builder#coderRetriever(CoderRetriever)} (for example to
 * optimize performance, to handle the cache differently, to handle the creation of coders manually).
 */
public interface CoderRetriever {

    /**
     * Retrieve a {@link StateCoder} from an annotation and the annotated class.
     *
     * @param saveState           annotation obtained from the annotated field
     * @param annotatedFieldClass java class of the annotate field
     * @return not null coder used to serialize and deserialize the state {@link Bundle}
     * @throws CoderNotFoundException if the coder can't be found or is unsupported
     */
    @NonNull
    StateCoder getCoder(@NonNull SaveState saveState, @NonNull Class<?> annotatedFieldClass) throws CoderNotFoundException;
}