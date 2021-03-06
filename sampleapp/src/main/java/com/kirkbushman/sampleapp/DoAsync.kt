package com.kirkbushman.sampleapp

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DoAsync(

    private val doWork: () -> Unit,
    private val onPre: (() -> Unit)? = null,
    private val onPost: (() -> Unit)? = null
) : CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        execute()
    }

    fun cancel() {
        job.cancel()
    }

    private fun execute() = launch {

        onPre?.invoke()
        doInBackground()
        onPost?.invoke()
    }

    private suspend fun doInBackground() = withContext(Dispatchers.IO) {

        doWork.invoke()
    }
}
