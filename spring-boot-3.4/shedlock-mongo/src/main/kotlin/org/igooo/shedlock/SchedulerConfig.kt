package org.igooo.shedlock

import com.mongodb.client.MongoClient
import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "5m", defaultLockAtLeastFor = "30s")
class SchedulerConfig {
    @Bean
    fun lockProvider(mongo: MongoClient): LockProvider {
        return MongoLockProvider(mongo.getDatabase("test_db"))
    }
}