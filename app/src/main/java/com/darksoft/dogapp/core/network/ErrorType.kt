package com.darksoft.dogapp.core.network

sealed class ErrorType (val errorCode: Int){

    object BadRequest: ErrorType(400)
    object NotFound: ErrorType(404)
    object InternalServerError: ErrorType(500)
    data class UncontrolledError(val code: Int): ErrorType(code)
    data class ExceptionError(val exception: Exception): ErrorType(0)

}