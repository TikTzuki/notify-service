package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import vn.unicloud.notification.enums.SourceEmailType
import vn.unicloud.notification.enums.SourceEventType

@Document("entity_trigger")
class EntityTrigger(
    @Id
    var id: String? = null,
    var sourceType: SourceEventType,
    var topic: String,
    var path: String,
    var templateId: String,
    var sourceEmail: List<String>,
    var sourceEmailType: SourceEmailType?=null,
    var sourceEmailCredentials: String?=null,
    var emails: List<String>,
) : BaseEntity()