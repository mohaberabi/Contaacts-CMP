package contact.data

import com.mohaberabi.cmpcontact.database.ContactEntity
import contact.domain.ContactModel


fun ContactEntity.toContact(
    photoBytes: ByteArray?
): ContactModel {
    return ContactModel(
        id = id,
        phone = phone,
        email = email,
        firstName = firstName,
        lastname = lastName,
        photoByte = photoBytes,
    )
}
//fun ContactModel.toContactEntity(): ContactModel {
//    return ContactModel(
//        id = id,
//        phone = phone,
//        email = email,
//        firstName = firstName,
//        lastname = lastName,
//        photoByte = null,
//    )
//}