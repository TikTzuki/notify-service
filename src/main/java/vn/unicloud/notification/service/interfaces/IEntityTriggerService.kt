package vn.unicloud.notification.service.interfaces

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import vn.unicloud.notification.dtos.TriggerDto

interface IEntityTriggerService {
    fun getTriggers(pageable: Pageable): Page<TriggerDto>
    fun getTrigger(id: String): TriggerDto
    fun createTrigger(triggerDto: TriggerDto): TriggerDto
    fun updateTrigger(id: String, triggerDto: TriggerDto): TriggerDto
    fun deleteTrigger(id: String)
}