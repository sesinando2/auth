package net.dlcruz.auth

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer

class AuthTokenEnhancer implements TokenEnhancer {

    @Override
    OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation([authorities: authentication.authorities*.authority])
        accessToken
    }
}
