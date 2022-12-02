package vn.unicloud.notification.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import org.slf4j.LoggerFactory
import org.springdoc.api.annotations.ParameterObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import vn.unicloud.notification.core.HttpResponse
import vn.unicloud.notification.dtos.TemplateDto
import vn.unicloud.notification.dtos.TemplateRequestPartDto
import vn.unicloud.notification.service.interfaces.ITemplateService

@RestController
@RequestMapping("/api/templates")
class TemplateController @Autowired constructor(
    val templateService: ITemplateService
) {
    val log = LoggerFactory.getLogger(this::class.java.canonicalName)

    @PostMapping
    fun post(@RequestBody template: TemplateDto): HttpResponse<TemplateDto> {
        return HttpResponse(templateService.createTemplate(template))
    }

    @PostMapping("/files", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE])
    fun postFile(
        @RequestPart templateFile: MultipartFile,
        @Parameter(content = [Content(mediaType = MediaType.APPLICATION_JSON_VALUE)])
        @RequestPart template: TemplateRequestPartDto
    ):HttpResponse<TemplateDto> {
        return HttpResponse(templateService.createTemplateFromFile(templateFile, template))
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách template")
    fun getList(@ParameterObject pageable: Pageable): HttpResponse<Page<TemplateDto>> {
        return HttpResponse(templateService.getTemplates(pageable))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): HttpResponse<TemplateDto> {
        return HttpResponse(templateService.getTemplate(id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): HttpResponse<Any> {
        return HttpResponse(templateService.deleteTemplate(id))
    }
}