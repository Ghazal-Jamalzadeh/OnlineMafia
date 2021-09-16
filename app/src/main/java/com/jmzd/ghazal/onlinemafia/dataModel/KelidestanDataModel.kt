package com.jmzd.ghazal.onlinemafia.dataModel

data class KelidestanDataModel(
    val kelidestan: List<Kelidestan>
)

data class Kelidestan(
    val city: String,
    val id: String,
    val name: String
)