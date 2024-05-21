package core.data

import android.content.Context
import core.util.ContactCoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID

actual class ImageStorage(
    private val context: Context,
    private val coroutineDispatcher: ContactCoroutineDispatcher,
) {


    actual suspend fun saveImage(bytes: ByteArray): String {
        return withContext(coroutineDispatcher.ioDispatcher) {
            val filename = UUID.randomUUID().toString() + ".jpg"
            context.openFileOutput(filename, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(bytes)
            }
            filename
        }
    }

    actual suspend fun getImage(fileName: String): ByteArray? {

        return withContext(coroutineDispatcher.ioDispatcher) {
            context.openFileInput(fileName).use { inputStream ->
                inputStream.readBytes()
            }
        }
    }

    actual suspend fun deleteImage(fileName: String) {


        return withContext(coroutineDispatcher.ioDispatcher) {
            context.deleteFile(fileName)
        }
    }

}