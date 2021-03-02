package kz3r.ppfresetada.spotify

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spotify")
data class SpotifyProperties (
    val clientId: String,
    val clientSecret: String,
    val playlistId: String
)