package net.dlcruz.auth.config

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        def referer = request.getHeader('referer')
        if (referer) {
            response.sendRedirect(referer)
        }
    }
}
