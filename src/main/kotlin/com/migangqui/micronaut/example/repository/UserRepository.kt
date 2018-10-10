package com.migangqui.micronaut.example.repository

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.property.MongoProperty
import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Flowable
import io.reactivex.Single
import org.bson.Document
import org.bson.types.ObjectId
import javax.inject.Singleton

@Singleton
class UserRepository(private val mongoClient: MongoClient, private val mongoProperty: MongoProperty) : MongoRepository<User> {
    override fun getAll(): Single<List<User>> {
        return Flowable.fromPublisher(collection().find()).toList()
    }

    override fun getById(id: String): Single<User> {
        return Single.fromPublisher(
                collection().find(eq("_id", ObjectId(id))))
    }

    override fun create(item: User): Single<User> {
        return Single
                .fromPublisher(collection().insertOne(item))
                .map { item }
    }

    override fun deleteAll() {
        Single.fromPublisher(collection().deleteMany(Document()))
                .subscribe()
    }

    override fun deleteUser(id: String) {
        Single.fromPublisher(collection().deleteOne(eq("_id", ObjectId(id))))
                .subscribe()
    }

    private fun collection(): MongoCollection<User> {
        return mongoClient.getDatabase(mongoProperty.database).getCollection("user", User::class.java)
    }
}