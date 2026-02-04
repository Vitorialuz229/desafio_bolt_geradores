package com.github.vitorialuz229.desafio.ralie.service

import com.github.vitorialuz229.desafio.ralie.client.RalieCsvHttpClient
import com.github.vitorialuz229.desafio.ralie.entity.RalieEntity
import com.github.vitorialuz229.desafio.ralie.mapper.RalieCsvMapper
import com.github.vitorialuz229.desafio.ralie.repository.RalieCopyRepository
import com.github.vitorialuz229.desafio.ralie.util.DataBufferLineUtil.toFluxUtf8
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.nio.file.Files

@Service
class RalieService(
    private val client: RalieCsvHttpClient,
    private val ralieCopyRepository: RalieCopyRepository
) {

    fun ingest(chunkSize: Int = 10000): Mono<Void> {
        return Mono.fromRunnable<Void> {
            val file = client.downloadCsvToFile()
            val chunkBuffer = mutableListOf<RalieEntity>()
            var chunkIndex = 0

            file.toFluxUtf8()
                .mapNotNull { RalieCsvMapper.parse(it) }
                .filter { it.codigoUsina != 0L && it.dataPublicacao != null }
                .doOnNext { entity ->
                    chunkBuffer.add(entity)
                    if (chunkBuffer.size >= chunkSize) {
                        saveChunk(chunkBuffer, chunkIndex)
                        chunkBuffer.clear()
                        chunkIndex++
                    }
                }
                .blockLast()

            if (chunkBuffer.isNotEmpty()) {
                saveChunk(chunkBuffer, chunkIndex)
            }

            file.delete()

            println("✔️ Ingestão RALIE finalizada. Total de chunks: ${chunkIndex + 1}")
        }.subscribeOn(Schedulers.boundedElastic())
    }

    private fun saveChunk(chunk: List<RalieEntity>, index: Int) {
        val tempFile = Files.createTempFile("ralie_chunk_${index}_", ".csv").toFile()
        tempFile.bufferedWriter(Charsets.UTF_8).use { writer ->
            chunk.forEach { entity ->
                val line = listOf(
                    entity.codigoUsina,
                    entity.dataPublicacao,
                    entity.nomeUsina,
                    entity.uf ?: "",
                    entity.tipoGeracao ?: "",
                    entity.potenciaOutorgada ?: "",
                    entity.dataPrevistaOperacao ?: "",
                    entity.dataRealizadaOperacao ?: ""
                ).joinToString(";")
                writer.write(line)
                writer.newLine()
            }
        }

        ralieCopyRepository.copyFromCsvFile(tempFile)
        println("COPY concluído para chunk $index: ${chunk.size} registros")
        tempFile.delete()
    }
}