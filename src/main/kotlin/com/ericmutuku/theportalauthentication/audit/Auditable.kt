package com.ericmutuku.theportalauthentication.audit

import lombok.AccessLevel
import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class Auditable<U> {
    @CreatedBy
    @Column(name = "created_by")
    var createdBy: U? = null
    @CreatedDate
    @Column(name = "created_date")
    var createdDate: Date? = null
    @LastModifiedBy
    @Column(name = "last_modified_by")
    var lastModifiedBy: U? = null
    @LastModifiedDate
    @Column(name = "last_modified_date")
    var lastModifiedDate: Date? = null

}