package di

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module


actual class KoinInit(
    private val context: Context,
    private val appScope: CoroutineScope,
) {


    val appCoroutineScopeModule = module {
        single<CoroutineScope> {
            appScope
        }
    }

    actual fun init() {
        startKoin {
            androidContext(context)
            androidLogger()
            modules(
                appCoroutineScopeModule,
                viewModelModule,
                appModule
            )
        }
    }
}