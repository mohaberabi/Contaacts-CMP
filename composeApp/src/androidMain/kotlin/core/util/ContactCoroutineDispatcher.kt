package core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class ContactCoroutineDispatcher {


    actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    actual val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    actual val unconfinedDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
}

