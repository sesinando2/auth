package net.dlcruz.auth.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
@RequestMapping(value = ['/api/me'], produces = 'application/json')
class CurrentUserController {

    @GetMapping
    Map<String, String> user(Principal principal) {
        [name: principal.name]
    }
}
