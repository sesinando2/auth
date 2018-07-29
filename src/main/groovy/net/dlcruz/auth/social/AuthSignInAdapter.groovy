package net.dlcruz.auth.social

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.security.web.savedrequest.SavedRequest
import org.springframework.social.connect.Connection
import org.springframework.social.connect.web.SignInAdapter
import org.springframework.web.context.request.NativeWebRequest

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthSignInAdapter implements SignInAdapter {

    @Override
    String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        def profile = SocialUtils.extractProfile(connection)
        SecurityContextHolder.context.authentication = new PreAuthenticatedAuthenticationToken(profile.email, connection.key.providerUserId, [new SimpleGrantedAuthority('USER')])

        HttpServletRequest req = request.nativeRequest
        HttpServletResponse resp = request.nativeResponse

        RequestCache rc = new HttpSessionRequestCache()
        SavedRequest savedRequest = rc.getRequest(req, resp)
        savedRequest?.redirectUrl
    }
}
