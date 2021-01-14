package com.difelix.planetasstarwars.exceptions

import java.time.LocalDateTime

data class ErrorMessage(

    var erro: String = "",
    var mensagemErro: String = "",
    var dataHoraErro: LocalDateTime = LocalDateTime.now()

)
