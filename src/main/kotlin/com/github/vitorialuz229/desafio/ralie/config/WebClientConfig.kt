package com.github.vitorialuz229.desafio.ralie.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { it.defaultCodecs().maxInMemorySize(300 * 1024 * 1024) } // 300 MB
            .build()

        return WebClient.builder()
            .exchangeStrategies(exchangeStrategies)
    }
}