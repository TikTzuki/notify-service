package vn.unicloud.notification.dtos


class NotifyRequest(
    var recipients: List<String> = listOf(),
    var template_value: Map<String, String> = mapOf()
)