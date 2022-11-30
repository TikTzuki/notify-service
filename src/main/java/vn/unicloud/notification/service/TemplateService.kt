package vn.unicloud.notification.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.entity.Template
import vn.unicloud.notification.enums.ResponseCode
import vn.unicloud.notification.exception.InternalException
import vn.unicloud.notification.mapper.toTemplate
import vn.unicloud.notification.mapper.toTemplateDto
import vn.unicloud.notification.repository.TemplateRepository
import vn.unicloud.notification.service.interfaces.ITemplateService

@Service
class TemplateService @Autowired constructor(
    val templateRepository: TemplateRepository
) : ITemplateService {

    override fun getTemplates(pageable: Pageable): Page<TemplateDto> {
        return templateRepository.findAll(pageable).map(Template::toTemplateDto)
    }

    override fun getTemplate(id: String): TemplateDto {
        return templateRepository.findById(id)
            .map(Template::toTemplateDto)
            .orElseThrow { InternalException(ResponseCode.TEMPLATE_NOT_FOUND) };
    }

    override fun createTemplate(templateDto: TemplateDto): TemplateDto {
        return templateRepository.save(templateDto.toTemplate())
            .toTemplateDto()
    }

    override fun deleteTemplate(id: String) {
        templateRepository.deleteById(id)
    }

}