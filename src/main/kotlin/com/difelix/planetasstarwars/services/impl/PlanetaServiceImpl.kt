package com.difelix.planetasstarwars.services.impl

import com.difelix.planetasstarwars.models.Planeta
import com.difelix.planetasstarwars.repositories.PlanetaRepository
import com.difelix.planetasstarwars.services.PlanetaService
import org.springframework.stereotype.Service

@Service
class PlanetaServiceImpl(private var planetaRepository: PlanetaRepository) : PlanetaService {

    override fun save(planeta: Planeta): Planeta = planetaRepository.save(planeta)

}