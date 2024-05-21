package contact.presentation.viewmodel

import contact.domain.ContactModel

sealed interface ContactEvent {
    data object OnAddNewContact : ContactEvent
    data object DismissContact : ContactEvent
    data class FirstNameChanged(val value: String) : ContactEvent
    data class LastNameChanged(val value: String) : ContactEvent
    data class EmailChanged(val value: String) : ContactEvent
    data class PhoneChanged(val value: String) : ContactEvent
    class OnPhotoPicked(val bytes: ByteArray) : ContactEvent
    data object OnAddPhotoClicked : ContactEvent
    data class OnSelectContact(val contact: ContactModel) : ContactEvent
    data class OnEditContact(val contact: ContactModel) : ContactEvent
    data object OnDeleteContact : ContactEvent
    data object OnSaveContact : ContactEvent

}