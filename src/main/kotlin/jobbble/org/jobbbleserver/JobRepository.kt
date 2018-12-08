package jobbble.org.jobbbleserver

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface JobRepository : ReactiveMongoRepository<Job, String> {
    fun findJobsByTitleLike(title: String): Flux<Job>
}