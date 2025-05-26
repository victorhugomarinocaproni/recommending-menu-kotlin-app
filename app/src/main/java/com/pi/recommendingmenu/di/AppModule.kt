package com.pi.recommendingmenu.di

import com.pi.recommendingmenu.core.data.networking.HttpClientFactory
import com.pi.recommendingmenu.recipes.presentation.item_selection.ItemSelectionViewModel
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create(CIO.create()) }

    viewModelOf(::ItemSelectionViewModel)
    viewModelOf(::RecipesViewModel)

    // TODO:
    //  3. Implement the Data Layer: RemoteDataSource and DataSource
    //  4. Implement the DI module configuration

}