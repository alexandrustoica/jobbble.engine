package jobbble.org.jobbbleserver

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
enum class UserRole {
    STUDENT, HR
}