package pl.daniel.tokenprovider.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.daniel.tokenprovider.TokenProviderService
import pl.daniel.tokenprovider.TokenProviderServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TokenProviderModule {

    @Provides
    @Singleton
    fun bindTokenProvider(): TokenProviderService = TokenProviderServiceImpl()
}
