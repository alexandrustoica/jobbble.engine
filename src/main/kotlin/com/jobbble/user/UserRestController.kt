package com.jobbble.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.xml.ws.Response

@RestController
@RequestMapping("/users")
class UserRestController {

    @Autowired
    private lateinit var service: UserService

    @GetMapping("/me")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun currentUser(@AuthenticationPrincipal user: User): ResponseEntity<User> =
            ResponseEntity.ok().body(user)

    @GetMapping("/{id}")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun findBy(@PathVariable("id") id: String): ResponseEntity<User> =
            service.findBy(id)
                    ?.let { ResponseEntity.ok(it) }
                    ?: ResponseEntity.notFound().build()

    @GetMapping("")
    fun all(): ResponseEntity<List<User>> =
            ResponseEntity.ok().body(service.all())

}