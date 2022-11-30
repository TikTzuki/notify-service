package vn.unicloud.notification.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Service
class MailService @Autowired constructor(
    var thymeleafService: ThymeleafService
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