package net.dlcruz.auth.config

import net.dlcruz.auth.AuthTokenEnhancer
import net.dlcruz.auth.service.implementation.AuthClientDetailsService
import net.dlcruz.auth.service.implementation.AuthUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.Ordered
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value('${security.security-realm}')
    private String securityRealm

    @Value('${security.signing-key}')
    private String signingKey

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter

    @Autowired
    private AuthenticationManager authenticationManager

    @Autowired
    AuthClientDetailsService clientDetailsService

    @Autowired
    private PasswordEncoder passwordEncoder

    @Autowired
    private AuthUserDetailsService userDetailsService

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

    @Override
    void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService)
    }

    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        def tokenEnhancers = []
        tokenEnhancers.addAll(Arrays.asList(accessTokenConverter))
        tokenEnhancers.add(new AuthTokenEnhancer())

        endpoints.tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter)
            .tokenEnhancer(new TokenEnhancerChain(tokenEnhancers: tokenEnhancers))
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
    }

    @Override
    void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.realm(securityRealm)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    @Bean
    FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        CorsConfiguration config = new CorsConfiguration()
        config.setAllowCredentials(true)
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source))
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
        bean
    }
}
