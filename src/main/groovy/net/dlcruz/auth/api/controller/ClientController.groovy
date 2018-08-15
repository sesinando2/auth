package net.dlcruz.auth.api.controller

import net.dlcruz.auth.model.Client
import net.dlcruz.auth.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/api/client', produces = 'application/json')
class ClientController {

    @Autowired
    ClientService clientService

    @GetMapping
    List<Client> list() {
        clientService.list()
    }
}
