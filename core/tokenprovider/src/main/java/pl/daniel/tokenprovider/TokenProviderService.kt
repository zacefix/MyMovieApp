package pl.daniel.tokenprovider

interface TokenProviderService {
    fun getToken(): String?
}