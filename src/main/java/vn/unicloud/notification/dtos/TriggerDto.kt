package vn.unicloud.notification.dtos

import vn.unicloud.notification.enums.SourceEmailType
import vn.unicloud.notification.enums.SourceEventType
import java.time.LocalDateTime

class TriggerDto(
    var id: String? = null,
    var sourceType: SourceEventType,
    var topic: String,
    var path: String,
    var templateId: String,
    var sourceEmail: List<String> = listOf(),
    var sourceEmailType: SourceEmailType? = null,
    var sourceEmailCredentials: String? = null,
    var emails: List<String> = listOf(),
    var createdBy: String?,
    var updatedBy: String?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
)