package net.dlcruz.auth.service.implementation

import net.dlcruz.auth.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class BootstrapService {

    @Value('${security.default-password}')
    private String defaultPassword

    @Autowired
    private UserService userService

    @Autowired
    private ClientServiceImpl clientService

    void initialiseDefaults() {
        initialiseDefaultUsers()
        initialiseDefaultClient()
    }

    void initialiseDefaultUsers() {
        def adminUser = new User(username: 'admin', password: defaultPassword, roles: Role.values())

        if (!userService.findByUsername(adminUser.username)) {
            userService.save(adminUser)
        }
    }

    void initialiseDefaultClient() {
        def authClient = new Client(
                clientId: 'admin',
                resourceIds: ResourceId.values(),
                secret: defaultPassword,
                authorizedGrantTypes: GrantType.values(),
                scope: Scope.values(),
                roles: Role.values())

        createClient(authClient)

        def financeClient = new Client(
                clientId: 'finance',
                resourceIds: ResourceId.values(),
                secret: defaultPassword,
                authorizedGrantTypes: GrantType.values(),
                scope: Scope.values(),
                roles: Role.values(),
                registeredRedirectUris: ['http://localhost:4200'])

        createClient(financeClient)

        def financeClientIntegration = new Client(
                clientId: 'finance-integration',
                resourceIds: ResourceId.values(),
                secret: defaultPassword,
                authorizedGrantTypes: GrantType.values(),
                scope: Scope.values(),
                roles: Role.values(),
                registeredRedirectUris: ['http://localhost:4200'])

        createClient(financeClientIntegration)
    }

    private void createClient(Client client) {
        if (!clientService.findByClientId(client.clientId)) {
            clientService.save(client)
        }
    }
}
