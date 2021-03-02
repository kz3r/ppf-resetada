package kz3r.ppfresetada.spotify.gateway.auth

import kz3r.ppfresetada.spotify.gateway.auth.model.TokenCache
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repository for Redis Token Cache
 */
@Repository
interface TokenRepository : CrudRepository<TokenCache, String>