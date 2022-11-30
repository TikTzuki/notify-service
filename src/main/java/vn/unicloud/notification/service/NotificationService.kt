package vn.unicloud.notification.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vn.unicloud.notification.entity.EntityTrigger
import vn.unicloud.notification.enums.ResponseCode
import vn.unicloud.notification.exception.InternalException
import vn.unicloud.notification.repository.EntityTriggerRepository
import vn.unicloud.notification.repository.TemplateRepository

@Service
class NotificationService @Autowired constructor(
    val mailService: MailService,
    val triggerRepository: EntityTriggerRepository,
    val templateRepository: TemplateRepository
    ) {
    fun invokeNotify(entity: String, event: String) {
        val entityTriggers= triggerRepository.findByPath("/%s/%s".format(entity, event))
        if(entityTriggers.isEmpty()){
            throw InternalException(ResponseCode.TRIGGER_NOT_FOUND);
        }
        val trigger = entityTriggers[0]
        val template = templateRepository.findById(trigger.templateId).get()
        mailService.sendMail(template.title, template.content, listOf("tranphanthanhlong18@gmail.com"))
    }
}