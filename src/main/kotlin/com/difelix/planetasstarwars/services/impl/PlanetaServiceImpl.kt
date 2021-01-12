package com.difelix.planetasstarwars.services.impl

import com.difelix.planetasstarwars.models.dtos.PlanetaRequest
import com.difelix.planetasstarwars.models.dtos.PlanetaResponse
import com.difelix.planetasstarwars.models.dtos.toPlaneta
import com.difelix.planetasstarwars.models.entities.toPlanetaResponse
import com.difelix.planetasstarwars.models.enums.CampoPlanetaBusca
import com.difelix.planetasstarwars.models.enums.CampoPlanetaSort
import com.difelix.planetasstarwars.repositories.PlanetaRepository
import com.difelix.planetasstarwars.services.PlanetaService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PlanetaServiceImpl(private var planetaRepository: PlanetaRepository) : PlanetaService {

    override fun save(planetaRequest: PlanetaRequest): PlanetaResponse {
        val planeta = planetaRepository.save(planetaRequest.toPlaneta())
        return planeta.toPlanetaResponse()
    }

    override fun update(id: Long, planetaRequest: PlanetaRequest): PlanetaResponse? {
        val existPlaneta = planetaRepository.existsById(id)

        if (!existPlaneta) {
            return null
        }

        var planeta = planetaRequest.toPlaneta()
        planeta.id = id
        planeta = planetaRepository.save(planeta)

        return planeta.toPlanetaResponse()
    }

    override fun delete(id: Long): HttpStatus {
        if (planetaRepository.existsById(id)) {
            planetaRepository.deleteById(id)
            return HttpStatus.ACCEPTED
        }
        return HttpStatus.NOT_FOUND
    }

    override fun searchByPlanetName(nome: String): PlanetaResponse? {
        val planeta = planetaRepository.findByNome(nome)
        return if (planeta.isPresent) planeta.get().toPlanetaResponse() else null
    }

    override fun findAll(): List<PlanetaResponse> {
        return planetaRepository.findAll().map {planeta -> planeta.toPlanetaResponse()}
    }

    override fun findAllPaginatedAndSorted(
        page: Int,
        size: Int,
        sorted: String,
        fieldSorted: String
    ): List<PlanetaResponse> {
        val sort = when(sorted) {
            CampoPlanetaSort.ASC.sort -> Sort.by(fieldSorted).ascending()
            CampoPlanetaSort.DESC.sort -> Sort.by(fieldSorted).descending()
            else -> Sort.by(fieldSorted).ascending()
        }

        val pagination = PageRequest.of(page, size, sort)

        return planetaRepository.findAll(pagination).map { planeta -> planeta.toPlanetaResponse() }
    }

    override fun count() = planetaRepository.count()

}