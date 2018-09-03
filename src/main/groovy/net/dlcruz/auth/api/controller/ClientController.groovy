package net.dlcruz.auth.api.controller

import net.dlcruz.auth.model.Client
import net.dlcruz.auth.model.GrantType
import net.dlcruz.auth.model.ResourceId
import net.dlcruz.auth.model.Role
import net.dlcruz.auth.model.Scope
import net.dlcruz.auth.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = '/api/client', produces = 'application/json')
class ClientController extends BaseEntityController<Client, Long> {

    @Autowired
    ClientService service

    @Override
    protected setProperties(Client entity, Map properties) {
        def enumFields = [resourceIds: ResourceId, authorizedGrantTypes: GrantType, scope: Scope, roles: Role]
        enumFields.each { String property, Class enumType ->
            properties[property]?.with { List<String>  values ->
                properties[property] = toEnumList(enumType, values)
            }
        }
        return super.setProperties(entity, properties)
    }
}
