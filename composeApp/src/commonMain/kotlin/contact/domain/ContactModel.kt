package contact.domain

data class ContactModel(
    val id: Long?,
    val firstName: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val photoByte: ByteArray?
) {

}
