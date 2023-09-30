package pl.daniel.datastore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.daniel.datastore.PreferencesDataStoreService
import pl.daniel.datastore.PreferencesDataStoreServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesDataStoreModule {

    @Binds
    @Singleton
    fun bindsPreferencesDataStore(referencesDataStoreServiceImpl: PreferencesDataStoreServiceImpl): PreferencesDataStoreService

}
