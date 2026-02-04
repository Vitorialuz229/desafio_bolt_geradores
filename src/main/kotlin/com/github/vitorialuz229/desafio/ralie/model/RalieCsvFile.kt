package com.github.vitorialuz229.desafio.ralie.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class RalieCsvFile(

    @JsonProperty("DatGeracaoConjuntoDados")
    val datGeracaoConjuntoDados: String? = null,

    @JsonProperty("DatRalie")
    val datRalie: String? = null,

    @JsonProperty("IdeNucleoCEG")
    val ideNucleoCEG: String? = null,

    @JsonProperty("CodCEG")
    val codCEG: String? = null,

    @JsonProperty("SigUFPrincipal")
    val sigUFPrincipal: String? = null,

    @JsonProperty("DscOrigemCombustivel")
    val dscOrigemCombustivel: String? = null,

    @JsonProperty("SigTipoGeracao")
    val sigTipoGeracao: String? = null,

    @JsonProperty("NomEmpreendimento")
    val nomEmpreendimento: String? = null,

    @JsonProperty("MdaPotenciaOutorgadaKw")
    val mdaPotenciaOutorgadaKw: String? = null,

    @JsonProperty("DscSituacaoObra")
    val dscSituacaoObra: String? = null,
)