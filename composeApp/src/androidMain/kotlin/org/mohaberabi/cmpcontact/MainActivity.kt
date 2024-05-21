package org.mohaberabi.cmpcontact

import ContactApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import core.presentation.util.ImagePickerFactory

class MainActivity : ComponentActivity() {
    val imagePickerFactory = ImagePickerFactory()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ContactApp(
                dynamicColor = false,
                darkTheme = isSystemInDarkTheme(),
                imagePicker = imagePickerFactory.createPicker()
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {

}