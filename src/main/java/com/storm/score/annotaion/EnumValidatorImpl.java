package com.storm.score.annotaion;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link ConstraintValidator}에서 구현된 메소드를 찾아보면 예시를 알 수 있다.
 */
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private EnumValidator annotation;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null && this.annotation.nullAble()) {
            return true;
        } else if (!this.annotation.nullAble() && value == null) {
            return false;
        }
        boolean result = false;
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumVal : enumValues) {
                if (value.equals(enumVal.toString())
                        || (this.annotation.ignoreCase() && value.equalsIgnoreCase(enumVal.toString()))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}