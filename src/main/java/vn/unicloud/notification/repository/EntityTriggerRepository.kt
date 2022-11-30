package vn.unicloud.notification.repository

import org.springframework.data.mongodb.repository.MongoRepository
import vn.unicloud.notification.entity.EntityTrigger

interface EntityTriggerRepository : MongoRepository<EntityTrigger, String> {
    fun existsByTemplateId(templateId: String): Boolean
    fun findByPath(path: String): List<EntityTrigger>
}