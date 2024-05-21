package di

import app.cash.sqldelight.db.SqlDriver
import com.mohaberabi.cmpcontact.database.ContactDatabase
import contact.data.SqlDelightContactDataSource
import contact.domain.ContactDataSource
import core.data.DatabaseDriverFactory
import core.data.ImageStorage
import core.util.ContactCoroutineDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appModule: Module = module {


    single<ContactCoroutineDispatcher> {
        ContactCoroutineDispatcher()
    }
    single<ImageStorage> { ImageStorage(get()) }

    single<SqlDriver> { DatabaseDriverFactory().create() }
    single { ContactDatabase(get()) }
    single<ContactDataSource> {
        SqlDelightContactDataSource(get(), get(), get(), get())
    }

}