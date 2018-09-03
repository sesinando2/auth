package net.dlcruz.auth.api.controller

import net.dlcruz.auth.model.User
import net.dlcruz.auth.service.implementation.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/api/user', produces = 'application/json')
class UserController {

    @Autowired
    UserService userService

    @GetMapping
    List<User> list() {
        userService.list()
    }
}
