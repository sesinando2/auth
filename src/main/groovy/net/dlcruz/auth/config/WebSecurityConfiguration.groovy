package net.dlcruz.auth.config

import net.dlcruz.auth.service.implementation.AuthUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthUserDetailsService userDetailsService

    @Autowired
    private PasswordEncoder passwordEncoder

    @Autowired
    private AuthLogoutSuccessHandler logoutSuccessHandler

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers('/login/**', '/signin/**', '/signup/**', '/actuators/**', '/api-docs/**').permitAll()
                .anyRequest().authenticated()
                .and()

            .formLogin()
                .loginPage('/login').permitAll()
                .defaultSuccessUrl('/')
                .and()

                .logout().permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()

            .csrf()
                .disable()
    }

    @Override
    void configure(WebSecurity web) throws Exception {
        web
            .ignoring().antMatchers('/**/**.css', '/**/**.js', '/webjars/**', '/images/*/**')
    }
}
