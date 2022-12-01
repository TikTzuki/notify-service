package vn.unicloud.notification.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import vn.unicloud.notification.entity.EntityTrigger
import vn.unicloud.notification.entity.Template
import vn.unicloud.notification.generated.v1.model.MailNotification.SendEmailEvent
import vn.unicloud.notification.repository.EntityTriggerRepository
import vn.unicloud.notification.repository.NotificationRepository
import vn.unicloud.notification.repository.TemplateRepository
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Service
class MailService @Autowired constructor(
    val thymeleafService: ThymeleafService,
    val triggerRepository: EntityTriggerRepository,
    val notificationRepository: NotificationRepository,
    val templateRepository: TemplateRepository
) {
    private val CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\""

    @Value("\${spring.mail.host}")
    private val host: String? = null

    @Value("\${spring.mail.port}")
    private val port: String? = null

    @Value("\${spring.mail.username}")
    private val email: String? = null

    @Value("\${spring.mail.password}")
    private val password: String? = null

    fun sendMail(sendMailEvent: SendEmailEvent) {
        val triggerOpt = triggerRepository.findById(sendMailEvent.triggerId);
        triggerOpt.ifPresentOrElse({
            val template = templateRepository.findById(it.templateId).get()
            val context = Context()
            context.setVariables(sendMailEvent.templateContextMap as Map<String, Any>?)
            val emailContent = ThymeleafService.templateEngine.process(template.content, context)
            bulkSendMail(it, template, emailContent)
        }, {
            System.out.println("trigger not found")
        })

    }

    fun bulkSendMail(trigger: EntityTrigger, template: Template, content: String) {
        println("bulk send mail")
        println(trigger.emails)
        trigger.emails = listOf("tranphanthanhlong18@gmail.com")
        trigger.emails.chunked(10).forEach {
            sendMail(template.title, content, it)
        }
    }

    fun sendMail(subject: String, content: String, recipients: Iterable<String>) {
        val props = Properties()
        props.put("mail.smtp.host", host)
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.port", port)

        val session: Session = Session.getInstance(props,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(email, password)
                }
            }
        )

        val message: Message = MimeMessage(session)
        println("before send")
        try {
            message.setRecipients(Message.RecipientType.TO, recipients.map { InternetAddress(it) }.toTypedArray())
            message.setFrom(InternetAddress(email))
            message.setSubject(subject)
            message.setContent(content, CONTENT_TYPE_TEXT_HTML)
            Transport.send(message)
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }
}