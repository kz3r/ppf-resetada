package kz3r.ppfresetada.model.deserialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import kz3r.ppfresetada.model.dto.TrackDTO
import java.time.OffsetDateTime

class TrackDeserializer : StdDeserializer<TrackDTO> {

    constructor(vc: Class<*>?) : super(vc)

    constructor() : super(TrackDTO::class.java)

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): TrackDTO {
        requireNotNull(p)

        val itemNode: JsonNode = p.codec.readTree(p)
        val trackNode = itemNode.get("track")
        val albumNode = trackNode.get("album")
        val artistNode = trackNode.get("artists")!!.get(0)


        return TrackDTO(
            id = trackNode.get("id").textValue(),
            name = trackNode.get("name").textValue(),
            artistId = artistNode.get("id").textValue(),
            artistName = artistNode.get("name").textValue(),
            albumId = albumNode.get("id").textValue(),
            albumName = albumNode.get("name").textValue(),
            duration = trackNode.get("duration_ms").longValue(),
            explicit = trackNode.get("explicit").booleanValue(),
            addedBy = itemNode.get("added_by")!!.get("id").textValue(),
            addedAt = OffsetDateTime.parse(itemNode.get("added_at").textValue())
        )

    }

}