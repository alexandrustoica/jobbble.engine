package com.jobbble.job

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobService {

    @Autowired
    private lateinit var repository: JobRepository

    fun all(): List<Job> = repository.findAll()
    fun insert(job: Job): Job? = repository.save(job)

}