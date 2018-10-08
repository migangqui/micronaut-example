package micronaut.example.repository

import com.google.gson.Gson
import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Flowable
import micronaut.example.model.User
import micronaut.example.mongo.OperationSubscriber
import org.bson.Document
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class UserRepository(private val mongoClient: MongoClient, private val gson: Gson) : MongoRepository<User> {
    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        Flowable.fromPublisher(collection().find()).toList().blockingGet().forEach { user ->
            users.add(gson.fromJson(user.toJson(), User::class.java))
        }
        return users
    }

    override fun getById(id: String): User {
        val user = Flowable.fromPublisher(collection().find(eq("_id", ObjectId(id))).first()).toList().blockingGet()[0]
        return gson.fromJson(user.toJson(), User::class.java)
    }

    override fun create(item: User) {
        val userJson = gson.toJson(item)
        collection().insertOne(Document.parse(userJson)).subscribe(OperationSubscriber())
    }

    private fun collection(): MongoCollection<Document> {
        return mongoClient.getDatabase("exampledb").getCollection("user")
    }
}