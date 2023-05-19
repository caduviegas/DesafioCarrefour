package com.innaval.desafiocarrefour.data.remote

import androidx.annotation.Keep
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


sealed class ResponseAny<out T> {
companion object {

    fun <T> ioException(response: Response<T>, errorBodyStr: String? = null) = IOException(
        "ERROR CODE: ${response.code()} - ERROR MESSAGE: ${response.message()} - ERROR BODY: $errorBodyStr",
        HttpException(response),
    )

    fun <T> create(response: Response<T>): ResponseAny<T> {
        return if (response.isSuccessful) {
            val body = response.body()

            if (body == null || response.code() == 204) {
                ResponseEmpty(response.code())
            } else {
                ResponseSuccess(body)
            }
        } else {
            if (response.code() == 422 && response.errorBody() != null) {
                response.errorBody()?.byteStream()
                    ?.bufferedReader()
                    ?.readLine().run {
                        ResponseError(ioException(response, this), this)
                    }
            } else {
                ResponseError(
                    ioException(response),
                )
            }
        }
    }

    fun create(exception: Exception): ResponseError = ResponseError(exception)
}
}

@Keep
data class ResponseError(val exception: Exception, val errorBody: String? = null) :
    ResponseAny<Nothing>()

@Keep
data class ResponseEmpty(val code: Int) : ResponseAny<Nothing>()

@Keep
data class ResponseSuccess<T>(val body: T) : ResponseAny<T>()
