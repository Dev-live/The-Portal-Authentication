package com.ericmutuku.theportalauthentication.audit

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditingConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return SpringSecurityAuditAwareImpl()
    }
}

internal class SpringSecurityAuditAwareImpl() : AuditorAware<String> {
    /**
     * get curent Auditor
     * @return Optional<String>
     */
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable("Yielder-V2")
    }


}

