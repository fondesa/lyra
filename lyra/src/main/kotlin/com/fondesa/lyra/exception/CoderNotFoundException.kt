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

package com.fondesa.lyra.exception

import com.fondesa.lyra.coder.StateCoder
import java.lang.Exception

/**
 * This exception is thrown when a [StateCoder] for a class can't be found.
 * This could happen because there isn't any default associated coder with a specific class,
 * or because the api level doesn't support a specific coder.
 */
class CoderNotFoundException : Exception {

    /**
     * Constructs an [CoderNotFoundException] with `null`
     * as its error detail message.
     */
    constructor() : super()

    /**
     * Constructs a [CoderNotFoundException] with the specified detail message.
     * The string `message` can be retrieved later by [Throwable.message].
     *
     * @param message the detail message
     */
    constructor(message: String) : super(message)

    /**
     * Constructs a [CoderNotFoundException] with the specified detail message and cause.
     * Note that the detail message associated with `cause` isn't automatically incorporated
     * into this exception's detail message.
     *
     * @param message The detail message (which is saved for later retrieval by [.getMessage])
     * @param cause   The cause (which is saved for later retrieval by [.getCause]).
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    constructor(message: String, cause: Throwable?) : super(message, cause)

    /**
     * Constructs a [CoderNotFoundException] with the specified cause and a detail message of
     * `(cause==null ? null : cause.toString())`.
     * This constructor is useful for [CoderNotFoundException] that are little more than wrappers for other throwables.
     *
     * @param cause The cause (which is saved for later retrieval by [.getCause]).
     * (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    constructor(cause: Throwable?) : super(cause)
}