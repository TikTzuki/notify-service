package vn.unicloud.notification.dtos

import vn.unicloud.notification.enums.SourceEventType
import java.time.LocalDateTime

class TriggerDto(
    var id: String?=null,
    var sourceType: SourceEventType,
    var topic: String,
    var path: String,
    var templateId: String,
    var createdBy: String?,
    var updatedBy: String?,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
)