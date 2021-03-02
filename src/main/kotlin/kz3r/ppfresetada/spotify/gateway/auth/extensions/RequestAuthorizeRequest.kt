package kz3r.ppfresetada.spotify.gateway.auth.extensions

import kz3r.ppfresetada.spotify.gateway.auth.AuthService
import okhttp3.Request

open class RequestAuthorization(
    private val authService: AuthService
) {
    fun Request.authorizeRequest(): Request {
        return authService.getToken().let { token ->
            newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }
    }
}