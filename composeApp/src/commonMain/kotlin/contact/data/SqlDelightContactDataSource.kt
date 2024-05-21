package contact.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.mohaberabi.cmpcontact.database.ContactDatabase
import com.mohaberabi.cmpcontact.database.ContactDatabaseQueries
import contact.domain.ContactDataSource
import contact.domain.ContactModel
import core.data.ImageStorage
import core.util.ContactCoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    private val appScope: CoroutineScope,
    private val database: ContactDatabase,
    private val coroutineDispatcher: ContactCoroutineDispatcher,
    private val imageStorage: ImageStorage,
) : ContactDataSource {


    private val queries = database.contactDatabaseQueries

    override fun getContacts(): Flow<List<ContactModel>> =
        queries
            .getContacts()
            .asFlow()
            .mapToList(coroutineDispatcher.ioDispatcher)
            .map { list ->
                list.map { contactEntity ->
                    val imageBytes = appScope.async {
                        contactEntity.imagePath?.let { path ->
                            imageStorage.getImage(path)
                        }
                    }.await()
                    contactEntity.toContact(imageBytes)
                }
            }

    override fun getRecentContacts(amount: Int): Flow<List<ContactModel>> =
        queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList(coroutineDispatcher.ioDispatcher)
            .map { list ->

                list.map { contactEntity ->
                    val imageBytes = appScope.async {
                        contactEntity.imagePath?.let { path ->
                            imageStorage.getImage(path)
                        }
                    }.await()
                    contactEntity.toContact(imageBytes)
                }
            }

    override suspend fun insertContact(contact: ContactModel) {
        val imagePath = contact.photoByte?.let {
            imageStorage.saveImage(it)
        }
        queries.insertContact(
            id = null,
            firstName = contact.firstName,
            lastName = contact.lastname,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = imagePath,
            phone = contact.phone
        )
    }


    override suspend fun deleteContact(id: Long) {
        val entity = queries.getContactById(id).executeAsOne()
        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }
        queries.deleteContact(id)
    }
}

