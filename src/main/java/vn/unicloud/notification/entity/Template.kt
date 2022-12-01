package vn.unicloud.notification.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import vn.unicloud.notification.enums.TemplateType

@Document("template")
class Template(
    @Id
    var id: String? = null,
    var title: String,
    var description: String,
    var type: TemplateType,
    var content: String,
    var context: Map<String, String> = mutableMapOf()
) : BaseEntity() {
}