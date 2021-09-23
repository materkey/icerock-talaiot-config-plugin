/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.gradle.talaiot

val Long.millisecondsAsSeconds: Long get() {
    return this / 1000
}

val Float.millisecondsAsSeconds: Float get() {
    return this / 1000
}