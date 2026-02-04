package com.github.vitorialuz229.desafio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class BoltGeradoresApplication

fun main(args: Array<String>) {
	runApplication<BoltGeradoresApplication>(*args)
}
