import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import contact.domain.ContactModel
import contact.presentation.screen.ContactListScreen
import contact.presentation.screen.ContactListScreenRoot
import contact.presentation.viewmodel.ContactListState
import core.presentation.ui.theme.ContactTheme
import core.presentation.util.ImagePicker
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ContactApp(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    imagePicker: ImagePicker,
) {
    ContactTheme(darkTheme, dynamicColor) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            ContactListScreenRoot(imagePicker = imagePicker)

        }
    }

}


