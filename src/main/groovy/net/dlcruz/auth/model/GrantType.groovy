package net.dlcruz.auth.model

enum GrantType {

    IMPLICIT, AUTHORIZATION_CODE, CLIENT_CREDENTIALS, PASSWORD, REFRESH_TOKEN

    @Override
    String toString() {
        name().toLowerCase()
    }
}
