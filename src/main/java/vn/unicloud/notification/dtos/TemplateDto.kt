package vn.unicloud.notification.dtos

import java.time.LocalDateTime

class TemplateDto(
    var id: String?,
    var title: String,
    var description: String,
    var type: String,
    var sourceType: String,
    var content: String,
    var createdAt: LocalDateTime?,
    var updatedAt: LocalDateTime?,
    var createdBy: String?,
    var updatedBy: String?,
)