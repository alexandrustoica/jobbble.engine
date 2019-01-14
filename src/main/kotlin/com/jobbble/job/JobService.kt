package com.jobbble.job

import com.jobbble.user.User
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobService {

    @Autowired
    private lateinit var repository: JobRepository

    fun all(): List<Job> = repository.findAll()

    fun insert(job: Job): Job? = repository.insert(job)

    fun apply(job: Job, candidate: User): Job? =
            job.copy(applicants = job.applicants() + listOf(candidate))
                    .let { repository.save(it) }

    fun findBy(id: String): Job? =
             repository.findByIdEquals(ObjectId(id))

    fun unapply(job: Job, candidate: User): Job? =
            job.copy(applicants = job.applicants()
                    .filter { it.id() == candidate.id() })
                    .let { repository.save(it) }
}