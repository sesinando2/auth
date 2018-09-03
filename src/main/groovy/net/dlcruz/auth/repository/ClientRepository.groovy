package net.dlcruz.auth.repository

import net.dlcruz.auth.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientId(String clientId)

    Client findByClientIdAndIdNot(String clientId, Long id)
}