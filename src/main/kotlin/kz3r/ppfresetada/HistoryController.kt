package kz3r.ppfresetada

import kz3r.ppfresetada.model.Track
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoryController(
    private val historyService: HistoryService
) {

    @GetMapping("/history")
    fun history(@PageableDefault(value = 100, page = 0) pageable: Pageable): ResponseEntity<Page<Track>> {
       return ResponseEntity.ok(historyService.trackHistory(pageable))
    }
}