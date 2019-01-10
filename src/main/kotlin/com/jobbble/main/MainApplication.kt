package com.jobbble.main

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
data class User (
        @JsonIgnore @Id val id: ObjectId,
        val username: String,
        val password: String) {
    @JsonProperty("id")
    fun id(): String = id.toString()
}

@Repository
interface UserRepository:
        MongoRepository<User, String>

@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    fun insert(user: User): User? = repository.insert(user)
    fun all(): List<User> = repository.findAll()

}

@RestController
@RequestMapping("/users")
class UserRestController {

    @Autowired
    private lateinit var service: UserService

    @PostMapping("")
    fun insert(@RequestBody user: User): ResponseEntity<User> =
            service.insert(user)?.let { ResponseEntity.ok(it) } ?:
            ResponseEntity.badRequest().body(user)

    @GetMapping("")
    fun all(): ResponseEntity<List<User>> =
            ResponseEntity.ok().body(service.all())
}

@SpringBootApplication
class MainApplication

fun main(args: Array<String>) {
    runApplication<MainApplication>(*args)
}

