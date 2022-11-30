package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("template")
class Template(
    @Id
    var id: String? = null,
    var title: String,
    var description: String,
    var type: String,
    var sourceType: String,
    var content: String,
): BaseEntity() {
}