package contact.domain

import kotlinx.coroutines.flow.Flow

interface ContactDataSource {


    fun getContacts(): Flow<List<ContactModel>>
    fun getRecentContacts(amount: Int): Flow<List<ContactModel>>
    suspend fun insertContact(contact: ContactModel)
    suspend fun deleteContact(id: Long)

}