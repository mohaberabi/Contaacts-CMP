package contact.presentation.viewmodel

import contact.domain.ContactModel

data class ContactListState(

    val contacts: List<ContactModel> = emptyList(),
    val recentAddedContacts: List<ContactModel> = emptyList(),
    val selectedContact: ContactModel? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val lastNameError: String? = null,
    val firstNameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
)
