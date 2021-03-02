package kz3r.ppfresetada.spotify.gateway.auth

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kz3r.ppfresetada.spotify.SPOTIFY_ACCOUNTS_HOST
import kz3r.ppfresetada.spotify.SpotifyProperties
import kz3r.ppfresetada.spotify.gateway.auth.model.Token
import kz3r.ppfresetada.spotify.gateway.auth.model.TokenCache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*

@Component
class AuthGateway (
    private val spotifyProperties: SpotifyProperties,
    private val tokenRepository: TokenRepository
) {
    private val mapper = jacksonObjectMapper()
    private val okHttpClient = OkHttpClient()
    private val authGrantTypeProperty = "grant_type=client_credentials"

    fun requestNewToken(): Token {
        val request = Request.Builder()
                .url("$SPOTIFY_ACCOUNTS_HOST/api/token")
                .addHeader("Authorization", "Basic ${buildClientCredentials()}")
                .post(authGrantTypeProperty.toRequestBody("application/x-www-form-urlencoded".toMediaType()))
                .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return mapper.readValue<Token>(response.body!!.string())
                .also { updateCache(it) }
        }
    }

    private fun buildClientCredentials(): String {
        val clientCredentialBytes = "${spotifyProperties.clientId}:${spotifyProperties.clientSecret}".toByteArray()

        return Base64.getEncoder()
            .encode(clientCredentialBytes)
            .decodeToString()
    }

    private fun updateCache(token: Token) {
        TokenCache(spotifyProperties.clientId, token.accessToken)
            .let {
                kotlin.runCatching {
                    tokenRepository.save(it)
                }.onSuccess{
                    //Log DEBUG cache updated
                }.onFailure {
                    //LOG ERROR fail to save cache
                }
            }
    }
}