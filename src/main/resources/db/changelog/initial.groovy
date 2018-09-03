databaseChangeLog {
  changeSet(id: '1535695013099-1', author: 'nandocruz (generated)') {
    createTable(tableName: 'client') {
      column(name: 'id', type: 'BIGINT', autoIncrement: true) {
        constraints(primaryKey: true, primaryKeyName: 'clientPK')
      }
      column(name: 'client_id', type: 'VARCHAR(255)')
      column(name: 'secret', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-2', author: 'nandocruz (generated)') {
    createTable(tableName: 'client_authorized_grant_types') {
      column(name: 'client_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'authorized_grant_types', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-3', author: 'nandocruz (generated)') {
    createTable(tableName: 'client_registered_redirect_uris') {
      column(name: 'client_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'registered_redirect_uris', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-4', author: 'nandocruz (generated)') {
    createTable(tableName: 'client_resource_ids') {
      column(name: 'client_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'resource_ids', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-5', author: 'nandocruz (generated)') {
    createTable(tableName: 'client_roles') {
      column(name: 'client_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'roles', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-6', author: 'nandocruz (generated)') {
    createTable(tableName: 'client_scope') {
      column(name: 'client_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'scope', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-7', author: 'nandocruz (generated)') {
    createTable(tableName: 'user') {
      column(name: 'id', type: 'BIGINT', autoIncrement: true) {
        constraints(primaryKey: true, primaryKeyName: 'userPK')
      }
      column(name: 'password', type: 'VARCHAR(255)')
      column(name: 'username', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-8', author: 'nandocruz (generated)') {
    createTable(tableName: 'user_roles') {
      column(name: 'user_id', type: 'BIGINT') {
        constraints(nullable: false)
      }
      column(name: 'roles', type: 'VARCHAR(255)')
    }
  }

  changeSet(id: '1535695013099-9', author: 'nandocruz (generated)') {
    addUniqueConstraint(columnNames: 'client_id', constraintName: 'UC_CLIENTCLIENT_ID_COL', tableName: 'client')
  }

  changeSet(id: '1535695013099-10', author: 'nandocruz (generated)') {
    addUniqueConstraint(columnNames: 'username', constraintName: 'UC_USERUSERNAME_COL', tableName: 'user')
  }

  changeSet(id: '1535695013099-11', author: 'nandocruz (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'client_id', baseTableName: 'client_registered_redirect_uris', constraintName: 'FK1ecuyk9pghr3w7ifwipe4a02k', deferrable: false, initiallyDeferred: false, referencedColumnNames: 'id', referencedTableName: 'client')
  }

  changeSet(id: '1535695013099-12', author: 'nandocruz (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'client_id', baseTableName: 'client_resource_ids', constraintName: 'FK1go6m7su1h8n0ddp7d3qibphf', deferrable: false, initiallyDeferred: false, referencedColumnNames: 'id', referencedTableName: 'client')
  }

  changeSet(id: '1535695013099-13', author: 'nandocruz (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'user_id', baseTableName: 'user_roles', constraintName: 'FK55itppkw3i07do3h7qoclqd4k', deferrable: false, initiallyDeferred: false, referencedColumnNames: 'id', referencedTableName: 'user')
  }

  changeSet(id: '1535695013099-14', author: 'nandocruz (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'client_id', baseTableName: 'client_scope', constraintName: 'FKdeybao3hsct7biwb8kx4lltsa', deferrable: false, initiallyDeferred: false, referencedColumnNames: 'id', referencedTableName: 'client')
  }

  changeSet(id: '1535695013099-15', author: 'nandocruz (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'client_id', baseTableName: 'client_roles', constraintName: 'FKhcuwm327ncan0ue5b65v578cl', deferrable: false, initiallyDeferred: false, referencedColumnNames: 'id', referencedTableName: 'client')
  }

  changeSet(id: '1535695013099-16', author: 'nandocruz (generated)') {
    addForeignKeyConstraint(baseColumnNames: 'client_id', baseTableName: 'client_authorized_grant_types', constraintName: 'FKl32hlober4h1mers03qn5t5rq', deferrable: false, initiallyDeferred: false, referencedColumnNames: 'id', referencedTableName: 'client')
  }

}
