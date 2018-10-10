package com.migangqui.micronaut.example.config

import com.mongodb.MongoClient.getDefaultCodecRegistry
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import javax.inject.Singleton


@Factory
class MongoConfig {

    @Bean
    @Singleton
    fun mongoClient(): MongoClient {
        val pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()))
        val settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build()
        return MongoClients.create(settings)
    }
}