package kz3r.ppfresetada.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonNaming
import kz3r.ppfresetada.model.deserialization.TrackDeserializer
import java.time.OffsetDateTime
import javax.persistence.Id

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = TrackDeserializer::class)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class TrackDTO (
    @Id
    val id: String,
    val name: String,
    val albumId: String,
    val albumName: String,
    val artistId: String,
    val artistName: String,
    val duration: Long,
    val explicit:  Boolean,
    val addedBy: String,
    val addedAt: OffsetDateTime,
) {
    override fun toString(): String {
        return "${name}[$albumName] - $artistName"
    }
}