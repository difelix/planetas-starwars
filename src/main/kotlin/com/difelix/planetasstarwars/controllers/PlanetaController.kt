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
    fun atualizarPlaneta(@PathVariable id: Long, @RequestBody planetaRequest: PlanetaRequest) : ResponseEntity<PlanetaResponse> {
        val planetaResponse = planetaService.update(id, planetaRequest)
        return ResponseEntity(planetaResponse, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun apagarPlaneta(@PathVariable id: Long) : ResponseEntity<Unit> {
        planetaService.delete(id)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    @GetMapping
    fun buscarPlanetaPeloNome(@RequestParam(value = "nome", required = true) nome: String) : ResponseEntity<PlanetaResponse> {
        val planetaResponse = planetaService.searchByPlanetName(nome)
        return ResponseEntity(planetaResponse, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun buscarTodosPlanetas() : ResponseEntity<List<PlanetaResponse>> {
        val listPlanetas = planetaService.findAll()
        val status = if (listPlanetas.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listPlanetas, status)
    }

    @GetMapping("/all/paginated")
    fun buscarTodosPlanetasComPaginacaoOrdenacao(@RequestParam(value = "page", defaultValue = "0") page: Int,
                                                 @RequestParam(value = "size", defaultValue = "10") size: Int,
                                                 @RequestParam(value = "sorted", defaultValue = "asc") sorted: String,
                                                 @RequestParam(value = "sorted_field", defaultValue = "nome") sortedField: String)
    : ResponseEntity<List<PlanetaResponse>> {
        val listPlanetas = planetaService.findAllPaginatedAndSorted(page, size, sorted, sortedField)
        val status = if (listPlanetas.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listPlanetas, status)
    }

    @GetMapping("/count")
    fun calcularQuantidadeRegistrosPlanetas() : ResponseEntity<Map<String, Long>> {
        val count = mapOf("count" to planetaService.count())
        return ResponseEntity(count, HttpStatus.OK)
    }

}