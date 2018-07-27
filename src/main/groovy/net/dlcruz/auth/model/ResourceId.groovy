package net.dlcruz.auth.model

enum ResourceId {

    AUTH, FINANCE

    @Override
    String toString() {
        name().toLowerCase()
    }
}
