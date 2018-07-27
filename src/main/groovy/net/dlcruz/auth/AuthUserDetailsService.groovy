package net.dlcruz.auth

import net.dlcruz.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService

    @Autowired
    private PasswordEncoder passwordEncoder

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        def user = userService.findByUsername(username)

        if (!user) throw new UsernameNotFoundException("User $username not found")

        User.withUsername(user.username)
            .password(user.password)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .roles(user.roles*.name() as String[])
            .build()
    }
}
