package com.ericmutuku.theportalauthentication.config

import com.ericmutuku.theportalauthentication.oauth2.service.OAuth2UserDetailsService
import com.ericmutuku.theportalauthentication.util.SecurityConstants
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.firewall.HttpFirewall
import org.springframework.security.web.firewall.StrictHttpFirewall
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(val oAuth2UserDetailsService: OAuth2UserDetailsService) : WebSecurityConfigurerAdapter() {

    /*
    *Security config for paths
    */
    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
                .antMatchers(HttpMethod.POST, "/users/validate-phone").permitAll()
                .antMatchers(HttpMethod.POST, "/users/reset-pass").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers( SecurityConstants.AUTH_LOGIN_URL,SecurityConstants.AUTH_ACCESS_URL,SecurityConstants.AUTH_AUTH_URL,SecurityConstants.AUTH_REFRESH_URL).permitAll()
                .antMatchers("/error**").permitAll()
                .antMatchers(HttpMethod.DELETE).denyAll()
                .antMatchers(HttpMethod.TRACE).denyAll()
                .antMatchers(HttpMethod.HEAD).denyAll()
                .antMatchers(HttpMethod.PUT).denyAll()
                .antMatchers(HttpMethod.OPTIONS).denyAll()
                .antMatchers(HttpMethod.PATCH).denyAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll()
                .and()
                .sessionManagement()
                .enableSessionUrlRewriting(true)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().xssProtection().xssProtectionEnabled(true)
        http.httpBasic()
    }
    private val WHITELIST = arrayOf(
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/configuration/ui",
            "/v2/api-docs",
            "/configuration/**",
            "/webjars/**"
    )

    @Throws(java.lang.Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(*WHITELIST)
                web.httpFirewall(looseHttpFirewall())
    }
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Throws(java.lang.Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(oAuth2UserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder())
    }
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
    @Bean(BeanIds.USER_DETAILS_SERVICE)
    @Throws(java.lang.Exception::class)
    override fun userDetailsServiceBean(): UserDetailsService {
        return oAuth2UserDetailsService
    }
    @Bean
    fun looseHttpFirewall(): HttpFirewall? {
        val firewall = StrictHttpFirewall()
        firewall.setAllowedHttpMethods(listOf("GET", "POST"))
        firewall.setAllowSemicolon(true)
        firewall.setAllowUrlEncodedSlash(true)
        firewall.setAllowBackSlash(true)
        firewall.setAllowUrlEncodedDoubleSlash(true)
        firewall.setUnsafeAllowAnyHttpMethod(true)
        firewall.setAllowUrlEncodedPercent(true)
        firewall.setAllowUrlEncodedPeriod(true)
        return firewall
    }
}