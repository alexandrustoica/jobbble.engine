package jobbble.org.jobbbleserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class Jobbble

fun main(args: Array<String>) {
    runApplication<Jobbble>(*args)
}
