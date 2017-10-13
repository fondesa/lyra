package com.fondesa.lyra.annotation

import android.os.Bundle
import com.fondesa.lyra.coder.StateCoder
import com.fondesa.lyra.coder.utils.StateCoderUtils
import kotlin.reflect.KClass

/**
 * Used to annotate the fields that you want to save/restore.
 * You can pass a custom [StateCoder] to save/restore a field, otherwise,
 * a default [StateCoder] will be used.
 * The possibilities are:
 * <ul>
 * <li>`@StateCoder`: StateCoder.class</li>
 * <li>`@StateCoder(CustomCoder.class)`: CustomCoder.class</li>
 * <li>`@StateCoder(value = CustomCoder.class)`: CustomCoder.class</li>
 * </ul>
 *
 * @param value Specifies the coder to serialize the field into a [Bundle] and to
 * deserialize it from a [Bundle].
 * The default coder is [StateCoder] without a real implementation.
 * The right default coder will be chosen runtime with [StateCoderUtils.getBasicCoderForClass].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class SaveState(val value: KClass<out StateCoder<*>> = StateCoder::class)