package kz3r.ppfresetada.model

import kz3r.ppfresetada.model.dto.PlaylistDTO
import javax.persistence.*

@Entity
@Table(name = "playlist")
data class Playlist (

    /**
     * Spotify's playlist ID
     */
    @Id
    val id: String,

    /**
     * URL for directly access the playlist
     */
    val href: String? = "",

    /**
     * Full playlist name (description not currently supported)
     */
    val name: String,

    @OneToMany(mappedBy = "playlist", cascade = [CascadeType.ALL])
    val tracks: List<Track>
) {
    object ModelMapper {
        fun fromDTO(dto: PlaylistDTO): Playlist {


            val p = Playlist(
                id = dto.id,
                href = dto.href,
                name = dto.name,
                tracks = listOf()
            )

            val trackList = dto.tracks.map { t ->
                Track.ModelMapper.fromDTO(t).apply { this.playlist = p }
            }.toList()

            return p.copy(tracks = trackList)
        }
    }
}