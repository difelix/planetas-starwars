package com.difelix.planetasstarwars.repositories

import com.difelix.planetasstarwars.models.entities.Planeta
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlanetaRepository : CrudRepository<Planeta, Long> {

    fun findByNome(nome: String) : Optional<Planeta>

    fun findAll(pageable: Pageable) : Iterable<Planeta>

}