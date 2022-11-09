package com.kmm.weather

import com.kmm.weather.di.KodeinInjector
import com.kmm.weather.presentation.HomePageInteractor
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object InteractorProvider {
    val homePageInteractor by KodeinInjector.instance<HomePageInteractor>()
}
