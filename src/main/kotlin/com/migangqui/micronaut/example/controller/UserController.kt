package com.migangqui.micronaut.example.controller

import com.migangqui.micronaut.example.model.User
import com.migangqui.micronaut.example.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.multipart.CompletedFileUpload
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/users")
class UserController(private val userService: UserService) {

    @Get
    fun getUsers(): HttpResponse<List<User>> {
        return userService.getUsers()
    }

    @Get("/{userId}")
    fun getUser(userId: String): HttpResponse<User> {
        return userService.getUserById(userId)
    }

    @Post
    fun createUser(@Body user: User): HttpResponse<User> {
       return userService.createUser(user)
    }

    @Post(value = "/upload", consumes = [MediaType.MULTIPART_FORM_DATA])
    fun uploadUserPhoto(file: CompletedFileUpload): HttpResponse<String> {
        return try {
            val tempFile = File.createTempFile(file.filename, "temp")
            val path = Paths.get(tempFile.absolutePath)
            Files.write(path, file.bytes)
            HttpResponse.ok("Uploaded")
        } catch (exception: IOException) {
            HttpResponse.badRequest("Upload Failed")
        }

    }
}