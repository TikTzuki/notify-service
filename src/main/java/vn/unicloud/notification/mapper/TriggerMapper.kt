package vn.unicloud.notification.mapper

import vn.unicloud.notification.dtos.TriggerDto
import vn.unicloud.notification.entity.EntityTrigger

fun TriggerDto.toEntityTrigger() = EntityTrigger(
    sourceType = sourceType,
    topic = topic,
    path = url,
    templateId = templateId
)

fun EntityTrigger.toTriggerDto() = TriggerDto(
    id = id,
    sourceType = sourceType,
    topic = topic,
    url = path,
    createdBy = createdBy,
    updatedBy = updatedBy,
    createdAt = createdAt,
    updatedAt = updatedAt,
    templateId = templateId
)