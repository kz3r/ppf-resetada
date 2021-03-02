package kz3r.ppfresetada.spotify

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kz3r.ppfresetada.model.dto.PlaylistDTO
import kz3r.ppfresetada.spotify.gateway.SpotifyGateway
import org.springframework.stereotype.Service

@Service
class SpotifyService(
    private val spotifyGateway: SpotifyGateway
) {
    val objectMapper = jacksonObjectMapper()

    fun playlistById(id: String): PlaylistDTO {
        return spotifyGateway.apiRequest("/playlists/$id").let {
            objectMapper.readValue(it)
        }
    }
}