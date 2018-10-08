package micronaut.example.service

import micronaut.example.model.User
import micronaut.example.repository.UserRepository
import javax.inject.Singleton


interface UserService {
    fun getUsers(): List<User>
    fun getUserById(id: String): User
    fun createUser(user: User)
}

@Singleton
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getUsers(): List<User> {
        return userRepository.getAll()
    }

    override fun getUserById(id: String): User {
        return userRepository.getById(id)
    }

    override fun createUser(user: User) {
        userRepository.create(user)
    }
}