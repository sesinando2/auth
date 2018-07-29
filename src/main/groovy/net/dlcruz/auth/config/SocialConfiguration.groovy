package net.dlcruz.auth.config

import net.dlcruz.auth.social.AuthConnectionSignup
import net.dlcruz.auth.social.AuthSignInAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer
import org.springframework.social.config.annotation.EnableSocial
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository
import org.springframework.social.connect.web.ProviderSignInController
import org.springframework.social.connect.web.ProviderSignInUtils
import org.springframework.social.google.connect.GoogleConnectionFactory

@Configuration
@EnableSocial
class SocialConfiguration extends SocialConfigurerAdapter {

    @Value('${spring.social.google.appId}')
    private String googleAppId

    @Value('${spring.social.google.appSecret}')
    private String googleAppSecret

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator

    @Autowired
    private UsersConnectionRepository usersConnectionRepository

    @Autowired
    private AuthConnectionSignup authConnectionSignup

    @Override
    void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        super.addConnectionFactories(connectionFactoryConfigurer, environment)
        def factory = new GoogleConnectionFactory(googleAppId, googleAppSecret)
        factory.setScope('email profile')
        connectionFactoryConfigurer.addConnectionFactory(factory)
    }

    @Bean
    ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(authConnectionSignup)

        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new AuthSignInAdapter())
    }

    @Bean
    ProviderSignInUtils providerSignInUtils() {
        new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository)
    }
}
