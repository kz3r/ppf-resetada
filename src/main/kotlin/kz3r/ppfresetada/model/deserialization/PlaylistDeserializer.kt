package kz3r.ppfresetada.model.deserialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kz3r.ppfresetada.model.dto.PlaylistDTO
import kz3r.ppfresetada.model.dto.TrackDTO

class PlaylistDeserializer : StdDeserializer<PlaylistDTO> {

    constructor(vc: Class<*>?) : super(vc)

    constructor() : super(PlaylistDTO::class.java)

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): PlaylistDTO {

        requireNotNull(p)
        val mapper = jacksonObjectMapper()

        val playlistNode: JsonNode = p.codec.readTree(p)
        val trackItems = playlistNode.get("tracks")?.get("items")!!

        val playlist = PlaylistDTO(
            id = playlistNode.get("id").textValue(),
            href = playlistNode.get("href").textValue(),
            name = playlistNode.get("name").textValue(),
            tracks = mapper.readValue(trackItems.toString(), object : TypeReference<List<TrackDTO>>() {})
        )

        //playlist.tracks.forEach{ track -> track.playlist = playlist}

        return playlist
    }

}