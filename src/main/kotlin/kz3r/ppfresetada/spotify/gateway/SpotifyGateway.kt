package kz3r.ppfresetada.spotify.gateway

import kz3r.ppfresetada.spotify.SPOTIFY_API_HOST
import kz3r.ppfresetada.spotify.SPOTIFY_API_VERSION
import kz3r.ppfresetada.spotify.gateway.auth.TokenAuthenticator
import kz3r.ppfresetada.spotify.gateway.auth.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component
import java.io.IOException

/**
 * Class responsible for Spotify API external requests.
 */
@Component
class SpotifyGateway(
    tokenInterceptor: TokenInterceptor,
    tokenAuthenticator: TokenAuthenticator
) {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(tokenInterceptor)
        .authenticator(tokenAuthenticator)
        .build()


    fun apiRequest(resourcePath: String): String = Request.Builder()
        .url(buildApiUrl(resourcePath))
        .build().let { executeApiRequest(it) }


    private fun buildApiUrl(resourcePath: String): String = StringBuilder()
        .append(SPOTIFY_API_HOST)
        .append("/$SPOTIFY_API_VERSION")
        .append(resourcePath)
        .toString()

    private fun executeApiRequest(request: Request): String {
        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return response.body!!.string()
        }
    }



}