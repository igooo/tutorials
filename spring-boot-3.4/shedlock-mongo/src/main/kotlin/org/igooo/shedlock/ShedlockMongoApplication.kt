package org.igooo.shedlock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShedlockMongoApplication

fun main(args: Array<String>) {
    runApplication<ShedlockMongoApplication>(*args)
}
