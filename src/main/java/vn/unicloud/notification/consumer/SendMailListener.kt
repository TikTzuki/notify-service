package vn.unicloud.notification.consumer

import com.google.protobuf.DynamicMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import vn.unicloud.notification.generated.v1.model.MailNotification.SendEmailEvent
import vn.unicloud.notification.service.EntityTriggerService
import vn.unicloud.notification.service.MailService


@Service
class SendMailListener(
    val mailService: MailService,
    val triggerService: EntityTriggerService
) {
    val log = LoggerFactory.getLogger(this::class.java.canonicalName)

    @KafkaListener(topicPattern = "email.*")
    fun consume(message: ConsumerRecord<String, DynamicMessage>) {
        val emailEvent = SendEmailEvent.newBuilder().build().parserForType.parseFrom(
            message.value().toByteArray()
        )
        mailService.sendMail(emailEvent)
    }
}