package vn.unicloud.notification.mapper

import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.entity.Template

class TemplateMapper {
}

fun Template.toTemplateDto() = TemplateDto(
    id = id!!,
    title = title,
    description = description,
    sourceType = sourceType,
    content = content,
    type = type,
    createdAt = createdAt!!,
    updatedAt = updatedAt!!,
    createdBy = createdBy,
    updatedBy = updatedBy
)

fun TemplateDto.toTemplate() = Template(
    title = title,
    description = description,
    sourceType = sourceType,
    content = content,
    type = type
)
