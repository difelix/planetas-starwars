package com.difelix.planetasstarwars.models.enums

enum class CampoPlanetaSort(val sort: String) {

    ASC("asc"),
    DESC("desc");

    companion object {
        fun contains(sort: String): CampoPlanetaSort {
            return try {
                values().first() { it.sort == sort }
            } catch (e: NoSuchElementException) {
                return ASC
            }
        }
    }

}