package vn.unicloud.notification.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.dtos.TemplateRequestPartDto
import vn.unicloud.notification.entity.Template
import vn.unicloud.notification.enums.ResponseCode
import vn.unicloud.notification.exception.InternalException
import vn.unicloud.notification.mapper.toTemplate
import vn.unicloud.notification.mapper.toTemplateDto
import vn.unicloud.notification.repository.EntityTriggerRepository
import vn.unicloud.notification.repository.TemplateRepository
import vn.unicloud.notification.service.interfaces.ITemplateService
import java.nio.charset.StandardCharsets

@Service
class TemplateService @Autowired constructor(
    val templateRepository: TemplateRepository,
    val triggerRepository: EntityTriggerRepository
) : ITemplateService {
    val log = LoggerFactory.getLogger(this::class.java.canonicalName)

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

    override fun createTemplateFromFile(templateFile: MultipartFile, templateDto: TemplateRequestPartDto): TemplateDto {
        val template = templateDto.toTemplate()
        template.content = String(templateFile.bytes, StandardCharsets.UTF_8)
        return templateRepository.save(template).toTemplateDto()
    }

    override fun deleteTemplate(id: String) {
        if (triggerRepository.existsByTemplateId(id)) {
            throw InternalException(ResponseCode.EXISTED_TRIGGER)
        }
        templateRepository.deleteById(id)
    }

}