package vn.unicloud.notification.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import vn.unicloud.notification.dtos.NotifyRequest
import vn.unicloud.notification.service.NotificationService

@RestController
@RequestMapping("/notifications")
class NotificationController @Autowired constructor(
    val notificationService: NotificationService
) {
    @PostMapping("/{entity}/{event}")
    fun invokeNotify(
        @PathVariable entity: String,
        @PathVariable event: String,
        @RequestBody notifyRequest: NotifyRequest
    ) {
        notificationService.invokeNotify(entity, event, notifyRequest)
    }
}