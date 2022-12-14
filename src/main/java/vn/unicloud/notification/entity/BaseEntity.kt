package vn.unicloud.notification.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime


abstract class BaseEntity(
    @CreatedBy
    var createdBy: String? = null,
    @LastModifiedBy
    var updatedBy: String? = null,
    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null,
) {
}