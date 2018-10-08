package micronaut.example.repository

import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Flowable
import io.reactivex.Single
import micronaut.example.model.User
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class UserRepository(private val mongoClient: MongoClient) : MongoRepository<User> {
    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        Flowable.fromPublisher(collection().find()).toList().blockingGet().forEach { user ->
            println(user)
            users.add(user)
        }
        return users
    }

    override fun getById(id: String): User {
        return Flowable
                .fromPublisher(
                        collection().find(eq("_id", ObjectId(id))).first())
                .toList().blockingGet()[0]
    }

    override fun create(item: User): Single<User> {
        return Single
                .fromPublisher(collection().insertOne(item))
                .map { success -> item }
    }

    private fun collection(): MongoCollection<User> {
        return mongoClient.getDatabase("exampledb").getCollection("user", User::class.java)
    }
}