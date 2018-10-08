package micronaut.example.repository

interface MongoRepository<T> {

    fun getAll(): List<T>
    fun getById(id: String): T?
    fun create(item: T)

}