package com.pi.recommendingmenu.di

import com.pi.recommendingmenu.core.data.networking.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create(CIO.create()) }

    // TODO:
    //  3. Implement the Data Layer: RemoteDataSource and DataSource
    //  4. Implement the DI module configuration

}