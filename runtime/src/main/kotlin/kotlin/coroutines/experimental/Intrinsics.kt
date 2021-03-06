/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kotlin.coroutines.experimental.intrinsics

import kotlin.coroutines.experimental.Continuation


/**
 * Creates a coroutine without receiver and with result type [T].
 * This function creates a new, fresh instance of suspendable computation every time it is invoked.
 *
 * To start executing the created coroutine, invoke `resume(Unit)` on the returned [Continuation] instance.
 * The [completion] continuation is invoked when coroutine completes with result or exception.
 *
 * This function is _unchecked_. Repeated invocation of any resume function on the resulting continuation corrupts the
 * state machine of the coroutine and may result in arbitrary behaviour or exception.
 */
public actual fun <T> (suspend () -> T).createCoroutineUnchecked(
        completion: Continuation<T>
): Continuation<Unit> = errorExperimentalCoroutinesAreNoLongerSupported()

/**
 * Creates a coroutine with receiver type [R] and result type [T].
 * This function creates a new, fresh instance of suspendable computation every time it is invoked.
 *
 * To start executing the created coroutine, invoke `resume(Unit)` on the returned [Continuation] instance.
 * The [completion] continuation is invoked when coroutine completes with result or exception.
 *
 * This function is _unchecked_. Repeated invocation of any resume function on the resulting continuation corrupts the
 * state machine of the coroutine and may result in arbitrary behaviour or exception.
 */
public actual fun <R, T> (suspend R.() -> T).createCoroutineUnchecked(
        receiver: R,
        completion: Continuation<T>
): Continuation<Unit> = errorExperimentalCoroutinesAreNoLongerSupported()

/**
 * Starts unintercepted coroutine without receiver and with result type [T] and executes it until its first suspension.
 * Returns the result of the coroutine or throws its exception if it does not suspend or [COROUTINE_SUSPENDED] if it suspends.
 * In the later case, the [completion] continuation is invoked when coroutine completes with result or exception.
 * This function is designed to be used from inside of [suspendCoroutineOrReturn] to resume the execution of suspended
 * coroutine using a reference to the suspending function.
 */
@Suppress("UNCHECKED_CAST")
@kotlin.internal.InlineOnly
public actual inline fun <T> (suspend () -> T).startCoroutineUninterceptedOrReturn(
        completion: Continuation<T>
): Any? = errorExperimentalCoroutinesAreNoLongerSupported()

/**
 * Starts unintercepted coroutine with receiver type [R] and result type [T] and executes it until its first suspension.
 * Returns the result of the coroutine or throws its exception if it does not suspend or [COROUTINE_SUSPENDED] if it suspends.
 * In the later case, the [completion] continuation is invoked when coroutine completes with result or exception.
 * This function is designed to be used from inside of [suspendCoroutineOrReturn] to resume the execution of suspended
 * coroutine using a reference to the suspending function.
 */
@Suppress("UNCHECKED_CAST")
@kotlin.internal.InlineOnly
public actual inline fun <R, T> (suspend R.() -> T).startCoroutineUninterceptedOrReturn(
        receiver: R,
        completion: Continuation<T>
): Any? = errorExperimentalCoroutinesAreNoLongerSupported()



/**
 * This value is used as a return value of [suspendCoroutineOrReturn] `block` argument to state that
 * the execution was suspended and will not return any result immediately.
 */
@SinceKotlin("1.1")
public actual val COROUTINE_SUSPENDED: Any get() = CoroutineSingletons.COROUTINE_SUSPENDED

// Using enum here ensures two important properties:
//  1. It makes SafeContinuation serializable with all kinds of serialization frameworks (since all of them natively support enums)
//  2. It improves debugging experience, since you clearly see toString() value of those objects and what package they come from
private enum class CoroutineSingletons {
    COROUTINE_SUSPENDED
}

@PublishedApi
internal fun errorExperimentalCoroutinesAreNoLongerSupported(): Nothing =
        error("kotlin.coroutines.experimental is no longer supported, please migrate to kotlin.coroutines")
