package kz3r.ppfresetada.spotify.gateway.auth

import kz3r.ppfresetada.spotify.gateway.auth.extensions.RequestAuthorization
import okhttp3.Interceptor
import okhttp3.Response
import org.springframework.stereotype.Component

/**
 * Interceptor for injecting our custom Authentication Token the Spotify API requests
 */
@Component
class TokenInterceptor (
    authService: AuthService
) : Interceptor, RequestAuthorization(authService) {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().authorizeRequest().let {
            chain.proceed(it)
        }
    }
}