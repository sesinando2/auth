package net.dlcruz.auth.service

import net.dlcruz.auth.model.Client
import net.dlcruz.auth.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    private ClientRepository clientRepository

    @Autowired
    private PasswordEncoder passwordEncoder

    Client findByClientId(String clientId) {
        clientRepository.findByClientId(clientId)
    }

    Client save(Client client) {
        client.secret = passwordEncoder.encode(client.secret)
        clientRepository.save(client)
    }
}
