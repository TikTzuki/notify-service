package vn.unicloud.notification.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vn.unicloud.notification.service.NotificationService

@RestController
@RequestMapping("/notifications")
class NotificationController @Autowired constructor(
    val notificationService: NotificationService
) {
    @PostMapping("/{entity}/{event}")
    fun invokeNotify(entity: String, event: String) {
        notificationService.invokeNotify(entity, event)
    }
}