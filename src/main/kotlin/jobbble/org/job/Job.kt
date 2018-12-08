package jobbble.org.job

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Job
@PersistenceConstructor constructor(
        @JsonIgnore @Id val id: ObjectId = ObjectId(),
        @JsonProperty val title: String) {

    @JsonProperty
    fun id(): String = id.toString()
}

