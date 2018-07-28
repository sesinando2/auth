package net.dlcruz.auth.config

import net.dlcruz.auth.service.AuthClientDetailsService
import net.dlcruz.auth.AuthTokenEnhancer
import net.dlcruz.auth.service.AuthUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value('${security.security-realm}')
    private String securityRealm

    @Autowired
    private TokenStore tokenStore

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

    @Override
    void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService)
    }

    @Override
    void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        def tokenEnhancers = []
        tokenEnhancers.addAll(Arrays.asList(accessTokenConverter))
        tokenEnhancers.add(new AuthTokenEnhancer())

        endpoints.tokenStore(tokenStore)
            .accessTokenConverter(accessTokenConverter)
            .tokenEnhancer(new TokenEnhancerChain(tokenEnhancers: tokenEnhancers))
            .authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
    }

    @Override
    void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.realm(securityRealm)
                .passwordEncoder(passwordEncoder)
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
