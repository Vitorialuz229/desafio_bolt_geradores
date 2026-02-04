package com.github.vitorialuz229.desafio.ralie.repository

import org.postgresql.copy.CopyManager
import org.postgresql.core.BaseConnection
import org.springframework.stereotype.Repository
import java.io.File
import java.io.FileInputStream
import javax.sql.DataSource

@Repository
class RalieCopyRepository(private val dataSource: DataSource) {

    fun copyFromCsvFile(file: File) {
        dataSource.connection.use { conn ->
            val pgConn = conn.unwrap(BaseConnection::class.java)
            val copyManager = CopyManager(pgConn)

            conn.autoCommit = false

            conn.createStatement().use { stmt ->
                stmt.execute("""
                    CREATE TEMP TABLE ralie_temp (
                        codigo_usina BIGINT,
                        data_publicacao DATE,
                        nome_usina VARCHAR(255),
                        uf VARCHAR(2),
                        tipo_geracao VARCHAR(100),
                        potencia_outorgada_kw NUMERIC,
                        data_prevista_operacao DATE,
                        data_realizada_operacao DATE
                    ) ON COMMIT DROP;
                """.trimIndent())
            }

            // Preencher tabela temporária com COPY
            FileInputStream(file).use { input ->
                val copySql = """
                    COPY ralie_temp (
                        codigo_usina,
                        data_publicacao,
                        nome_usina,
                        uf,
                        tipo_geracao,
                        potencia_outorgada_kw,
                        data_prevista_operacao,
                        data_realizada_operacao
                    )
                    FROM STDIN WITH (FORMAT csv, DELIMITER ';', NULL '', HEADER false)
                """.trimIndent()
                copyManager.copyIn(copySql, input)
            }

            // Inserir na tabela principal, ignorando duplicados
            conn.createStatement().use { stmt ->
                stmt.execute("""
                    INSERT INTO ralie_usina (
                        codigo_usina,
                        data_publicacao,
                        nome_usina,
                        uf,
                        tipo_geracao,
                        potencia_outorgada_kw,
                        data_prevista_operacao,
                        data_realizada_operacao
                    )
                    SELECT * FROM ralie_temp
                    ON CONFLICT (codigo_usina, data_publicacao) DO NOTHING;
                """.trimIndent())
            }

            conn.commit()
            println("COPY concluído com sucesso via tabela temporária")
        }
    }
}