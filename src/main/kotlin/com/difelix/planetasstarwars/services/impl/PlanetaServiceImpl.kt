package com.difelix.planetasstarwars.services.impl

import com.difelix.planetasstarwars.exceptions.NotFoundException
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
import org.springframework.stereotype.Service

@Service
class PlanetaServiceImpl(private var planetaRepository: PlanetaRepository) : PlanetaService {

    override fun save(planetaRequest: PlanetaRequest): PlanetaResponse {
        val planeta = planetaRepository.save(planetaRequest.toPlaneta())
        return planeta.toPlanetaResponse()
    }

    override fun update(id: Long, planetaRequest: PlanetaRequest): PlanetaResponse {
        val existPlaneta = planetaRepository.existsById(id)

        if (!existPlaneta)
            throw NotFoundException("Planeta com id [$id] não foi encontrado")

        var planeta = planetaRequest.toPlaneta()
        planeta.id = id
        planeta = planetaRepository.save(planeta)

        return planeta.toPlanetaResponse()
    }

    override fun delete(id: Long) {
        if (!planetaRepository.existsById(id))
            throw NotFoundException("Planeta com id [$id] não foi encontrado")
        planetaRepository.deleteById(id)
    }

    override fun searchByPlanetName(nome: String): PlanetaResponse {
        val planeta = planetaRepository.findByNome(nome)
        val planetaEncontrado = planeta.orElseThrow {
            NotFoundException("Planeta com nome [$nome] não foi encontrado") }
        return planetaEncontrado.toPlanetaResponse()
    }

    override fun findAll(): List<PlanetaResponse> {
        return planetaRepository.findAll().map {planeta -> planeta.toPlanetaResponse()}
    }

    override fun findAllPaginatedAndSorted(
        page: Int,
        size: Int,
        sort: String,
        sortField: String
    ): List<PlanetaResponse> {
        val tipoOrdenacao = CampoPlanetaSort.contains(sort)
        val campoOrdenacao = CampoPlanetaBusca.contains(sortField)

        val pagination = if (tipoOrdenacao == CampoPlanetaSort.ASC)
            PageRequest.of(page, size, Sort.by(campoOrdenacao.campo).ascending())
        else PageRequest.of(page, size, Sort.by(campoOrdenacao.campo).descending())

        return planetaRepository.findAll(pagination).map { planeta -> planeta.toPlanetaResponse() }
    }

    override fun count() = planetaRepository.count()

}