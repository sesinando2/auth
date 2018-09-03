package net.dlcruz.auth.api.controller

import net.dlcruz.auth.api.exception.EntityNotFoundException
import net.dlcruz.auth.service.EntityService
import org.codehaus.groovy.runtime.InvokerHelper
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

abstract class BaseEntityController<EntityType, IdType> {

    @GetMapping
    List<EntityType> list() {
        service.list()
    }

    @GetMapping('/page')
    Page<EntityType> listByPage(Pageable pageable) {
        service.listByPage(pageable)
    }

    @GetMapping('/{id}')
    EntityType get(@PathVariable('id') IdType id) {
        getEntityAndThrowIfNotFound(id)
    }

    @PostMapping
    EntityType create(@RequestBody EntityType entity) {
        service.validate(entity)
        service.create(entity)
    }

    @PostMapping('/validate')
    EntityType validate(@RequestBody EntityType entity) {
        service.validate(entity)
        entity
    }

    @PutMapping('/{id}')
    EntityType update(@PathVariable('id') IdType id, @RequestBody Map properties) {
        def entity = getEntityAndThrowIfNotFound(id)
        setProperties(entity, properties)
        service.validate(entity)
        service.save(entity)
    }

    @DeleteMapping('/{id}')
    void delete(@PathVariable('id') IdType id) {
        def entity = getEntityAndThrowIfNotFound(id)
        service.delete(entity)
    }

    @PostMapping('/{id}/validate')
    EntityType validate(@PathVariable('id') IdType id, @RequestBody Map properties) {
        def entity = getEntityAndThrowIfNotFound(id)
        setProperties(entity, properties)
        service.validate(entity)
        entity
    }

    protected abstract EntityService<EntityType, IdType> getService()

    protected EntityType getEntityAndThrowIfNotFound(IdType id) {
        def entity = service.get(id)

        if (!entity) {
            throw new EntityNotFoundException(service.entityClass, id)
        }

        entity
    }

    protected setProperties(EntityType entity, Map properties) {
        InvokerHelper.setProperties(entity, properties)
    }

    protected <EnumType extends Enum> EnumType toEnum(Class<EnumType> target, String name) {
        try {
            Enum.valueOf(target, name)
        } catch (any) {
            return null
        }
    }

    protected <EnumType extends Enum> List<EnumType> toEnumList(Class<EnumType> target, List<String> names) {
        names.collect(this.&toEnum.curry(target)) - null
    }
}
