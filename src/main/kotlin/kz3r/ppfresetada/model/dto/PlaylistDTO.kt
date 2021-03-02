package kz3r.ppfresetada.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonNaming
import kz3r.ppfresetada.model.deserialization.PlaylistDeserializer

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = PlaylistDeserializer::class)
data class PlaylistDTO (
    val id: String,

    val href: String? = "",

    val name: String,

    val tracks: List<TrackDTO>
)