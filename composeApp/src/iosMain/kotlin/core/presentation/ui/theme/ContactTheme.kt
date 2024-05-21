package core.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
actual fun ContactTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = if (darkTheme) lightScheme else darkScheme,
        content = content
    )

}