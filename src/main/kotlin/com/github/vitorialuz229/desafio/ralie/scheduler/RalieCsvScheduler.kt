package com.github.vitorialuz229.desafio.ralie.scheduler

import com.github.vitorialuz229.desafio.ralie.service.RalieService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class RalieCsvScheduler(
    private val downloadService: RalieService
) {

    @Scheduled(cron = "\${scheduler.ralie-cron}")
    fun downloadCsv() {
        downloadService.ingest()
            .doOnSuccess {
                println("RALIE CSV ingerido com sucesso")
            }
            .doOnError { error ->
                println("Erro ao baixar/processar RALIE: ${error.message}")
            }
            .subscribe()
    }
}