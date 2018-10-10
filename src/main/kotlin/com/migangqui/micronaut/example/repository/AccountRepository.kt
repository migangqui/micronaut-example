package com.migangqui.micronaut.example.repository

import com.migangqui.micronaut.example.model.Account
import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.property.MongoProperty
import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class AccountRepository(private val mongoClient: MongoClient, private val mongoProperty: MongoProperty) : MongoRepository<Account> {
    override fun getAll(): Single<List<Account>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: String): Single<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(item: Account): Single<Account> {
        return Single
                .fromPublisher(collection().insertOne(item))
                .map { item }
    }

    override fun deleteAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getByUsername(username: String): Single<Account> {
        return Single.fromPublisher(collection().find(eq("username", username)))
    }

    private fun collection(): MongoCollection<Account> {
        return mongoClient.getDatabase(mongoProperty.database).getCollection("account", Account::class.java)
    }
}
