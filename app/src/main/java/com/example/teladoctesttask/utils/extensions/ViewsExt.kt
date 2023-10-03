package com.example.teladoctesttask.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DEBOUNCE_TIME: Long = 500

fun EditText.setOnTextChangeDebouncedListener(
    debounce: Long = DEBOUNCE_TIME,
    action: (query: String) -> Unit
) {
    addTextChangedListener(object : TextWatcher {
        private var debounceJob: Job? = null

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            debounceJob?.cancel()
            debounceJob = CoroutineScope(Dispatchers.Default).launch {
                delay(debounce)
                withContext(Dispatchers.Main) { action(s.toString()) }
            }
        }
    })
}