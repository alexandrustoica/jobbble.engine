package jobbble.org.jobbbleserver

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import javax.validation.constraints.Email


@JsonIgnoreProperties(ignoreUnknown = true)
data class User
@PersistenceConstructor constructor(
        @JsonIgnore @Id val id: ObjectId = ObjectId(),
        @JsonProperty @Indexed(unique = true) val username: String,
        @JsonProperty @Email val email: String,
        @JsonProperty val password: String,
        @JsonProperty val role: UserRole) : Serializable {

    @JsonProperty("id")
    fun id(): String = id.toString()
}