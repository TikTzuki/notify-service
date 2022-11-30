package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document("notification")
class Notification(
    @Id var id: String,
    var name: String,
    var phone: String,
    var mail: String,
    @DBRef
    var template: Template,
    var sourceId: String,
    var sourceType: String,
    var type: String,
    var read: Boolean,
    var trash: Boolean,
) : BaseEntity()
