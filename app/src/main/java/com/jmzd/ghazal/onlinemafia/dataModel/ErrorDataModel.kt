package com.jmzd.ghazal.onlinemafia.dataModel

data class ErrorDataModel(
    val error: Error
)

data class Error(
    val message: String,
    val status: String
)