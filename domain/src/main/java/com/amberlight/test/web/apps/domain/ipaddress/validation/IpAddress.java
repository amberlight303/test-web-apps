package com.amberlight.test.web.apps.domain.ipaddress.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Documented
@Constraint(
		validatedBy = {IpAddressValidator.class}
)
public @interface IpAddress {
	String message() default "Invalid IP address";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}