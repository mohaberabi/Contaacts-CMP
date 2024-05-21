package contact.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import contact.domain.ContactDataSource
import contact.domain.ContactModel
import contact.domain.ContactValidator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ContactViewModel(
    private val contactDataSource: ContactDataSource,
) : ViewModel() {


    var newContact: ContactModel? by mutableStateOf(null)
        private set
    private val _state = MutableStateFlow(ContactListState())

    val state: StateFlow<ContactListState> =
        combine(
            _state,
            contactDataSource.getContacts(),
            contactDataSource.getRecentContacts(20)
        ) { state, all, recent ->
            state.copy(
                contacts = all,
                recentAddedContacts = recent,
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5.seconds.inWholeMilliseconds),
            ContactListState()
        )


    fun onEvent(event: ContactEvent) {
        when (event) {
            ContactEvent.OnSaveContact -> saveContact(newContact)
            ContactEvent.OnAddNewContact -> addNewContact()
            ContactEvent.OnAddPhotoClicked -> Unit
            ContactEvent.OnDeleteContact -> deleteContact(_state.value.selectedContact)
            ContactEvent.DismissContact -> dismissContactSheet()
            is ContactEvent.EmailChanged -> newContact = newContact?.copy(email = event.value)
            is ContactEvent.FirstNameChanged -> newContact =
                newContact?.copy(firstName = event.value)

            is ContactEvent.LastNameChanged -> newContact = newContact?.copy(lastname = event.value)
            is ContactEvent.OnEditContact -> editContact(event.contact)
            is ContactEvent.OnPhotoPicked -> newContact = newContact?.copy(photoByte = event.bytes)
            is ContactEvent.OnSelectContact -> selectContact(event.contact)
            is ContactEvent.PhoneChanged -> newContact = newContact?.copy(phone = event.value)
        }
    }


    private fun saveContact(contact: ContactModel?) {
        contact?.let { cont ->
            val result = ContactValidator.validateContact(cont)
            val errors = listOfNotNull(
                result.emailError,
                result.firstNameError,
                result.lastNameError,
                result.phoneNumberError
            )
            if (errors.isEmpty()) {

                _state.update {
                    it.copy(
                        isAddContactSheetOpen = false,
                        lastNameError = null,
                        phoneError = null,
                        firstNameError = null,
                        emailError = null,
                    )
                }
                viewModelScope.launch {
                    contactDataSource.insertContact(cont)
                    delay(300L)
                    newContact = null
                }
            } else {
                _state.update {
                    it.copy(
                        firstNameError = result.firstNameError,
                        lastNameError = result.lastNameError,
                        phoneError = result.phoneNumberError,
                        emailError = result.emailError
                    )
                }
            }
        }
    }

    private fun selectContact(contact: ContactModel) {
        _state.update { it.copy(selectedContact = contact, isAddContactSheetOpen = true) }
    }

    private fun addNewContact() {
        _state.update {
            it.copy(
                isAddContactSheetOpen = true
            )
        }
        newContact = ContactModel(
            null,
            "",
            "",
            "",
            "",
            null,
        )
    }

    private fun editContact(contact: ContactModel) {
        _state.update {
            it.copy(
                isSelectedContactSheetOpen = false,
                isAddContactSheetOpen = true,
                selectedContact = contact
            )
        }
    }

    private fun dismissContactSheet() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isSelectedContactSheetOpen = false,
                    isAddContactSheetOpen = false,
                    emailError = null,
                    firstNameError = null,
                    phoneError = null,
                    lastNameError = null,
                )
            }
            delay(300L)
            _state.update { it.copy(selectedContact = null) }
        }
    }

    private fun deleteContact(contact: ContactModel?) {
        contact?.id?.let {
            _state.update { it.copy(isSelectedContactSheetOpen = false) }
            viewModelScope.launch {
                contactDataSource.deleteContact(it)
                delay(300L)
            }

            _state.update { it.copy(selectedContact = null) }

        }

    }
}