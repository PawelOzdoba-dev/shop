package pl.app.shop.service.impl

import pl.app.shop.domain.dto.UserDto
import pl.app.shop.validator.impl.PasswordValidator
import spock.lang.Specification

class PasswordValidatorSpec extends Specification {

    def passwordValidator = new PasswordValidator()

    def 'should test password validator'() {
        given:
        def userDto = new UserDto(password: password, confirmPassword: confirmPassword)

        when:
        def result = passwordValidator.isValid(userDto, null)

        then:
        result == expected

        where:
        password | confirmPassword || expected
        '123'    | '123'           || true
        '1233'   | '123'           || false
    }

}
