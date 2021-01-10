package com.difelix.planetasstarwars.models.entities

import com.difelix.planetasstarwars.models.dtos.PlanetaResponse
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TBL_PLANETA_STAR_WARS", schema = "public")
data class Planeta(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,

    @Column(name = "nome", length = 100, nullable = false)
    var nome: String = "",

    @Column(name = "clima", length = 100, nullable = false)
    var clima: String = "",

    @Column(name = "terreno", length = 100, nullable = false)
    var terreno: String = ""

)

fun Planeta.toPlanetaResponse() = PlanetaResponse(
    id = id,
    nome = nome,
    clima = clima,
    terreno = terreno
)
