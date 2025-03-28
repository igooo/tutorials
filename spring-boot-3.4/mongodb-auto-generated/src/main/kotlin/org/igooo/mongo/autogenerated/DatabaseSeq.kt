package org.igooo.mongo.autogenerated

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("database_seq")
data class DatabaseSeq(@Id val id: String, val seq: Long)