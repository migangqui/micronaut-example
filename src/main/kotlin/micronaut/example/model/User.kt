package micronaut.example.model

import org.bson.types.ObjectId

data class User(val _id: ObjectId?, val name: String, val surname: String, val job: String, val age: Int) {
    constructor() : this(null, "", "", "", 0)
}