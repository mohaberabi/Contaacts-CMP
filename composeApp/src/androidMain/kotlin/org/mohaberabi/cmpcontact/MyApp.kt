package org.mohaberabi.cmpcontact

import android.app.Application
import di.KoinInit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {


    private val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {
        super.onCreate()
        KoinInit(
            applicationContext,
            applicationScope,

            ).init()
    }
}