package com.difelix.planetasstarwars.services

import com.difelix.planetasstarwars.models.dtos.PlanetaRequest
import com.difelix.planetasstarwars.models.dtos.PlanetaResponse
import org.springframework.http.HttpStatus

interface PlanetaService {

    fun save(planetaRequest: PlanetaRequest) : PlanetaResponse

    fun update(id: Long, planetaRequest: PlanetaRequest) : PlanetaResponse

    fun delete(id: Long)

    fun searchByPlanetName(nome: String) : PlanetaResponse

    fun findAll() : List<PlanetaResponse>

    fun findAllPaginatedAndSorted(page: Int, size: Int, sort: String, sortField: String) : List<PlanetaResponse>

    fun count() : Long

}