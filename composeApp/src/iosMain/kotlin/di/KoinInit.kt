package di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.core.module.single
import org.koin.dsl.module

actual class KoinInit(
    private val appScope: CoroutineScope,
) {


    val applicationScopeModule = module {
        single<CoroutineScope> {
            appScope
        }
    }

    actual fun init() {
        startKoin {
            modules(
                applicationScopeModule,
                viewModelModule,
                appModule,
            )
        }
    }
}