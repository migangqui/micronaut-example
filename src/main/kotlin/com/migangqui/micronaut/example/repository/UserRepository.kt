package com.migangqui.micronaut.example.repository

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.property.MongoProperty
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class UserRepository(private val mongoClient: MongoClient, private val mongoProperty: MongoProperty) : MongoRepository<User> {
    override fun getAll(): List<User> {
        val users = mutableListOf<User>()
        collection().find().forEach { user ->
            users.add(user)
        }
        return users
    }

    override fun getById(id: String): User? {
        return collection().find(eq("_id", ObjectId(id))).first()
    }

    override fun create(item: User): User {
        collection().insertOne(item)
        return item
    }

    /* Private methods */

    private fun collection(): MongoCollection<User> {
        return mongoClient.getDatabase(mongoProperty.database).getCollection("user", User::class.java)
    }
}