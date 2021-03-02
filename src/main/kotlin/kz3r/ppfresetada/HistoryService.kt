package kz3r.ppfresetada

import kz3r.ppfresetada.model.Playlist
import kz3r.ppfresetada.model.Track
import kz3r.ppfresetada.repository.PlaylistRepository
import kz3r.ppfresetada.repository.TrackRepository
import kz3r.ppfresetada.spotify.SpotifyProperties
import kz3r.ppfresetada.spotify.SpotifyService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class HistoryService(
    private val spotifyService: SpotifyService,
    private val spotifyProperties: SpotifyProperties,
    private val trackRepository: TrackRepository,
    private val playlistRepository: PlaylistRepository
) {

    /**
     * @see [[trackHistory]]
     */
    fun trackHistory(pageable: Pageable): Page<Track> = trackHistory(spotifyProperties.playlistId, pageable)

    /**
     * Returns a (paged) list of songs in the specified playlist
     */
    fun trackHistory(playlistId: String, pageable: Pageable): Page<Track> {
        return trackRepository.findByPlaylistIdOrderByAddedAtDesc(playlistId, pageable)
    }

    /**
     * Get a playlist from spotify and append new tracks to the database
     */
    fun updateHistory() {
        spotifyService.playlistById(spotifyProperties.playlistId).let {
            playlistRepository.save(Playlist.ModelMapper.fromDTO(it))
        }
    }
}