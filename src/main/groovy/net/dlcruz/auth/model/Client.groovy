package net.dlcruz.auth.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Client {

    @Id
    @NotNull
    @Column(unique = true)
    String clientId

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<ResourceId> resourceIds

    @NotNull
    String secret

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<GrantType> authorizedGrantTypes

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<Scope> scope

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<Role> roles

    @ElementCollection
    Set<String> registeredRedirectUris
}
