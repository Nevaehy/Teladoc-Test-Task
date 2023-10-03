package com.example.teladoctesttask.utils.extensions

fun <K> MutableMap<K, Int>.inc(key: K): Int = merge(key, 1, Math::addExact)!!