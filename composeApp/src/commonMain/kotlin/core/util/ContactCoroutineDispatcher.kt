package core.util

import kotlinx.coroutines.CoroutineDispatcher

expect class ContactCoroutineDispatcher {


    val ioDispatcher: CoroutineDispatcher
    val mainDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
    val unconfinedDispatcher: CoroutineDispatcher

}

