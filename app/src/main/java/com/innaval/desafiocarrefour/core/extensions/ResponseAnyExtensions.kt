package com.innaval.desafiocarrefour.core.extensions

import com.innaval.desafiocarrefour.data.remote.ResponseAny
import com.innaval.desafiocarrefour.data.remote.ResponseEmpty
import com.innaval.desafiocarrefour.data.remote.ResponseError
import com.innaval.desafiocarrefour.data.remote.ResponseSuccess

fun <T> ResponseAny<T>.read(
    success: (T) -> Unit,
    error: ((Exception) -> Unit)? = null,
    empty: ((Int) -> Unit)? = null,
) {
    when (this) {
        is ResponseSuccess -> success(this.body)
        is ResponseError -> error?.invoke(this.exception)
        is ResponseEmpty -> empty?.invoke(this.code)
    }
}