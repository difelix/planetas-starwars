package com.difelix.planetasstarwars.models.enums

enum class CampoPlanetaBusca(val campo: String) {

    ID("id"),
    NOME("nome"),
    CLIMA("clima"),
    TERRENO("terreno");

    companion object {
        fun contains(campo: String): CampoPlanetaBusca {
            return try {
                values().first() { it.campo == campo }
            } catch (e: NoSuchElementException) {
                return NOME
            }
        }
    }

}