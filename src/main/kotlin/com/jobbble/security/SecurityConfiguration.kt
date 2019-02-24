package com.jobbble.security

import com.jobbble.user.UserRepository
import com.jobbble.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {


    @Autowired
    private lateinit var service: UserService

    @Autowired
    private lateinit var repository: UserRepository

    @Bean
    fun service(): UserDetailsService {
        return service
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(service).passwordEncoder(
                BCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and()
                .sessionManagement().sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS).and()
                .csrf().disable().exceptionHandling()
                .accessDeniedPage("/access-denied").and()
                .authorizeRequests()
                .antMatchers("/api/login/*").permitAll()
                .antMatchers("/login/*").permitAll()
                .antMatchers("/students/*").permitAll()
                .antMatchers("/hrs/*").permitAll()
                .anyRequest().authenticated().and()
                .addFilter(JsonWebTokenAuthorizationFilter(
                        authenticationManager(), repository))
                .addFilter(JsonWebTokenAuthenticationFilter(
                                authenticationManager(), service))
                .csrf().disable().exceptionHandling()
                .accessDeniedPage("/access-denied").and()
                .httpBasic()
    }
}