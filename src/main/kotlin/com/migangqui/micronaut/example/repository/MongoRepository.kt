package com.migangqui.micronaut.example.repository

import com.migangqui.micronaut.example.model.User
import io.reactivex.Single

interface MongoRepository<T> {

    fun getAll(): Single<List<T>>
    fun getById(id: String): Single<User>
    fun create(item: T): Single<T>
    fun deleteAll()
    fun deleteUser(id: String)
}