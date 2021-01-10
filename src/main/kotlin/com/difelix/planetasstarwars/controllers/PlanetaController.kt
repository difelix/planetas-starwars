package com.difelix.planetasstarwars.controllers

import com.difelix.planetasstarwars.models.dtos.PlanetaRequest
import com.difelix.planetasstarwars.models.dtos.PlanetaResponse
import com.difelix.planetasstarwars.services.PlanetaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/planetas/starwars")
class PlanetaController(private var planetaService: PlanetaService) {

    @PostMapping
    fun salvarPlaneta(@RequestBody planetaRequest: PlanetaRequest) : ResponseEntity<PlanetaResponse> {
        val planetaResponse = planetaService.save(planetaRequest)
        return ResponseEntity(planetaResponse, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun atualizarPlaneta(@PathVariable id: Long, @RequestBody planetaRequest: PlanetaRequest) : ResponseEntity<PlanetaResponse?> {
        val planetaResponse = planetaService.update(id, planetaRequest)
        val status = if (planetaResponse == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(planetaResponse, status)
    }

    @DeleteMapping("/{id}")
    fun apagarPlaneta(@PathVariable id: Long) : ResponseEntity<Unit> {
        val status = planetaService.delete(id)
        return ResponseEntity(status)
    }

    @GetMapping
    fun buscarPlanetaPeloNome(@RequestParam(value = "nome", required = true) nome: String) : ResponseEntity<PlanetaResponse?> {
        val planetaResponse = planetaService.searchByPlanetName(nome)
        val status = if (planetaResponse == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(planetaResponse, status)
    }

}