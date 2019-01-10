package com.jobbble.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentRestController {
    @Autowired
    private lateinit var service: UserService

    @PostMapping("")
    fun insert(@RequestBody user: User): ResponseEntity<User> =
            user.copy(role = UserRole.STUDENT)
                    .let { service.insert(it) }
                    ?.let { ResponseEntity.ok(it) }
                    ?: ResponseEntity.badRequest().body(user)
}