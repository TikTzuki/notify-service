package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("actor")
class Actor(
    @Id
    var id: String,
    var name: String,
    var serverAuth: String
):BaseEntity()