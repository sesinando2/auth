package net.dlcruz.auth.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

import java.security.Principal

@Controller
class AuthenticationController {

    @GetMapping('/login')
    String login(Principal principal) {
        if (principal) {
            return 'redirect:/'
        }

        'login'
    }
}
