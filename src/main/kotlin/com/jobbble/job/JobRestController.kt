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

    @PutMapping("/apply/{id}")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun apply(@PathVariable("id") jobId: String,
              @AuthenticationPrincipal user: User): ResponseEntity<Job> =
            service.findBy(jobId)
                    ?.let { service.apply(it, user) }
                    ?.let { ResponseEntity.ok(it) }
                    ?: ResponseEntity.badRequest().body(Job())

    @PutMapping("/unapply/{id}")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun unapply(@PathVariable("id") jobId: String,
                @AuthenticationPrincipal user: User): ResponseEntity<Job> =
            service.findBy(jobId)
                    ?.let { service.unapply(it, user) }
                    ?.let { ResponseEntity.ok(it) }
                    ?: ResponseEntity.badRequest().body(Job())

    @GetMapping("/{id}")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun findBy(@PathVariable("id") jobId: String): ResponseEntity<Job> =
            service.findBy(jobId)
                    ?.let { ResponseEntity.ok(it) }
                    ?: ResponseEntity.badRequest().body(Job())

    @DeleteMapping("/{id}")
    @Secured(value = ["ROLE_HR", "ROLE_STUDENT"])
    fun delete(@PathVariable("id") jobId: String): ResponseEntity<Job> =
            service.delete(jobId)
                    ?.let { ResponseEntity.ok(it)}
                    ?: ResponseEntity.notFound().build<Job>()

}