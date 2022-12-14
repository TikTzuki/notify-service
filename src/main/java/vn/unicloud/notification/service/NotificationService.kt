package vn.unicloud.notification.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import vn.unicloud.notification.dtos.NotifyRequest
import vn.unicloud.notification.enums.ResponseCode
import vn.unicloud.notification.exception.InternalException
import vn.unicloud.notification.generated.v1.model.MailNotification.SendEmailEvent
import vn.unicloud.notification.repository.EntityTriggerRepository
import vn.unicloud.notification.repository.TemplateRepository

@Service
class NotificationService @Autowired constructor(
    val mailService: MailService,
    val triggerRepository: EntityTriggerRepository,
    val kafkaTemplate: KafkaTemplate<String, SendEmailEvent>,
    val templateRepository: TemplateRepository,
) {

    fun invokeNotify(entity: String, event: String, notifyRequest: NotifyRequest) {
        val entityTriggers = triggerRepository.findByPath("/%s/%s".format(entity, event))
        if (entityTriggers.isEmpty()) {
            throw InternalException(ResponseCode.TRIGGER_NOT_FOUND);
        }
        val trigger = entityTriggers[0]
        val mailEvent = SendEmailEvent.newBuilder()
            .putAllTemplateContext(
                mapOf(Pair("name", "Shen"), Pair("weather", "Sunny"))
            )
            .setTriggerId(trigger.id).build()
        kafkaTemplate.send("emailPaygate", trigger.id!!, mailEvent)
    }
}