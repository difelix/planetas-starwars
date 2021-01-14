package com.difelix.planetasstarwars.controllers

import com.difelix.planetasstarwars.models.dtos.PlanetaRequest
import com.difelix.planetasstarwars.services.PlanetaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@ResponseBody
@RequestMapping("/planetas/starwars")
class PlanetaController(private var planetaService: PlanetaService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun salvarPlaneta(@RequestBody planetaRequest: PlanetaRequest) =
        planetaService.save(planetaRequest)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun atualizarPlaneta(@PathVariable id: Long, @RequestBody planetaRequest: PlanetaRequest) =
        planetaService.update(id, planetaRequest)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun apagarPlaneta(@PathVariable id: Long) {
        planetaService.delete(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarPlanetaPeloNome(@RequestParam(value = "nome", required = true) nome: String) =
        planetaService.searchByPlanetName(nome)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosPlanetas() = planetaService.findAll()

    @GetMapping("/all/paginated")
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosPlanetasComPaginacaoOrdenacao(@RequestParam(value = "page", defaultValue = "0") page: Int,
                                                 @RequestParam(value = "size", defaultValue = "10") size: Int,
                                                 @RequestParam(value = "sorted", defaultValue = "asc") sorted: String,
                                                 @RequestParam(value = "sorted_field", defaultValue = "nome") sortedField: String)
    = planetaService.findAllPaginatedAndSorted(page, size, sorted, sortedField)

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    fun calcularQuantidadeRegistrosPlanetas() = mapOf("count" to planetaService.count())

}