package micronaut.example.service

import com.google.gson.Gson
import com.mongodb.reactivestreams.client.MongoClient
import micronaut.example.model.User
import org.bson.Document
import javax.inject.Singleton

interface UserService {
    fun getUserById(id: String): User
}

@Singleton
class UserServiceImpl(val mongoClient: MongoClient): UserService {
    override fun getUserById(id: String): User {
        val user = User("1","Miguel", "Angel", "Software Engineer", 25)
        val userJson = Gson().toJson(user)
        mongoClient.getDatabase("exampledb").getCollection("user").insertOne(Document.parse(userJson))
        return user
    }
}