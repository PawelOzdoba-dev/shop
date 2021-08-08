package pl.app.shop.validator.impl;

import pl.app.shop.domain.dto.ProductDto;
import pl.app.shop.validator.ProductIsExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIsExistValidator implements ConstraintValidator<ProductIsExist, ProductDto> {

    @Override
    public boolean isValid(ProductDto productDto, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
