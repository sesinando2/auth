package net.dlcruz.auth.repository

import net.dlcruz.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username)
}
