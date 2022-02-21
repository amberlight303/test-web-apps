package com.amberlight.test.web.apps.domain.monetary.validation;

import com.amberlight.test.web.apps.domain.monetary.MonetaryAmount;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * The annotated element must be a number within accepted range.
 * Supported types are:
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code CharSequence}</li>
 *     <li>{@code byte}, {@code short}, {@code int}, {@code long}, and their respective
 *     wrapper types</li>
 * </ul>
 * <p>
 * {@code null} elements are considered valid.
 *
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Digits(integer = MonetaryAmount.PRECISION - MonetaryAmount.SCALE,
		fraction = MonetaryAmount.SCALE)
@DecimalMax(value = MonetaryAmount.MAX_MONETARY_VALUE,
		message = "The amount can not be greater than " + MonetaryAmount.MAX_MONETARY_VALUE)
public @interface MonetaryValue {

	String message() default "Must be a valid monetary amount";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
