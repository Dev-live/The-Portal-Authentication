package com.ericmutuku.theportalauthentication.oauth2.service

import com.ericmutuku.theportalauthentication.model.UserEntity
import com.ericmutuku.theportalauthentication.repository.UserRepository
import com.ericmutuku.theportalauthentication.oauth2.model.OAuth2UserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class OAuth2UserDetailsService(private val userRepository: UserRepository): UserDetailsService {
    /*
    * Fetch User entity from Database for authentication
    */
    @Throws(UsernameNotFoundException::class)
    @Transactional
    override fun loadUserByUsername(phoneNumber: String): UserDetails {
       val userEntity : UserEntity = userRepository.findByPhoneNumber(phoneNumber)
        return OAuth2UserDetails.build(userEntity)

    }

}