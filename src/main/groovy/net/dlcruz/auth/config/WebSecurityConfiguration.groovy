package net.dlcruz.auth.config

import net.dlcruz.auth.service.AuthUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value('${security.signing-key}')
    private String signingKey

    @Autowired
    private AuthUserDetailsService userDetailsService

    @Autowired
    private PasswordEncoder passwordEncoder

    @Autowired
    private AuthLogoutSuccessHandler logoutSuccessHandler

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        new JwtAccessTokenConverter(signingKey: signingKey)
    }

    @Bean
    JwtTokenStore tokenStore() {
        new JwtTokenStore(accessTokenConverter())
    }

    @Bean
    @Primary
    DefaultTokenServices tokenServices() {
        new DefaultTokenServices(tokenStore: tokenStore(), supportRefreshToken: true)
    }

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
            .formLogin()
                .loginPage('/login')
                .defaultSuccessUrl('/')
                .permitAll()
                .and()

            .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()

            .csrf()
                .disable()

            .authorizeRequests()
                .antMatchers('/login/**', '/signin/**', '/signup/**')
                .permitAll()

                .anyRequest()
                .authenticated()
    }

    @Override
    void configure(WebSecurity web) throws Exception {
        web
            .ignoring().antMatchers('/**/**.css', '/**/**.js', '/webjars/**', '/images/*/**')
    }
}
