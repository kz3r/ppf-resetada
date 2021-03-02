package kz3r.ppfresetada.model

import com.fasterxml.jackson.annotation.JsonBackReference
import kz3r.ppfresetada.model.dto.TrackDTO
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@IdClass(TrackId::class)
@Table(name = "track_log")
data class Track (

    /**
     * Spotify's song ID
     */
    @Id
    val id: String,

    /**
     * When(timestamp) the song was added to the playlist.
     * ps: Combo with [[id]] for the primary key
     */
    @Id
    val addedAt: OffsetDateTime,

    /**
     * Spotify user ID that added the song to the playlist
     */
    val addedBy: String,

    /**
     * Full song name
     */
    val name: String,

    /**
     * Spotify's album ID
     */
    val albumId: String,

    /**
     * Full album name
     */
    val albumName: String,

    /**
     * Spotify's artist ID
     * ps: For the sake of simplicty, only the first artist in the artists list is parsed.
     * In theory, this should be de main artist for the given track.
     */
    val artistId: String,

    /**
     * Full artist name
     * ps: For the sake of simplicty, only the first artist in the artists list is parsed.
     * In theory, this should be de main artist for the given track.
     */
    val artistName: String,

    /**
     * Track duration in milliseconds
     */
    val duration: Long,

    /**
     * Contains explicit content
     */
    val explicit:  Boolean,

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    @JsonBackReference
    var playlist: Playlist? = null
) {
    object ModelMapper {
        fun fromDTO(dto: TrackDTO): Track {
            return Track(
                id = dto.id,
                name = dto.name,
                albumId = dto.albumId,
                albumName = dto.albumName,
                artistId = dto.artistId,
                artistName = dto.artistName,
                duration = dto.duration,
                explicit = dto.explicit,
                addedAt = dto.addedAt,
                addedBy = dto.addedBy
            )
        }
    }
}