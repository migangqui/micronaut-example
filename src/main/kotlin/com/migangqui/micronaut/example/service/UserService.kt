package com.migangqui.micronaut.example.service

import io.micronaut.http.HttpResponse
import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.repository.UserRepository
import java.nio.file.attribute.UserPrincipalNotFoundException
import javax.inject.Singleton


interface UserService {
    fun getUsers(): HttpResponse<List<User>>
    fun getUserById(id: String): HttpResponse<User>
    fun createUser(user: User): HttpResponse<User>
}

@Singleton
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getUsers(): HttpResponse<List<User>> {
        return HttpResponse.ok(userRepository.getAll())
    }

    override fun getUserById(id: String): HttpResponse<User> {
        val user = userRepository.getById(id)
        return when (user) {
            null -> HttpResponse.notFound<User>()
            else -> HttpResponse.ok(user)
        }
    }

    override fun createUser(user: User): HttpResponse<User> {
        userRepository.create(user)
        return HttpResponse.created(user)
    }
}