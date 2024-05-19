package com.storm.score.annotaion;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListEnumValidatorImpl implements ConstraintValidator<ListEnumValidator, List<String>> {
    private ListEnumValidator annotation;

    @Override
    public void initialize(ListEnumValidator constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        for (String val : value) {
            boolean isValid = false;

            for (Enum<?> enumVal : this.annotation.enumClass().getEnumConstants()) {
                String enumValDescription = enumVal.name();
                if (annotation.ignoreCase()) {
                    if (enumValDescription.equalsIgnoreCase(val)) {
                        isValid = true;
                        break;
                    }
                } else {
                    if (enumValDescription.equals(val)) {
                        isValid = true;
                        break;
                    }
                }
            }

            if (!isValid) {
                return false;
            }
        }

        return true;
    }
}
