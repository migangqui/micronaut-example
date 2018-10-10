package com.migangqui.micronaut.example.model

import org.bson.types.ObjectId

data class User(var _id: ObjectId? = null, var name: String = "", var surname: String = "", var job: String = "", var age: Int = 0)