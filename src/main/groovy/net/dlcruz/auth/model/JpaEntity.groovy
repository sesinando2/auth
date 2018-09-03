package net.dlcruz.auth.model

interface JpaEntity<IdType> {

    IdType getId()
}