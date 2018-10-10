package com.migangqui.micronaut.example.model

import org.bson.types.ObjectId

data class Account(var _id: ObjectId? = null, var username: String = "", var password: String = "", var authorities: MutableList<String> = mutableListOf())