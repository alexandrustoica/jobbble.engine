package jobbble.org.jobbbleserver

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class UserNotFound(
        override val message: String =
                "Unable to find the user in our database"):
        RuntimeException()