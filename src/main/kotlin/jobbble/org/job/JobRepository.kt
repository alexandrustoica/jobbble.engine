package jobbble.org.job

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface JobRepository : ReactiveMongoRepository<Job, ObjectId>