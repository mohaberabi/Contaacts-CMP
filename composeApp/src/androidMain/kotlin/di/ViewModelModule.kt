package di

import contact.presentation.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module = module {


    viewModelOf(::ContactViewModel)
}