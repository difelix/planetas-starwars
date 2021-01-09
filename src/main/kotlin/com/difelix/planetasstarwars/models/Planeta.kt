package com.difelix.planetasstarwars.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TBL_PLANETA_STAR_WARS")
data class Planeta(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    @Column(name = "nome", length = 100, nullable = false)
    var nome: String,

    @Column(name = "clima", length = 100, nullable = false)
    var clima: String,

    @Column(name = "terreno", length = 100, nullable = false)
    var terreno: String

)
