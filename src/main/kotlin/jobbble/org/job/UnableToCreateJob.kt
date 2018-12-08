package jobbble.org.job

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(
        HttpStatus.NOT_ACCEPTABLE)
class UnableToCreateJob(override val message: String?) :
        RuntimeException()