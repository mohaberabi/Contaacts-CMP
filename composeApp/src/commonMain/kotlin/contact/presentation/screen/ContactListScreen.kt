package contact.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import contact.domain.ContactModel
import contact.presentation.compose.ContactItem
import contact.presentation.viewmodel.ContactEvent
import contact.presentation.viewmodel.ContactListState
import contact.presentation.viewmodel.ContactViewModel
import core.presentation.ui.compose.AddContactSheet
import core.presentation.util.ImagePicker
import core.presentation.util.koinViewModel


@Composable
fun ContactListScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = koinViewModel(),
    imagePicker: ImagePicker
) {

    val state by viewModel.state.collectAsState()

    val newContact = viewModel.newContact
    ContactListScreen(
        state = state,
        newContact = newContact,
        onEvent = viewModel::onEvent,
        imagePicker = imagePicker
    )
}


@Composable
fun ContactListScreen(
    modifier: Modifier = Modifier,
    newContact: ContactModel?,
    state: ContactListState,
    onEvent: (ContactEvent) -> Unit,
    imagePicker: ImagePicker,
) {


    imagePicker.registerPicker { bytes ->
        onEvent(ContactEvent.OnPhotoPicked(bytes))
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactEvent.OnAddNewContact)
                },
                shape = RoundedCornerShape(20.dp)
            ) {

                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add contact ")
            }
        },
    ) { padding ->

        Box {
            LazyColumn(
                modifier = Modifier.padding(padding),
            ) {

                item {
                    Text(
                        "My Contacts (${state.contacts.size})",
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                    )
                }

                items(state.contacts) { contact ->
                    ContactItem(
                        modifier = Modifier.fillMaxWidth()
                            .clickable { onEvent(ContactEvent.OnEditContact(contact)) },
                        contact = contact
                    )
                }

            }
            AddContactSheet(
                state = state,
                modifier = Modifier.padding(padding),
                newContact = newContact,
                isOpen = state.isAddContactSheetOpen,
                onEvent = { event ->
                    if (event is ContactEvent.OnAddPhotoClicked) {
                        imagePicker.pickImage()
                    } else {
                        onEvent(event)
                    }
                }
            )
        }
    }


}