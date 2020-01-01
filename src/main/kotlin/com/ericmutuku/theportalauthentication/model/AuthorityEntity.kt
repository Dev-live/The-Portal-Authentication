package com.ericmutuku.theportalauthentication.model

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "authorities")
data  class AuthorityEntity (
        @Enumerated(EnumType.STRING)
        @Cascade(CascadeType.SAVE_UPDATE)
        @Column(name = "name", unique = true)
        var name: AuthorityName,
        @Column(name = "description", nullable = true)
        var description :String,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false, updatable = false)
        var id: Int = 0
)
