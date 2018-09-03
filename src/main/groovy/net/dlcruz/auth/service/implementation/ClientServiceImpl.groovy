package net.dlcruz.auth.service.implementation

import net.dlcruz.auth.api.exception.ObjectValidationException
import net.dlcruz.auth.model.Client
import net.dlcruz.auth.repository.ClientRepository
import net.dlcruz.auth.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl extends BaseEntityService<Client, Long> implements ClientService {

    @Autowired
    ClientRepository repository

    @Autowired
    EntityValidationService validationService

    @Autowired
    MessageResolver messageResolver

    @Override
    Client findByClientId(String clientId) {
        repository.findByClientId(clientId)
    }

    @Override
    ObjectValidationException validate(Client client) {
        def validationException = super.validate(client, false)
        validateUniqueClientId(client, validationException)

        if (validationException.hasError) {
            throw validationException
        }

        validationException
    }

    void validateUniqueClientId(Client client, ObjectValidationException validationException) {
        def existingClient = getExistingClient(client)

        if (existingClient) {
            def fieldName = 'clientId'
            def defaultMessage = messageResolver.getMessage(ObjectValidationException.UNIQUE_CONSTRAINT, [fieldName])
            validationException.pushUniqueConstraint(client, fieldName, existingClient, defaultMessage)
        }
    }


    private Client getExistingClient(Client client) {
        if (client.id == null) {
            repository.findByClientId(client.clientId)
        } else {
            repository.findByClientIdAndIdNot(client.clientId, client.id)
        }
    }
}
