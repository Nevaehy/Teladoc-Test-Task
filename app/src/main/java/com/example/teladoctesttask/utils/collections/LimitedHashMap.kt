package com.example.teladoctesttask.utils.collections

class LimitedHashMap<K, V>(private val maxSize: Int) : LinkedHashMap<K, V>() {

    override fun put(key: K, value: V): V? {
        if (size >= maxSize) {
            val iterator = iterator()
            iterator.next()
            iterator.remove()
        }
        return super.put(key, value)
    }
}