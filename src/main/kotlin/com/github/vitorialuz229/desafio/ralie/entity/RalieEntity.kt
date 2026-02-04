package com.github.vitorialuz229.desafio.ralie.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(
    name = "ralie_usina",
    uniqueConstraints = [UniqueConstraint(columnNames = ["codigo_usina", "data_publicacao"])]
)
class RalieEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "codigo_usina", nullable = false)
    val codigoUsina: Long,

    @Column(name = "nome_usina", nullable = false)
    val nomeUsina: String,

    @Column(name = "uf", columnDefinition = "CHAR(2)")
    val uf: String? = null,

    @Column(name = "tipo_geracao")
    val tipoGeracao: String? = null,

    @Column(name = "potencia_outorgada_kw", precision = 18, scale = 4)
    val potenciaOutorgada: BigDecimal? = null,

    @Column(name = "data_publicacao")
    val dataPublicacao: LocalDate? = null,

    // Cronograma
    @Column(name = "data_prevista_operacao")
    val dataPrevistaOperacao: LocalDate? = null,

    @Column(name = "data_realizada_operacao")
    val dataRealizadaOperacao: LocalDate? = null,

    // Metadata
    @Column(name = "data_ingestao", nullable = false)
    val dataIngestao: LocalDateTime = LocalDateTime.now()
)
