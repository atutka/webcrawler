package com.wiprodigital.webcrawler.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = MaxPagesValidator.class)
@Documented
public @interface MaxPages
{

    String message() default "{validation.maximum.maxpages}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
