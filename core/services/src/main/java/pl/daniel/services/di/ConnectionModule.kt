package pl.daniel.services.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.daniel.tokenprovider.TokenAuthenticator
import pl.daniel.tokenprovider.TokenProviderService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConnectionModule {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @Provides
    @Singleton
    fun bindRetrofit(
        tokenProviderService: TokenProviderService
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(okHttpClient(tokenProviderService))
            .build()

    private fun okHttpClient(
        authorizationService: TokenProviderService
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .authenticator(TokenAuthenticator(authorizationService))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}