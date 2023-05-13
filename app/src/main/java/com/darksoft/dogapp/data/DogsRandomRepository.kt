package com.darksoft.dogapp.data

import com.darksoft.dogapp.core.network.ErrorType
import com.darksoft.dogapp.core.network.ResultType
import com.darksoft.dogapp.data.network.DogApi
import com.darksoft.dogapp.data.network.model.toDomain
import com.darksoft.dogapp.domain.dto.DogsRandomDto
import com.darksoft.dogapp.domain.model.DogsRandomModel
import javax.inject.Inject

class DogsRandomRepository @Inject constructor(private val api: DogApi) {

    suspend fun getDogsRandom(dogsRandomDto: DogsRandomDto): ResultType<DogsRandomModel> {
        return try {
            val response = api.getDogs(dogsRandomDto.cantidad)
            if (response.isSuccessful) {
                response.body()?.let { dogsRandomResponse ->
                    ResultType.Success(dogsRandomResponse.toDomain())
                } ?: ResultType.Error(ErrorType.UncontrolledError(-1))
            } else {
                val error = when (response.code()) {
                    ErrorType.BadRequest.errorCode -> ErrorType.BadRequest
                    ErrorType.NotFound.errorCode -> ErrorType.NotFound
                    ErrorType.InternalServerError.errorCode -> ErrorType.InternalServerError
                    else -> ErrorType.UncontrolledError(response.code())
                }
                ResultType.Error(error)
            }
        } catch (e: Exception) {
            ResultType.Error(ErrorType.ExceptionError(e))
        }
    }

}