package contact.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import contact.domain.ContactModel
import core.presentation.util.rememberBitmapFromBytes


@Composable
fun ContactPhoto(
    modifier: Modifier = Modifier,
    contact: ContactModel?,
    iconSize: Dp = 25.dp,
) {

    val bitmap = rememberBitmapFromBytes(contact?.photoByte)
    val photoModifier = modifier.clip(RoundedCornerShape(35))

    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            modifier = photoModifier,
            contentDescription = "contact image"
        )
    } else {
        Box(
            modifier = photoModifier.background(MaterialTheme.colors.secondaryVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Person, contentDescription = "contact icon",
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}