package kz3r.ppfresetada.spotify.gateway.auth.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

/**
 * Custom token for Redis caching
 */
@RedisHash("SpotifyToken")
data class TokenCache (
    @Id
    val clientId: String,
    val token: String
)