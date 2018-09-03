package net.dlcruz.auth.service

import net.dlcruz.auth.model.Client

interface ClientService extends EntityService<Client, Long> {

    Client findByClientId(String clientId)
}
