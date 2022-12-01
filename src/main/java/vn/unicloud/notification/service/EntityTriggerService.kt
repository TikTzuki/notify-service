package vn.unicloud.notification.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import vn.unicloud.notification.dtos.TriggerDto
import vn.unicloud.notification.entity.EntityTrigger
import vn.unicloud.notification.enums.ResponseCode
import vn.unicloud.notification.exception.InternalException
import vn.unicloud.notification.mapper.toEntityTrigger
import vn.unicloud.notification.mapper.toTriggerDto
import vn.unicloud.notification.repository.EntityTriggerRepository
import vn.unicloud.notification.repository.TemplateRepository
import vn.unicloud.notification.service.interfaces.IEntityTriggerService

@Service
class EntityTriggerService @Autowired constructor(
    val entityTriggerRepository: EntityTriggerRepository,
    val templateRepository: TemplateRepository
) : IEntityTriggerService {
    override fun getTriggers(pageable: Pageable): Page<TriggerDto> {
        return entityTriggerRepository.findAll(pageable).map(EntityTrigger::toTriggerDto)
    }

    override fun getTrigger(id: String): TriggerDto {
        return entityTriggerRepository.findById(id)
            .map(EntityTrigger::toTriggerDto)
            .orElseThrow { InternalException(ResponseCode.TRIGGER_NOT_FOUND) }
    }

    override fun createTrigger(triggerDto: TriggerDto): TriggerDto {
        if (!templateRepository.existsById(triggerDto.templateId)) {
            throw InternalException(ResponseCode.TEMPLATE_NOT_FOUND)
        }
        if (triggerDto.path.split("/").size < 2) {
            throw InternalException(ResponseCode.INVALID_PATH_FORMAT)
        }

        val entityTrigger = triggerDto.toEntityTrigger();
        return entityTriggerRepository.save(entityTrigger).toTriggerDto()
    }

    override fun updateTrigger(id: String, triggerDto: TriggerDto): TriggerDto {
        val entityTrigger = entityTriggerRepository.findById(id);
        if (entityTrigger.isPresent)
            return entityTriggerRepository.save(triggerDto.toEntityTrigger())
                .toTriggerDto();
        else
            throw InternalException(ResponseCode.TRIGGER_NOT_FOUND)
    }

    override fun deleteTrigger(id: String) {
        entityTriggerRepository.deleteById(id)
    }
}