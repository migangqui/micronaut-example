package micronaut.example.repository

import com.google.gson.Gson
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import micronaut.example.model.User
import org.bson.Document
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class UserRepository(private val mongoClient: MongoClient) : MongoRepository<User> {
    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        collection().find().forEach { user ->
            users.add(Gson().fromJson(user.toJson(), User::class.java))
        }
        return users
    }

    override fun getById(id: String): User? {
        val user = collection().find(eq("_id", ObjectId(id))).first()
        return if (user!=null) Gson().fromJson(user.toJson(), User::class.java) else null
    }

    override fun create(item: User) {
        val userJson = Gson().toJson(item)
        collection().insertOne(Document.parse(userJson))
    }

    private fun collection(): MongoCollection<Document> {
        return mongoClient.getDatabase("exampledb").getCollection("user")
    }
}