package com.github.vitorialuz229.desafio.ralie.controller

import com.github.vitorialuz229.desafio.ralie.entity.RalieEntity
import com.github.vitorialuz229.desafio.ralie.service.RalieService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@RestController
@RequestMapping("/ralie")
class RalieController(private val ralieService: RalieService) {

    @GetMapping("/top-5-produtores")
    fun buscarTop5MaioresProdutores(): List<RalieEntity> {
        return ralieService.buscarTop5MaioresProdutores()
    }
}