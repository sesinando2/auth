package net.dlcruz.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
@RequestMapping(value = ['/user', '/me'], produces = 'application/json')
class UserController {

    @GetMapping
    Map<String, String> user(Principal principal) {
        [name: principal.name]
    }
}
