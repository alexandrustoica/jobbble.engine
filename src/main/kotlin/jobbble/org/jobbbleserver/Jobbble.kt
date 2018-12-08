package jobbble.org.jobbbleserver

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Document
data class Job
@PersistenceConstructor constructor(
        @JsonProperty @Id val id: ObjectId = ObjectId(),
        @JsonProperty val title: String)


@Repository
interface JobRepository : ReactiveMongoRepository<Job, String> {
    fun findJobsByTitleLike(title: String): Flux<Job>
}

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


@SpringBootApplication
class Jobbble

fun main(args: Array<String>) {
    runApplication<Jobbble>(*args)
}
