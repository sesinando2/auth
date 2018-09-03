package net.dlcruz.auth.api.exception

import net.dlcruz.auth.api.model.ApiError
import org.springframework.validation.FieldError

class ObjectValidationException extends RuntimeException {

    static final UNIQUE_CONSTRAINT = 'uniqueConstraint'

    private Object bean
    private List<ApiError> fieldErrors

    ObjectValidationException(Object bean) {
        super("A validation error has occurred for ${bean.class.simpleName}")
        this.bean = bean
        this.fieldErrors = []
    }

    boolean getHasError() {
        !fieldErrors.empty
    }

    List<ApiError> getFieldErrors() {
        return fieldErrors
    }

    void pushUniqueConstraint(Object bean, String fieldName, Object existingBean, String defaultMessage) {
        def arguments = [rejectedValue: bean[fieldName], existingAllocation: existingBean]
        push(UNIQUE_CONSTRAINT, fieldName, defaultMessage, arguments)
    }

    void push(String errorCode, String field, String defaultMessage, Map<String, Object> arguments = []) {
        def code = "${errorCode}.${bean.class.simpleName}.${field}"
        def codes = [code, "${errorCode}.${bean.class.simpleName}", errorCode]

        fieldErrors << new ApiError(
            code: code,
            codes: codes,
            defaultMessage: defaultMessage ?: "A validation error has occurred in ${bean.class.simpleName}.${field}",
            arguments: arguments + [objectName: bean.class.simpleName, field: field]
        )
    }

    void push(FieldError fieldError) {
        def arguments = [
                bean: bean.class.simpleName,
                objectName: fieldError.objectName,
                field: fieldError.field,
                rejectedValue: fieldError.rejectedValue
        ]

        fieldError.arguments?.each { Object argument ->
            arguments.putAll(argument.properties - argument.properties.subMap(['class']))
        }

        fieldErrors << new ApiError(
            code: fieldError.code,
            codes: fieldError.codes,
            defaultMessage: fieldError.defaultMessage,
            arguments: arguments
        )
    }

    void push(List<FieldError> fieldErrors) {
        fieldErrors.each(this.&push)
    }

    ObjectValidationException leftShift(FieldError fieldError) {
        push(fieldError)
        this
    }

    ObjectValidationException leftShift(List<FieldError> fieldErrors) {
        push(fieldErrors)
        this
    }
}
