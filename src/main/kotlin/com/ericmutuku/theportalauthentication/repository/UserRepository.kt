package com.ericmutuku.theportalauthentication.repository

import com.ericmutuku.theportalauthentication.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByPhoneNumber(phoneNumber: String): UserEntity
}