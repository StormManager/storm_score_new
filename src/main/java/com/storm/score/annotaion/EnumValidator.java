package com.storm.score.annotaion;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* https://funofprograming.wordpress.com/2016/09/29/java-enum-validator/ */
/* https://velog.io/@hellozin/Annotation%EC%9C%BC%EB%A1%9C-Enum-%EA%B2%80%EC%A6%9D%ED%95%98%EA%B8%B0 */

/* 해당 annotation이 실행 할 ConstraintValidator 구현체를 `EnumValidatorImpl`로 지정합니다. */
@Constraint(validatedBy = {EnumValidatorImpl.class})
/* 해당 annotation은 메소드, 필드, 파라미터에 적용 할 수 있습니다. */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
/* annotation을 Runtime까지 유지합니다. */
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    String message() default "Invalid value. This is not permitted.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    boolean ignoreCase() default false;

    boolean nullAble() default true;
}