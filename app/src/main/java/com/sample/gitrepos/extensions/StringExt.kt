package com.sample.gitrepos.extensions


/**
 * String extension functions to be added in this file
 */



fun String.toIntOrDefault(default: Int = 0): Int = this.toIntOrNull()
    ?: default

fun String.toDoubleOrDefault(default: Double = 0.0): Double = this.toDoubleOrNull()
    ?: default

fun String.toFloatOrDefault(default: Float = 0f): Float = this.toFloatOrNull()
    ?: default

fun String.toLongOrDefault(default: Long = 0): Long = this.toLongOrNull()
    ?: default

fun String?.filterNull(defaultValue: String = ""): String {
    return this ?: defaultValue
}