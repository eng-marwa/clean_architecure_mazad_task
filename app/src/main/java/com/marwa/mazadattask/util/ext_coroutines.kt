package com.marwa.mazadattask.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ui(invoke: suspend () -> Unit) = CoroutineScope(Dispatchers.Main).launch {
    invoke.invoke()
}


fun Int.delay(invoke: suspend () -> Unit) = ui {
    kotlinx.coroutines.delay(this.toLong())
    invoke.invoke()
}

fun network(invoke: suspend () -> Unit) =  CoroutineScope(Dispatchers.IO).launch {
    invoke.invoke()
}
