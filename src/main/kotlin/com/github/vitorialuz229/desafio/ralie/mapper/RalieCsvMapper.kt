package com.github.vitorialuz229.desafio.ralie.mapper

import com.github.vitorialuz229.desafio.ralie.entity.RalieEntity
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object RalieCsvMapper {

    private val dateFormatterCsv = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val dateFormatterIso = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private fun parseDateSafe(value: String?): LocalDate? {
        if (value.isNullOrBlank() || value == "0") return null
        return try {
            LocalDate.parse(value, dateFormatterCsv)
        } catch (e: DateTimeParseException) {
            try {
                LocalDate.parse(value, dateFormatterIso)
            } catch (e2: DateTimeParseException) {
                null
            }
        }
    }

    private fun parseBigDecimalSafe(value: String?): BigDecimal? {
        if (value.isNullOrBlank() || value == "0") return null
        return try {
            value.replace(",", ".").toBigDecimal()
        } catch (e: NumberFormatException) {
            null
        }
    }

    private fun parseStringSafe(value: String?, maxLength: Int? = null): String? {
        if (value.isNullOrBlank() || value == "0") return null
        val trimmed = value.trim()
        return if (maxLength != null) trimmed.take(maxLength) else trimmed
    }

    fun parse(line: String): RalieEntity? {
        val columns = line.split(';')

        if (columns.isEmpty() || columns[0] == "DatGeracaoConjuntoDados") return null
        val codigoUsina = columns.getOrNull(2)?.toLongOrNull() ?: 0L
        if (codigoUsina == 0L) return null

        return RalieEntity(
            codigoUsina = columns.getOrNull(2)?.toLongOrNull() ?: 0L,
            nomeUsina = parseStringSafe(columns.getOrNull(7), 255) ?: "USINA_DESCONHECIDA",
            uf = parseStringSafe(columns.getOrNull(4), 2),
            tipoGeracao = parseStringSafe(columns.getOrNull(6), 100),
            potenciaOutorgada = parseBigDecimalSafe(columns.getOrNull(8)),
            dataPublicacao = parseDateSafe(columns.getOrNull(0)),
            dataPrevistaOperacao = parseDateSafe(columns.getOrNull(17)),
            dataRealizadaOperacao = parseDateSafe(columns.getOrNull(18))
        )
    }
}