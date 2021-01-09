package com.difelix.planetasstarwars.services

import com.difelix.planetasstarwars.models.Planeta

interface PlanetaService {

    fun save(planeta: Planeta) : Planeta

}