package kz3r.ppfresetada.spotify.gateway.auth.exception

class TokenNotFoundException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
}