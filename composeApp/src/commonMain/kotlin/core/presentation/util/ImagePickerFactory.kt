package core.presentation.util

import androidx.compose.runtime.Composable

expect class ImagePickerFactory {


    @Composable
    fun createPicker(): ImagePicker

}