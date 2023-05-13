package com.darksoft.dogapp.data.network.model

import com.darksoft.dogapp.domain.model.DogsRandomModel

data class DogsRandomResponse(
    val message: List<String>,
    val status: String
)

// Mapeamos los datos para la capa domain (tiene que tener el mismo nombre de la clase)
fun DogsRandomResponse.toDomain() = DogsRandomModel(
    message = this.message,
    status = this.status
)