package com.jefersonsalazar.test.testbold.framework.util

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.jefersonsalazar.test.domain.ErrorDomain
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toError(): ErrorDomain = when (this) {
    is IOException -> ErrorDomain.Connectivity
    is HttpException -> ErrorDomain.Server(code())
    else -> ErrorDomain.Unknown(message ?: "")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<ErrorDomain, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}