package org.adaschool.retrofit.storage

interface Storage {

    fun saveToken(token: String)

    fun getToken(): String?

    fun clear()
}
