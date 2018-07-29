databaseChangeLog {
    changeSet(id: '1532829012364-1', author: 'nandocruz (generated)') {
        createTable(tableName: 'client') {
            column(name: 'client_id', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'secret', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
        }
    }

    changeSet(id: '1532829012364-2', author: 'nandocruz (generated)') {
        createTable(tableName: 'client_authorized_grant_types') {
            column(name: 'client_client_id', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'authorized_grant_types', type: 'VARCHAR(255)')
        }
    }

    changeSet(id: '1532829012364-3', author: 'nandocruz (generated)') {
        createTable(tableName: 'client_registered_redirect_uris') {
            column(name: 'client_client_id', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'registered_redirect_uris', type: 'VARCHAR(255)')
        }
    }

    changeSet(id: '1532829012364-4', author: 'nandocruz (generated)') {
        createTable(tableName: 'client_resource_ids') {
            column(name: 'client_client_id', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'resource_ids', type: 'VARCHAR(255)')
        }
    }

    changeSet(id: '1532829012364-5', author: 'nandocruz (generated)') {
        createTable(tableName: 'client_roles') {
            column(name: 'client_client_id', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'roles', type: 'VARCHAR(255)')
        }
    }

    changeSet(id: '1532829012364-6', author: 'nandocruz (generated)') {
        createTable(tableName: 'client_scope') {
            column(name: 'client_client_id', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'scope', type: 'VARCHAR(255)')
        }
    }

    changeSet(id: '1532829012364-7', author: 'nandocruz (generated)') {
        createTable(tableName: 'user') {
            column(name: 'id', type: 'BIGINT', autoIncrement: true) {
                constraints(primaryKey: true)
            }
            column(name: 'password', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
            column(name: 'username', type: 'VARCHAR(255)') {
                constraints(nullable: false)
            }
        }
    }

    changeSet(id: '1532829012364-8', author: 'nandocruz (generated)') {
        createTable(tableName: 'user_roles') {
            column(name: 'user_id', type: 'BIGINT') {
                constraints(nullable: false)
            }
            column(name: 'roles', type: 'VARCHAR(255)')
        }
    }

    changeSet(id: '1532829012364-9', author: 'nandocruz (generated)') {
        addPrimaryKey(columnNames: 'client_id', constraintName: 'PRIMARY', tableName: 'client')
    }

    changeSet(id: '1532829012364-10', author: 'nandocruz (generated)') {
        addUniqueConstraint(columnNames: 'username', constraintName: 'UK_sb8bbouer5wak8vyiiy4pf2bx', tableName: 'user')
    }

    changeSet(id: '1532829012364-11', author: 'nandocruz (generated)') {
        createIndex(indexName: 'FK50hu8pj8lb1rpfv0o43a60d5i', tableName: 'client_authorized_grant_types') {
            column(name: 'client_client_id')
        }
    }

    changeSet(id: '1532829012364-12', author: 'nandocruz (generated)') {
        createIndex(indexName: 'FK55itppkw3i07do3h7qoclqd4k', tableName: 'user_roles') {
            column(name: 'user_id')
        }
    }

    changeSet(id: '1532829012364-13', author: 'nandocruz (generated)') {
        createIndex(indexName: 'FKlalsu54007uphf98lqaaaw03', tableName: 'client_resource_ids') {
            column(name: 'client_client_id')
        }
    }

    changeSet(id: '1532829012364-14', author: 'nandocruz (generated)') {
        createIndex(indexName: 'FKnxkf18pdc7is2pcfonoy5tct2', tableName: 'client_roles') {
            column(name: 'client_client_id')
        }
    }

    changeSet(id: '1532829012364-15', author: 'nandocruz (generated)') {
        createIndex(indexName: 'FKo464c8na0x3dy626ctx0wyami', tableName: 'client_registered_redirect_uris') {
            column(name: 'client_client_id')
        }
    }

    changeSet(id: '1532829012364-16', author: 'nandocruz (generated)') {
        createIndex(indexName: 'FKtnwpd3ope97np8tprkmg3pm23', tableName: 'client_scope') {
            column(name: 'client_client_id')
        }
    }

    changeSet(id: '1532829012364-17', author: 'nandocruz (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'client_client_id', baseTableName: 'client_authorized_grant_types', constraintName: 'FK50hu8pj8lb1rpfv0o43a60d5i', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'client_id', referencedTableName: 'client')
    }

    changeSet(id: '1532829012364-18', author: 'nandocruz (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'user_id', baseTableName: 'user_roles', constraintName: 'FK55itppkw3i07do3h7qoclqd4k', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'user')
    }

    changeSet(id: '1532829012364-19', author: 'nandocruz (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'client_client_id', baseTableName: 'client_resource_ids', constraintName: 'FKlalsu54007uphf98lqaaaw03', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'client_id', referencedTableName: 'client')
    }

    changeSet(id: '1532829012364-20', author: 'nandocruz (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'client_client_id', baseTableName: 'client_roles', constraintName: 'FKnxkf18pdc7is2pcfonoy5tct2', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'client_id', referencedTableName: 'client')
    }

    changeSet(id: '1532829012364-21', author: 'nandocruz (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'client_client_id', baseTableName: 'client_registered_redirect_uris', constraintName: 'FKo464c8na0x3dy626ctx0wyami', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'client_id', referencedTableName: 'client')
    }

    changeSet(id: '1532829012364-22', author: 'nandocruz (generated)') {
        addForeignKeyConstraint(baseColumnNames: 'client_client_id', baseTableName: 'client_scope', constraintName: 'FKtnwpd3ope97np8tprkmg3pm23', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'client_id', referencedTableName: 'client')
    }

}
