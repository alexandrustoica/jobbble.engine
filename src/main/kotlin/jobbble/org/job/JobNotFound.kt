package jobbble.org.job

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(
        code = HttpStatus.NOT_FOUND,
        reason = "Unable to find your job!")
class JobNotFound(override val message: String?) :
        RuntimeException()