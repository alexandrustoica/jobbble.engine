package jobbble.org.jobbbleserver

import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
class UnableToCreateUser(
        override val message: String? =
                "Unable to create new user!") : RuntimeException()

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var repository: UserRepository

    @PutMapping("/student/register")
    fun registerNewStudent(@RequestBody user: User): Mono<User> =
            repository.insert(user.copy(role = UserRole.STUDENT))

    @PutMapping("/hr/register")
    fun registerNewHR(@RequestBody user: User): Mono<ResponseEntity<User>> =
            repository.insert(user.copy(role = UserRole.HR))
                    .map { ResponseEntity.ok().body(it) }
                    .onErrorMap { error -> UnableToCreateUser(error.message) }

    @GetMapping("")
    fun findAllUsers(): Flux<User> =
            repository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Mono<ResponseEntity<User>> =
            repository.findById(ObjectId(id))
                    .map { ResponseEntity.ok().body(it) }
                    .switchIfEmpty(Mono.error(UserNotFound()))

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: String): Mono<ResponseEntity<User>> =
            repository.deleteById(ObjectId(id))
                    .flatMap { repository.findById(ObjectId(id)) }
                    .map { ResponseEntity.ok().body(it) }
                    .switchIfEmpty(Mono.error(UserNotFound()))

    @PostMapping("")
    fun update(@RequestBody user: User): Mono<ResponseEntity<User>> =
            repository.insert(user)
                    .map { ResponseEntity.ok().body(it) }
                    .switchIfEmpty(Mono.error(UserNotFound()))
}