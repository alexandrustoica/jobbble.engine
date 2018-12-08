package jobbble.org.jobbbleserver

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Job
@PersistenceConstructor constructor(
        @JsonProperty @Id val id: ObjectId = ObjectId(),
        @JsonProperty val title: String)

