package pl.daniel.tokenprovider

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenProviderService: TokenProviderService
) : Authenticator {
    companion object {
        const val AUTH_HEADER = "Authorization"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val retryCount = response.request.header("RetryCount")?.toInt() ?: 0
        if (retryCount > 2) {
            return null
        }
        return response.request.newBuilder()
            .header(AUTH_HEADER, "Bearer ${tokenProviderService.getToken()}")
            .header("RetryCount", "${retryCount + 1}")
            .build()
    }


}
