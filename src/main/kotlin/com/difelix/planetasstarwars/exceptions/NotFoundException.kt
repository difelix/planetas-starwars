package com.difelix.planetasstarwars.exceptions

import java.lang.RuntimeException

class NotFoundException(msg: String) : RuntimeException(msg) {
}