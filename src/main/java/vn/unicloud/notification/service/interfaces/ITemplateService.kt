package vn.unicloud.notification.service.interfaces

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile
import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.dtos.TemplateRequestPartDto

interface ITemplateService {
    fun createTemplate(templateDto: TemplateDto): TemplateDto
    fun createTemplateFromFile(templateFile: MultipartFile, templateDto: TemplateRequestPartDto): TemplateDto
    fun getTemplates(pageable: Pageable): Page<TemplateDto>
    fun getTemplate(id: String): TemplateDto
    fun deleteTemplate(id: String)
}