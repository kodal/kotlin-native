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

package kotlin.sequences

import kotlin.comparisons.*
import kotlin.coroutines.experimental.*
import kotlin.native.internal.FixmeConcurrency

@FixmeConcurrency
internal actual class ConstrainedOnceSequence<T> actual constructor(sequence: Sequence<T>) : Sequence<T> {
    // TODO: not MT friendly.
    private var sequenceRef : Sequence<T>? = sequence

    override actual fun iterator(): Iterator<T> {
        val sequence = sequenceRef
        if (sequence == null) throw IllegalStateException("This sequence can be consumed only once.")
        sequenceRef = null
        return sequence.iterator()
    }
}
