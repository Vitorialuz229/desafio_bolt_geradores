package com.github.vitorialuz229.desafio.ralie.util

import reactor.core.publisher.Flux
import java.io.File
import java.nio.file.Files

object DataBufferLineUtil {

    fun File.toFluxUtf8(): Flux<String> {
        return Flux.using(
            { Files.newBufferedReader(this.toPath(), Charsets.ISO_8859_1) },
            { reader -> Flux.fromStream(reader.lines()) },
            { reader -> reader.close() }
        )
    }
}