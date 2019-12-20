package com.anesabml.quotey

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.anesabml.quotey.core.data.IQuoteDataSource
import com.anesabml.quotey.core.data.IQuoteRepository
import com.anesabml.quotey.core.data.QuoteRepository
import com.anesabml.quotey.core.interactors.*
import com.anesabml.quotey.framework.Interactors
import com.anesabml.quotey.framework.data.RemoteLocalQuoteDataSource
import com.anesabml.quotey.ui.favorites.FavoriteQuotesViewModel
import com.anesabml.quotey.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupDI()

        setupSharedPreferenceListener()
    }

    private fun setupDI() {
        val myModule = module {
            single<IQuoteDataSource> {
                RemoteLocalQuoteDataSource(
                    this@MyApp
                )
            }
            single<IQuoteRepository> { QuoteRepository(get()) }

            single {
                Interactors(
                    GetRandomQuote(get()),
                    AddQuote(get()),
                    AddQuoteToFavorite(get()),
                    RemoveQuoteFromFavorite(get()),
                    GetFavoritesQuotes(get()),
                    GetAllQuotes(get())
                )
            }

            viewModel { MainViewModel(get()) }
            viewModel { FavoriteQuotesViewModel(get()) }
        }

        startKoin {
            // declare used Android context
            androidContext(this@MyApp)
            // declare modules
            modules(myModule)
        }
    }

    private fun setupSharedPreferenceListener() {
        val defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val listener =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                if (key == "theme") {
                    when (sharedPreferences.getString("theme", "light")) {
                        "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }

                }
            }
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }
}