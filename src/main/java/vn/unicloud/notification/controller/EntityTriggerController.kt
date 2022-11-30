package vn.unicloud.notification.controller

import io.swagger.v3.oas.annotations.Operation
import org.springdoc.api.annotations.ParameterObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import vn.unicloud.notification.core.HttpResponse
import vn.unicloud.notification.dtos.TriggerDto
import vn.unicloud.notification.service.interfaces.IEntityTriggerService

@RestController
@RequestMapping("/triggers")
class EntityTriggerController @Autowired constructor(
    val triggerService: IEntityTriggerService
) {

    @PostMapping
    fun post(@RequestBody trigger: TriggerDto): HttpResponse<TriggerDto> {
        return HttpResponse(triggerService.createTrigger(trigger))
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách template")
    fun getList(@ParameterObject pageable: Pageable): HttpResponse<Any> {
        return HttpResponse(triggerService.getTriggers(pageable))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): HttpResponse<TriggerDto> {
        return HttpResponse(triggerService.getTrigger(id))
    }

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody trigger: TriggerDto): HttpResponse<TriggerDto> {
        return HttpResponse(triggerService.updateTrigger(id, trigger))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): HttpResponse<Any> {
        return HttpResponse(triggerService.deleteTrigger(id))
    }
}