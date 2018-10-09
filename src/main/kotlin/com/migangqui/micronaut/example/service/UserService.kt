package com.migangqui.micronaut.example.service

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.repository.UserRepository
import io.reactivex.Single
import javax.inject.Singleton


interface UserService {
    fun getUsers(): Single<List<User>>
    fun getUserById(id: String): User
    fun createUser(user: User): User
}

@Singleton
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getUsers(): Single<List<User>> {
        return userRepository.getAll()
    }

    override fun getUserById(id: String): User {
        return userRepository.getById(id)
    }

    override fun createUser(user: User): User {
        return userRepository.create(user).blockingGet()
    }
}