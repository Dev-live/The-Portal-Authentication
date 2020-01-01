package com.ericmutuku.theportalauthentication.model

import com.ericmutuku.theportalauthentication.audit.Auditable
import lombok.AccessLevel
import lombok.Getter
import lombok.Setter
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name = "USERS")
data class UserEntity(
        @Column
        @get:Length(min = 3, max = 50 )
        @get:NotBlank
        var firstName: String,
        @Column(nullable = true)
        var lastName: String?,
        @Column
        var profession: String,
        @Column
        @get:Email
        var email: String,
        @Column
        @get:Length(min=4 ,max= 100)
        var password: String,
        @Column
        var gender: String,
        @Column(unique=true ,nullable = false)
        @Length(min= 10, max =13)
        @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
                message="Mobile number is invalid")
        var phoneNumber: String,
        @Column
        var location: String,
        @Column
        var longitude: String,
        @Column
        var latitude: String,
        @Column
        var activity: String,
        @Column
        var active: Boolean,
        @ManyToMany(fetch=FetchType.EAGER)
        val  authorities: Set<AuthorityEntity> = mutableSetOf(),
        @Id
        @Length(min = 10)
        @GeneratedValue(strategy = GenerationType.AUTO)

        val id: UUID? = null

): Auditable<String>()
