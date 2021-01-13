package com.difelix.planetasstarwars.controllers.handler

import com.difelix.planetasstarwars.exceptions.ErrorMessage
import com.difelix.planetasstarwars.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
@ResponseBody
class HandlerException {

    @ExceptionHandler(HttpServerErrorException.InternalServerError::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalServerErrorException(ex: HttpServerErrorException.InternalServerError,
                                     request: WebRequest) : ErrorMessage =
        ErrorMessage(erro = "INTERNAL_SERVER_ERROR", mensagemErro = ex.message.orEmpty())

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun notFoundException(ex: NotFoundException, request: WebRequest) =
        ErrorMessage(erro = "Planeta não encontrado", mensagemErro = ex.message.orEmpty())

}