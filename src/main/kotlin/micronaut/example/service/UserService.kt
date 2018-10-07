package micronaut.example.service

import com.google.gson.Gson
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Flowable
import io.reactivex.Single
import micronaut.example.model.User
import org.bson.Document
import javax.inject.Singleton

interface UserService {
    fun getUsers(): Single<List<Document>>
    fun getUserById(id: String): User
}

@Singleton
class UserServiceImpl(val mongoClient: MongoClient): UserService {
    override fun getUsers(): Single<List<Document>> {
        return Flowable.fromPublisher(collection().find()).toList()
    }

    override fun getUserById(id: String): User {
        val user = User("1","Miguel", "Angel", "Software Engineer", 25)
        val userJson = Gson().toJson(user)
        Flowable.fromPublisher(collection().insertOne(Document.parse(userJson)))
        return user
    }

    private fun collection(): MongoCollection<Document> {
        return mongoClient.getDatabase("exampledb").getCollection("user")
    }
}