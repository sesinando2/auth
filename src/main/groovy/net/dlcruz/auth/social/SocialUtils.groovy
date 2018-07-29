package net.dlcruz.auth.social

import org.springframework.social.connect.Connection
import org.springframework.social.connect.UserProfile
import org.springframework.social.facebook.api.Facebook

class SocialUtils {

    static UserProfile extractProfile(Connection<Facebook> connection) {
        switch (connection.api) {
            case Facebook:
                return extractProfileFor(connection.api)
            default:
                return connection.fetchUserProfile()
        }
    }

    private static UserProfile extractProfileFor(Facebook api) {
        def fields = ['name', 'first_name', 'last_name', 'id', 'email'] as String[]
        def user = api.fetchObject('me', org.springframework.social.facebook.api.User, fields)
        new UserProfile(user.name, user.firstName, user.lastName, user.email, user.email)
    }
}
