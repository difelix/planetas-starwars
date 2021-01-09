package com.difelix.planetasstarwars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlanetasStarwarsApplication

fun main(args: Array<String>) {
	runApplication<PlanetasStarwarsApplication>(*args)
}
