package com.amberlight.test.web.apps.domain.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.groups.Default;

import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ValidationUtil {

	private final Validator validator;

	public ValidationUtil(Validator validator) {
		this.validator = requireNonNull(validator);
	}

	/**
	 * Validates all constraints on {@code object}.
	 *
	 * @param object object to validate
	 * @param groups the group or list of groups targeted for validation (defaults to
	 *        {@link Default})
	 * @param <T> the type of the object to validate
	 * @return the validated object, if it is valid.
	 * @throws ConstraintViolationException if object is invalid
	 * @throws IllegalArgumentException if object is {@code null}
	 *         or if {@code null} is passed to the varargs groups
	 * @throws ValidationException if a non recoverable error happens
	 *         during the validation process
	 */
	public <T> T validateObject(T object, Class<?>... groups) {
		Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		return object;
	}
}
