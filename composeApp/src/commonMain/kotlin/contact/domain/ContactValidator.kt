package contact.domain

object ContactValidator {


    fun validateContact(contact: ContactModel): ValidateResult {
        var result = ValidateResult()
        val dummyError = "This field can not be empty "
        if (contact.firstName.isBlank()) {
            result = result.copy(
                firstNameError = dummyError,
            )
        }
        if (contact.lastname.isBlank()) {
            result = result.copy(
                lastNameError = dummyError,
            )
        }
        if (contact.email.isBlank()) {
            result = result.copy(
                emailError = dummyError,
            )
        }
        if (contact.phone.isBlank()) {
            result = result.copy(
                phoneNumberError = dummyError,
            )
        }
        return result
    }


    data class ValidateResult(
        val firstNameError: String? = null,
        val lastNameError: String? = null,
        val emailError: String? = null,
        val phoneNumberError: String? = null,
    )
}