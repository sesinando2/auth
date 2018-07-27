package net.dlcruz.auth.model

enum Scope {

    READ, WRITE, TRUST

    @Override
    String toString() {
        name().toLowerCase()
    }
}
