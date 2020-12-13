package me.antandtim.mad12.config

import me.antandtim.mad12.user.provider.UserProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig(private val userProvider: UserProvider) : WebSecurityConfigurerAdapter() {
    
    override fun configure(web: WebSecurity) {
        web
            .ignoring()
            .antMatchers(HttpMethod.POST, "/user")
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/v2/api-docs")
            .antMatchers("/configuration/ui")
            .antMatchers("/swagger-resources/**")
            .antMatchers("/configuration/security")
            .antMatchers("/swagger-ui.html")
            .antMatchers("/webjars/**")
    }
    
    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .authenticationProvider(userProvider)
            .httpBasic()
            .and()
            .cors().disable()
            .csrf().disable()
    }
}

@Configuration
class PasswordEncoderConfig {
    
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}