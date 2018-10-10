package com.migangqui.micronaut.example.config

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import javax.inject.Singleton

@Factory
class MongoConfig {
    @Bean
    @Singleton
    fun mongoClient(): MongoClient {
        return MongoClients.create()
    }
}