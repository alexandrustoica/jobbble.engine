package com.jobbble.job

import com.jobbble.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/jobs")
class JobRestController {

    @Autowired
    private lateinit var service: JobService

    @GetMapping("")
    fun all(): ResponseEntity<List<Job>> =
            ResponseEntity.ok().body(service.all())

    @PostMapping("")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun insert(@RequestBody job: Job,
               @AuthenticationPrincipal user: User): ResponseEntity<Job> =
            service.insert(job.copy(author = user))
                    ?.let { ResponseEntity.ok(it) }
                    ?: ResponseEntity.badRequest().body(job)

}