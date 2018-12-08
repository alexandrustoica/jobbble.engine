package jobbble.org.job

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/jobs")
class JobController {

    @Autowired
    private lateinit var repository: JobRepository

    @ResponseBody
    @GetMapping("")
    fun all(): Flux<Job> = repository.findAll()

    @ResponseBody
    @PutMapping("")
    fun insert(@RequestBody job: Job): Mono<ResponseEntity<Job>> =
            repository.insert(job)
                    .map { ResponseEntity.ok().body(it) }
                    .onErrorMap { error -> UnableToCreateJob(error.message) }

    @ResponseBody
    @PostMapping("/{id}")
    fun update(@PathVariable id: String,
               @RequestBody job: Job): Mono<ResponseEntity<Job>> =
            repository.save(job.copy(id = ObjectId(id)))
                    .map { ResponseEntity.ok().body(it) }
                    .onErrorMap { error -> UnableToUpdateJob(error.message) }


    @ResponseBody
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): Mono<ResponseEntity<Job>> =
            repository.deleteById(ObjectId(id))
                    .flatMap { repository.findById(ObjectId(id)) }
                    .map { ResponseEntity.ok().body(it) }
                    .onErrorMap { error -> JobNotFound(error.message) }

    @ResponseBody
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Mono<ResponseEntity<Job>> =
            repository.findById(ObjectId(id))
                    .map { ResponseEntity.ok().body(it) }
                    .onErrorMap { error -> JobNotFound(error.message) }

}