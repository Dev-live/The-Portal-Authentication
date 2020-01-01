package com.ericmutuku.theportalauthentication.oauth2.model

import com.ericmutuku.theportalauthentication.model.UserEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors

class OAuth2UserDetails : UserDetails {
    /*
    * Authorization Object
    */

    private var phoneNumber: String

    constructor(id: UUID, firstName: String, phoneNumber: String, active: Boolean, password: String, email: String, authority: MutableCollection<SimpleGrantedAuthority>) {
        this.id =id
        this.phoneNumber = phoneNumber
        this.name = firstName
        this.active = active
        this.email = email
        this.password = password
        this.authorities = authority
    }

    private var id: UUID

    private var name: String

    private var active :Boolean

    @JsonIgnore
    private var password: String
    private  var email:String
    private var authorities:Collection<GrantedAuthority?>


    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    override fun isEnabled(): Boolean {
       return active
    }

    override fun getUsername(): String {
        return phoneNumber
    }

    override fun isCredentialsNonExpired(): Boolean {
        return active
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return active
    }

    override fun isAccountNonLocked(): Boolean {
        return active
    }
    fun getId(): UUID {
        return id
    }
    fun getName(): String? {
        return name
    }

    fun getEmail(): String? {
        return email
    }
companion object {
    fun build(userEntity: UserEntity): OAuth2UserDetails {
        /* val serialVersionUID = 1L */
        val authority = userEntity.authorities.stream().map { authorityEntity -> SimpleGrantedAuthority(authorityEntity.name.name) }.collect(Collectors.toSet())

        return userEntity.id?.let {
            OAuth2UserDetails(
                    it,
                    userEntity.firstName,
                    userEntity.phoneNumber,
                    userEntity.active,
                    userEntity.password,
                    userEntity.email,
                    authority
            )
        }!!
    }

}


}
