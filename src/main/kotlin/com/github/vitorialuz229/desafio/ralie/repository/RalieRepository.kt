package com.github.vitorialuz229.desafio.ralie.repository

import com.github.vitorialuz229.desafio.ralie.entity.RalieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RalieRepository : JpaRepository<RalieEntity, Long> {

    fun findTop5ByPotenciaOutorgadaIsNotNullOrderByPotenciaOutorgadaDesc(): List<RalieEntity>
}