package net.dlcruz.auth.service.implementation

import net.dlcruz.auth.model.User
import net.dlcruz.auth.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private UserRepository userRepository

    @Autowired
    private PasswordEncoder passwordEncoder

    User findByUsername(String username) {
        userRepository.findByUsername(username)
    }

    User save(User user) {
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
    }

    List<User> list() {
        userRepository.findAll()
    }

    void delete(Long id) {
        userRepository.delete(id)
    }
}
