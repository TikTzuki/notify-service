package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import vn.unicloud.notification.enums.SourceEventType
import vn.unicloud.notification.enums.TemplateType

@Document("notification")
class Notification(
    @Id
    var id: String?=null,
    var name: String,
    var phone: String,
    var mail: String,
    @DBRef
    var template: Template,
    var sourceId: String,
    var sourceType: SourceEventType,
    var type: TemplateType,
    var read: Boolean,
    var trash: Boolean,
) : BaseEntity()
