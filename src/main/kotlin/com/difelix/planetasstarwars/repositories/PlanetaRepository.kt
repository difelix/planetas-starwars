package com.difelix.planetasstarwars.repositories

import com.difelix.planetasstarwars.models.Planeta
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanetaRepository : CrudRepository<Planeta, Long> {

}