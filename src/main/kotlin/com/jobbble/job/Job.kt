package com.jobbble.job

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.jobbble.user.User
import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
data class Job
@PersistenceConstructor constructor(
        @JsonIgnore @Id val id: ObjectId = ObjectId(),
        val title: String = "Default",
        val company: String= "Company",
        val location: String = "New York",
        val text: String = "Default",
        val position: String = "Developer",
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
        @CreatedDate val createdBy: Calendar = Calendar.getInstance(),
        @CreatedBy @JsonIgnore @DBRef private val author: User = User(),
        @JsonIgnore @DBRef private val applicants: List<User> = listOf()) {

    @JsonProperty("id")
    fun id(): String = id.toString()

    @JsonProperty("author")
    fun author(): User = author

    @JsonProperty
    fun applicants(): List<User> = applicants

}