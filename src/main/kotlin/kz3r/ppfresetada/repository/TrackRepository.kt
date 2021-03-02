package kz3r.ppfresetada.repository

import kz3r.ppfresetada.model.Track
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackRepository : JpaRepository<Track, String> {

    fun findByPlaylistIdOrderByAddedAtDesc(playlistId: String, pageable: Pageable): Page<Track>
}