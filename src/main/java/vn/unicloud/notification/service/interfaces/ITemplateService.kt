package vn.unicloud.notification.service.interfaces

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import vn.unicloud.notification.dtos.TemplateDto
import java.util.*

interface ITemplateService {
    fun createTemplate(templateDto: TemplateDto): TemplateDto
    fun getTemplates(pageable: Pageable): Page<TemplateDto>
    fun getTemplate(id: String): TemplateDto
    fun deleteTemplate(id: String)
}