import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import core.presentation.util.ImagePickerFactory
import di.KoinInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController

fun MainViewController() = ComposeUIViewController(

    configure = {
        val appScope = CoroutineScope(SupervisorJob())
        KoinInit(appScope).init()
    }
) {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark

    ContactApp(
        dynamicColor = false,
        darkTheme = isDarkTheme,
        imagePicker = ImagePickerFactory(
            rootController = LocalUIViewController.current
        ).createPicker()
    )
}
