package com.ericmutuku.theportalauthentication.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component

/**
 * class that will be responsible for choosing right message according to specified locale
 */
@Component
class Translator @Autowired internal constructor(messageSource: ResourceBundleMessageSource?) {
    companion object {
        private var messageSource: ResourceBundleMessageSource? = null
        /**
         * accept message code that should be translated.
         */
        @JvmStatic
        fun toLocale(msgCode: String?, vararg args: Any?): String {
            val locale = LocaleContextHolder.getLocale()
            // messageSource.getMessage(…) accepts parameter “msg”. But this is not exactly the message that should be translated. It is just message code.
            return messageSource!!.getMessage(msgCode!!, args, locale)
        }
    }

    init {
        Companion.messageSource = messageSource
    }
}