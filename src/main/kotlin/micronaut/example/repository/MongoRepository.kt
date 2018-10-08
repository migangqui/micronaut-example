package micronaut.example.repository

import io.reactivex.Single

interface MongoRepository<T> {

    fun getAll(): List<T>
    fun getById(id: String): T
    fun create(item: T): Single<T>

}