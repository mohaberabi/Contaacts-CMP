package core.presentation.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import contact.domain.ContactModel
import contact.presentation.compose.ContactPhoto
import contact.presentation.viewmodel.ContactEvent
import contact.presentation.viewmodel.ContactListState


@Composable
fun AddContactSheet(
    modifier: Modifier = Modifier,
    newContact: ContactModel?,
    isOpen: Boolean,
    onEvent: (ContactEvent) -> Unit,
    state: ContactListState,
) {


    ContactSheet(
        visible = isOpen
    ) {

        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart,
        ) {
            IconButton(
                onClick = {
                    onEvent(ContactEvent.DismissContact)
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "close"
                )
            }
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(16.dp))


                if (newContact?.photoByte != null) {
                    Box(
                        modifier = Modifier.size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .clickable { onEvent(ContactEvent.OnAddPhotoClicked) }
                            .border(1.dp, MaterialTheme.colorScheme.onSecondaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add image",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    ContactPhoto(
                        modifier = Modifier.size(150.dp)
                            .clickable {
                                onEvent(ContactEvent.OnAddPhotoClicked)
                            },
                        contact = newContact
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                ContactTextField(
                    value = newContact?.firstName ?: "",
                    onChanged = {
                        onEvent(ContactEvent.FirstNameChanged(it))
                    },
                    hint = "Mohab",
                    error = state.firstNameError,
                    label = "First name"
                )
                Spacer(modifier = Modifier.height(16.dp))


                ContactTextField(
                    value = newContact?.lastname ?: "",
                    onChanged = {
                        onEvent(ContactEvent.LastNameChanged(it))
                    },
                    hint = "Erabi",
                    error = state.lastNameError,
                    label = "Last name"
                )
                Spacer(modifier = Modifier.height(16.dp))

                ContactTextField(
                    value = newContact?.email ?: "",
                    onChanged = {
                        onEvent(ContactEvent.EmailChanged(it))
                    },
                    hint = "loser@yahoo.com",
                    error = state.emailError,
                    label = "Email"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContactTextField(
                    value = newContact?.phone ?: "",
                    onChanged = {
                        onEvent(ContactEvent.PhoneChanged(it))
                    },
                    hint = "010101010",
                    error = state.phoneError,
                    label = "Phone"
                )



                Button(
                    onClick = { onEvent(ContactEvent.OnSaveContact) },
                ) {
                    Text("Save")
                }

            }


        }
    }
}
