package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("entity_trigger")
class EntityTrigger(
    @Id var id: String,
    var type: String,
    var topic: String,
    var url: String
):BaseEntity()