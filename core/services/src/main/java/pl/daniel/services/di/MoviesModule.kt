package pl.daniel.services.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.daniel.services.MoviesService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Provides
    @Singleton
    fun provideStripService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)
}