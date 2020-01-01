package com.ericmutuku.theportalauthentication.util

class SecurityConstants private constructor() {
    companion object {
        const val AUTH_AUTH_URL = "/oauth/authorize"
        const val AUTH_ACCESS_URL = "/oauth/access_token"
        const val AUTH_LOGIN_URL = "/oauth/token"
        const val AUTH_REFRESH_URL = "/oauth/check_token"
    }

    init {
        throw IllegalStateException("Cannot create instance of static util class")
    }
}