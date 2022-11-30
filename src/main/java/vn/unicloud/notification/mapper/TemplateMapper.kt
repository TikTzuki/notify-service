package vn.unicloud.notification.mapper

import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.dtos.TemplateRequestPartDto
import vn.unicloud.notification.entity.Template

fun Template.toTemplateDto() = TemplateDto(
    id = id!!,
    title = title,
    description = description,
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
    content = content,
    type = type
)

fun TemplateRequestPartDto.toTemplate() = Template(
    title = title,
    description = description,
    type = type,
    content = ""
)