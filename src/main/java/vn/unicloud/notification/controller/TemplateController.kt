package vn.unicloud.notification.controller

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springdoc.api.annotations.ParameterObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import vn.unicloud.notification.core.HttpResponse
import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.service.interfaces.ITemplateService

@RestController
@RequestMapping("/templates")
class TemplateController @Autowired constructor(
    val templateService: ITemplateService
) {
    val log = LoggerFactory.getLogger(this::class.java.canonicalName)

    @PostMapping
    fun postTemplate(template: TemplateDto): HttpResponse<TemplateDto> {
        return HttpResponse(templateService.createTemplate(template))
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách template")
    fun getTemplates(@ParameterObject pageable: Pageable): HttpResponse<Any> {
        return HttpResponse(templateService.getTemplates(pageable))
    }

    @GetMapping("/{id}")
    fun getTemplate(@PathVariable id: String): HttpResponse<TemplateDto> {
        return HttpResponse(templateService.getTemplate(id))
    }

    @DeleteMapping("/{id}")
    fun putTemplate(@PathVariable id: String): HttpResponse<Any> {
        return HttpResponse(templateService.deleteTemplate(id))
    }
}