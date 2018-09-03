package net.dlcruz.auth.service.implementation

import net.dlcruz.auth.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.ClientRegistrationException
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientService clientService

    @Override
    ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        def client = clientService.findByClientId(clientId)

        if (!client) throw new ClientRegistrationException("Cannot find client with clientId: $clientId")

        new BaseClientDetails(
            clientId: client.clientId,
            resourceIds: client.resourceIds*.toString(),
            clientSecret: client.secret,
            authorizedGrantTypes: client.authorizedGrantTypes*.toString(),
            scope: client.scope*.toString(),
            authorities: client.roles.collect { new SimpleGrantedAuthority(it.name()) },
            registeredRedirectUris: client.registeredRedirectUris*.toString(),
            autoApproveScopes: client.scope*.toString()
        )
    }
}
