package com.ginamelinia.architectureplayground.repository

interface LoginRepository {

    fun validateInput(username: String, password: String): Boolean

    fun authenticate(username: String, password: String): String?

    fun saveToken(token: String)

    fun loadToken(): String?
}