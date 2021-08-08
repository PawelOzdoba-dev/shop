package pl.app.shop.validator;

import pl.app.shop.validator.impl.PasswordValidator;
import pl.app.shop.validator.impl.ProductIsExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductIsExistValidator.class)
public @interface ProductIsExist {
    String message() default "Product not exist in system";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
