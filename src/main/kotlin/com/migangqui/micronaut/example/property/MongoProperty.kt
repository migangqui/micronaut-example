package com.migangqui.micronaut.example.property

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("mongodb")
class MongoProperty {

    var database: String? = null

}