package vn.unicloud.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import vn.unicloud.notification.generated.v1.model.MailNotification.SendEmailEvent;

@SpringBootApplication
@EnableMongoAuditing
@EnableKafka
class NotificationServiceApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Autowired
    KafkaTemplate<String, SendEmailEvent> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        SendEmailEvent email = SendEmailEvent.newBuilder()
                .setTriggerId("10")
                .build();
        kafkaTemplate.send("emailPaygate", email.getTriggerId(), email);
    }
}