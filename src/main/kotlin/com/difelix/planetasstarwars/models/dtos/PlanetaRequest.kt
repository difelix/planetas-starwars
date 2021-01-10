package com.difelix.planetasstarwars.models.dtos

import com.difelix.planetasstarwars.models.entities.Planeta
import com.fasterxml.jackson.annotation.JsonProperty

data class PlanetaRequest(

    @JsonProperty("nome")
    var nome: String? = null,

    @JsonProperty("clima")
    var clima: String? = null,

    @JsonProperty("terreno")
    var terreno: String? = null

)

fun PlanetaRequest.toPlaneta() = Planeta(
    nome = nome.orEmpty(),
    clima = clima.orEmpty(),
    terreno = terreno.orEmpty()
)
