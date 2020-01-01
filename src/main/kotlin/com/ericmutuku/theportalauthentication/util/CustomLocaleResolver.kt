package com.ericmutuku.theportalauthentication.util

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * class that will be responsible for defining user’s locale.
 */
@Configuration
class CustomLocaleResolver : AcceptHeaderLocaleResolver() {
    // We have 2 locales supported in the project: en(English) and mya(Burmese).
    private val LOCALES = listOf(
            Locale("en")
    )

    /**
     * The locale should be passed in the header called “Accept-Language”
     * So, if the header is present and it is not empty, we will use locale from it, otherwise — we’ll use default locale, which is en.
     */
    override fun resolveLocale(request: HttpServletRequest): Locale {
        val headerLang = request.getHeader("Accept-Language")
        return if (headerLang == null || headerLang.isEmpty()) Locale.getDefault() else Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES)
    }

    /**
     * An application context delegates the message resolution to a bean with the exact name messageSource.
     */
    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val rs = ResourceBundleMessageSource()
        rs.setBasename("messages")
        rs.setDefaultEncoding("UTF-8")
        rs.setUseCodeAsDefaultMessage(true)
        return rs
    }

    /**
     * To use custom name messages in a properties file like we need to define a LocalValidatorFactoryBean and register the messageSource:
     * if we had already extended the WebMvcConfigurerAdapter, to avoid having the custom validator ignored, we’d have to set the validator by overriding the getValidator() method from the parent class.
     */
    @Bean
    fun validator(): LocalValidatorFactoryBean {
        val bean = LocalValidatorFactoryBean()
        bean.setValidationMessageSource(messageSource())
        return bean
    }
}