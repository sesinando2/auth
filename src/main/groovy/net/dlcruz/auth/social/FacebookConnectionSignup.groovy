package net.dlcruz.auth.social

import net.dlcruz.auth.model.Role
import net.dlcruz.auth.model.User
import net.dlcruz.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionSignUp
import org.springframework.social.facebook.api.Facebook
import org.springframework.stereotype.Service

@Service
class FacebookConnectionSignup implements ConnectionSignUp {

    @Value('${security.default-password}')
    private String defaultPassword

    @Autowired
    private UserService userService

    @Override
    String execute(Connection<?> connection) {
        Facebook api = connection.api
        def profile = api.fetchObject('me', org.springframework.social.facebook.api.User, ['email'] as String[])
        def user = userService.findByUsername(profile.email)

        if (!user) {
            user = userService.save(new User(username: profile.email, password: connection.key.providerUserId, roles: [Role.USER]))
        }

        user.username
    }
}
