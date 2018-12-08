package jobbble.org.jobbbleserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/jobs")
class JobController {

    @Autowired
    private lateinit var repository: JobRepository

    @ResponseBody
    @GetMapping("/{title}",
            produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun findFirstJobByTitleLike(@PathVariable title: String): Flux<Job> =
            repository.findJobsByTitleLike(title)

    @ResponseBody
    @GetMapping("")
    fun findAllJobs(): Flux<Job> = repository.findAll()

    @ResponseBody
    @PutMapping("")
    fun insert(@RequestBody job: Job): Mono<Job> =
            repository.save(job)

}