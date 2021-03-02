package kz3r.ppfresetada.spotify.gateway.auth.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

/**
 * Token object return by Spotify Accounts API
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Token (
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)