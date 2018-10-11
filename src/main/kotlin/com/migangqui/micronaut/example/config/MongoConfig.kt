package com.migangqui.micronaut.example.config

import com.migangqui.micronaut.example.property.MongoProperty
import com.mongodb.MongoClient.getDefaultCodecRegistry
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Indexes.ascending
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import mu.KotlinLogging
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import javax.inject.Singleton


@Factory
class MongoConfig {

    private val log = KotlinLogging.logger {}

    @Bean
    @Singleton
    fun mongoClient(mongoProperty: MongoProperty): MongoClient {
        val pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()))
        val settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build()
        val mongoClient = MongoClients.create(settings)

        log.info("Creating indexes")
        // Indexes Creation
        mongoClient.getDatabase(mongoProperty.database).getCollection("account")
                .createIndex(ascending("username"), IndexOptions().unique(true))

        return mongoClient
    }
}