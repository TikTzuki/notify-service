package vn.unicloud.notification.dtos

import vn.unicloud.notification.enums.TemplateType
import java.time.LocalDateTime

class TemplateDto(
    var id: String?,
    var title: String,
    var description: String,
    var type: TemplateType,
    var content: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
    var createdBy: String?,
    var updatedBy: String?,
)

class TemplateRequestPartDto(
    var title: String,
    var description: String,
    var type: TemplateType,
)