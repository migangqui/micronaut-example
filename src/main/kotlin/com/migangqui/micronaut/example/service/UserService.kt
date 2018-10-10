package com.migangqui.micronaut.example.service

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.repository.UserRepository
import io.reactivex.Single
import javax.inject.Singleton


interface UserService {
    fun getUsers(): Single<List<User>>
    fun getUserById(id: String): Single<User>
    fun createUser(user: User): Single<User>
    fun deleteUsers()
    fun deleteUserById(id: String)
}

@Singleton
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getUsers(): Single<List<User>> {
        return userRepository.getAll()
    }

    override fun getUserById(id: String): Single<User> {
        return userRepository.getById(id)
    }

    override fun createUser(user: User): Single<User> {
        return userRepository.create(user)
    }

    override fun deleteUsers() {
        userRepository.deleteAll()
    }

    override fun deleteUserById(id: String) {
        userRepository.deleteUser(id)
    }
}