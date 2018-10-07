package micronaut.example.service

import micronaut.example.model.User
import javax.inject.Singleton

interface UserService {
    fun getUserById(id: String): User
}

@Singleton
class UserServiceImpl: UserService {
    override fun getUserById(id: String): User {
        return User("Miguel", "Angel", "Software Engineer", 25)
    }
}