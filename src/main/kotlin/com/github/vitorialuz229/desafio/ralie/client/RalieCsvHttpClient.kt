package com.github.vitorialuz229.desafio.ralie.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.util.retry.Retry
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.time.Duration

@Component
class RalieCsvHttpClient(
    builder: WebClient.Builder
) {
    private val webClient = builder.build()

    fun downloadCsvToFile(): File {
        val tempFile = Files.createTempFile("ralie_", ".csv").toFile()

        val dataBufferFlux = webClient.get()
            .uri("https://dadosabertos.aneel.gov.br/dataset/57e4b8b5-a5db-40e6-9901-27ca629d0477/resource/4a615df8-4c25-48fa-bbea-873a36a79518/download/ralie-usina.csv")
            .retrieve()
            .bodyToFlux(DataBuffer::class.java)
            .retryWhen(
                Retry.backoff(Long.MAX_VALUE, Duration.ofSeconds(10))
                    .filter { it is WebClientResponseException }
                    .doBeforeRetry {
                        println("🔄 Falha ao baixar RALIE. Tentando novamente...")
                    }
            )

        DataBufferUtils.write(
            dataBufferFlux,
            tempFile.toPath(),
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        ).block()

        return tempFile
    }
}