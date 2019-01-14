package com.jobbble.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
data class Skill @PersistenceConstructor constructor(
        @JsonIgnore @Id val id: ObjectId = ObjectId(),
        private val value: String) {
    @JsonProperty("id")
    fun id(): String = id.toString()
}