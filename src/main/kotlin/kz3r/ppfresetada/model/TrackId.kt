package kz3r.ppfresetada.model

import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.Embeddable

/**
 * IdClass for [Track]
 *
 * ps: IdClass requires default constructors, which are provided in this instance via @Embeddable notation
 */
@Embeddable
data class TrackId (
    val id: String,
    val addedAt: OffsetDateTime
) : Serializable