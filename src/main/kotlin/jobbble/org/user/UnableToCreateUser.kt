package jobbble.org.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
class UnableToCreateUser(
        override val message: String? =
                "Unable to create new user!") : RuntimeException()