package com.difelix.planetasstarwars.controllers

import com.difelix.planetasstarwars.models.dtos.PlanetaRequest
import com.difelix.planetasstarwars.services.PlanetaService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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

    @ApiOperation(value = "Operação que salva os dados de um planeta")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Retorna planeta salvo"),
        ApiResponse(code= 500, message = "Internal Server Error")
    ])
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun salvarPlaneta(@RequestBody planetaRequest: PlanetaRequest) =
        planetaService.save(planetaRequest)

    @ApiOperation(value = "Operação que atualiza os dados de um planeta cadastrado no sistema")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna planeta atualizado"),
        ApiResponse(code = 404, message = "Id do planeta não encontrado"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun atualizarPlaneta(@PathVariable id: Long, @RequestBody planetaRequest: PlanetaRequest) =
        planetaService.update(id, planetaRequest)


    @ApiOperation(value = "Operação que apaga os dados de um planeta cadastrado no sistema")
    @ApiResponses(value = [
        ApiResponse(code = 202, message = "Planeta excluído com sucesso"),
        ApiResponse(code = 404, message = "Id do planeta não encontrado"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun apagarPlaneta(@PathVariable id: Long) {
        planetaService.delete(id)
    }

    @ApiOperation(value = "Operação que consulta os dados de um planeta pelo nome")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna planeta encontrado"),
        ApiResponse(code = 404, message = "Id do planeta não encontrado"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarPlanetaPeloNome(@RequestParam(value = "nome", required = true) nome: String) =
        planetaService.searchByPlanetName(nome)

    @ApiOperation(value = "Operação que busca todos os planetas cadastrados no sistema")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna lista dos planetas encontrados"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosPlanetas() = planetaService.findAll()

    @ApiOperation(value = "Operação que busca todos os planetas cadastrados no sistema com paginação e ordenação")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna lista dos planetas encontrados"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @GetMapping("/all/paginated")
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosPlanetasComPaginacaoOrdenacao(@RequestParam(value = "page", defaultValue = "0") page: Int,
                                                 @RequestParam(value = "size", defaultValue = "10") size: Int,
                                                 @RequestParam(value = "sorted", defaultValue = "asc") sorted: String,
                                                 @RequestParam(value = "sorted_field", defaultValue = "nome") sortedField: String)
    = planetaService.findAllPaginatedAndSorted(page, size, sorted, sortedField)

    @ApiOperation(value = "Operação que retorna a contagem da quantidade de planetas cadastrados no sistema")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Retorna count do banco"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    fun calcularQuantidadeRegistrosPlanetas() = mapOf("count" to planetaService.count())

}