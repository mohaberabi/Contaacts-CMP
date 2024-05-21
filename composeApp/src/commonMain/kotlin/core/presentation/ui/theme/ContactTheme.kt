package core.presentation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable

expect fun ContactTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit,
)