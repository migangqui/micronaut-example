package micronaut.example.repository

import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Flowable
import io.reactivex.Single
import micronaut.example.model.User
import micronaut.example.property.MongoProperty
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class UserRepository(private val mongoClient: MongoClient, private val mongoProperty: MongoProperty) : MongoRepository<User> {
    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        return Flowable.fromPublisher(collection().find()).toList().blockingGet()
//        return users
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
                .map { item }
    }

    private fun collection(): MongoCollection<User> {
        return mongoClient.getDatabase(mongoProperty.database).getCollection("user", User::class.java)
    }
}