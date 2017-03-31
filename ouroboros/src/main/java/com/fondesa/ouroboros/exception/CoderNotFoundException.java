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

package com.fondesa.ouroboros.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fondesa.ouroboros.coder.StateCoder;

/**
 * This exception is thrown when a {@link StateCoder} for a class can't be found.
 * This could happen because there isn't any default associated coder with a specific class,
 * or because the api level doesn't support a specific coder.
 */
public class CoderNotFoundException extends Exception {
    private static final long serialVersionUID = 8422996591439458736L;

    /**
     * Constructs an {@link CoderNotFoundException} with <code>null</code>
     * as its error detail message.
     */
    public CoderNotFoundException() {
        super();
    }

    /**
     * Constructs a {@link CoderNotFoundException} with the specified detail message.
     * The string {@code message} can be retrieved later by {@link Throwable#getMessage()}.
     *
     * @param message the detail message
     */
    public CoderNotFoundException(@NonNull String message) {
        super(message);
    }

    /**
     * Constructs a {@link CoderNotFoundException} with the specified detail message and cause.
     * Note that the detail message associated with {@code cause} isn't automatically incorporated
     * into this exception's detail message.
     *
     * @param message The detail message (which is saved for later retrieval by {@link #getMessage()})
     * @param cause   The cause (which is saved for later retrieval by {@link #getCause()}).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public CoderNotFoundException(@NonNull String message, @Nullable Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a {@link CoderNotFoundException} with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())}.
     * This constructor is useful for {@link CoderNotFoundException} that are little more than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by {@link #getCause()}).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public CoderNotFoundException(@Nullable Throwable cause) {
        super(cause);
    }
}