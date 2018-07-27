package net.dlcruz.auth.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @NotNull
    @Column(unique = true)
    String username

    @NotNull
    String password

    @ElementCollection
    @Enumerated(EnumType.STRING)
    Set<Role> roles
}
