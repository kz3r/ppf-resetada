package kz3r.ppfresetada.spotify.gateway.auth

import kz3r.ppfresetada.spotify.SpotifyProperties
import kz3r.ppfresetada.spotify.gateway.auth.exception.TokenNotFoundException
import kz3r.ppfresetada.spotify.gateway.auth.model.TokenCache
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val spotifyProperties: SpotifyProperties,
    private val tokenRepository: TokenRepository,
    private val authGateway: AuthGateway
) {

    fun getCachedToken(): TokenCache = tokenRepository.findById(spotifyProperties.clientId).orElseThrow {
            TokenNotFoundException()
        }

    fun getToken(): String = kotlin.runCatching {
        getCachedToken().token
    }.getOrDefault(authGateway.requestNewToken().accessToken)
}