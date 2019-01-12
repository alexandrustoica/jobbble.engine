package com.jobbble.job

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository: MongoRepository<Job, ObjectId>