package com.jefersonsalazar.test.domain

sealed interface ErrorDomain {
    class Unknown(val message: String) : ErrorDomain
    class Server(val code: Int) : ErrorDomain
    object Connectivity : ErrorDomain
}