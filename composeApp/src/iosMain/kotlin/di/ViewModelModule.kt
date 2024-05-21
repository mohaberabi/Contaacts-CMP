package di

import contact.presentation.viewmodel.ContactViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual val viewModelModule: Module = module {

    singleOf(::ContactViewModel)
}