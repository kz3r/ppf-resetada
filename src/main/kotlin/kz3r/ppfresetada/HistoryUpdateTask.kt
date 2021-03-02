package kz3r.ppfresetada

import org.slf4j.Logger
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class HistoryUpdateTask (
    private val historyService: HistoryService,
    private val logger: Logger
) {


    @Scheduled(fixedRateString = "\${spotify.historyUpdate:21600000}")
    fun historyUpdate() {
        kotlin.runCatching {
            historyService.updateHistory()
        }.onSuccess {
            logger.info("Playlist track history is up to date!")
        }.onFailure {
            logger.error("Unable to update playlist track history...", it)
        }
    }
}