package org.igooo.shedlock

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class TaskSchedulerService {

    @Scheduled(fixedDelay = 2000)
    @SchedulerLock(name="executeTask", lockAtMostFor = "1m", lockAtLeastFor = "1s")
    fun executeTask() {
        println("execute task at: ${LocalDateTime.now()}")
    }

}