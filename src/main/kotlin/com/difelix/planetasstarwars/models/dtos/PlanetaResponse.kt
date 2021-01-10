package com.difelix.planetasstarwars.models.dtos

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PlanetaResponse(

    @JsonProperty("id")
    var id: Long? = null,

    @JsonProperty("nome")
    var nome: String? = null,

    @JsonProperty("clima")
    var clima: String? = null,

    @JsonProperty("terreno")
    var terreno: String? = null

)
