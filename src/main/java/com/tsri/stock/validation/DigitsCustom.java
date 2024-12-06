package com.tsri.stock.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DigitsCustomValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface DigitsCustom {
	int integer();
	int fraction();
    String message() default "Invalid money number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
