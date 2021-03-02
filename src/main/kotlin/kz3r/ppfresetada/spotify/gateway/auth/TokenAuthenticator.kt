package kz3r.ppfresetada.spotify.gateway.auth

import kz3r.ppfresetada.spotify.gateway.auth.extensions.RequestAuthorization
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.springframework.stereotype.Component

/**
 * [[Authenticator]] for refreshing our custom Authentication Token the Spotify API requests in case of 401 responses
 */
@Component
class TokenAuthenticator (
    authService: AuthService
) : Authenticator, RequestAuthorization(authService) {
    override fun authenticate(route: Route?, response: Response): Request? {
        response.code
        return response.request.authorizeRequest()
    }
}