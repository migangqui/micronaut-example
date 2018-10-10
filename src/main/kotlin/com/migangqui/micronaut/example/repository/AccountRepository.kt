package com.migangqui.micronaut.example.repository

import com.migangqui.micronaut.example.model.Account
import com.migangqui.micronaut.example.property.MongoProperty
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import javax.inject.Singleton

@Singleton
class AccountRepository(private val mongoClient: MongoClient, private val mongoProperty: MongoProperty) : MongoRepository<Account> {
    override fun getAll(): List<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getById(id: String): Account {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(item: Account): Account {
        collection().insertOne(item)
        return item
    }

    fun getByUsername(username: String): Account? {
        return collection().find(eq("username", username)).first()
    }

    private fun collection(): MongoCollection<Account> {
        return mongoClient.getDatabase(mongoProperty.database).getCollection("account", Account::class.java)
    }
}
