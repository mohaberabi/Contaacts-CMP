package contact.presentation.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import contact.domain.ContactModel


@Composable
fun ContactItem(
    modifier: Modifier = Modifier, contact: ContactModel,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ContactPhoto(
            modifier = Modifier.size(40.dp),
            contact = contact,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "${contact.firstName} ${contact.lastname}",
            modifier = Modifier.weight(1f)
        )
    }
}