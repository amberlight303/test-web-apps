package com.amberlight.test.web.apps.domain.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to use for validation that a monetary amount does not have value
 * below the specified minimum.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
@Constraint(validatedBy = MonetaryAmountValidator.class)
public @interface Amount {

	/**
	 * The message.
	 */
	String message() default "Amount must be positive";

	/**
	 * Optional min value.
	 */
	String min();

	/**
	 * The groups.
	 */
	Class<?>[] groups() default {};

	/**
	 * The payload.
	 */
	Class<? extends Payload>[] payload() default {};

}
