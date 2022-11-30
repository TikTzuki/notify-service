package vn.unicloud.notification.repository

import org.springframework.data.mongodb.repository.MongoRepository
import vn.unicloud.notification.entity.Template

interface TemplateRepository : MongoRepository<Template, String>