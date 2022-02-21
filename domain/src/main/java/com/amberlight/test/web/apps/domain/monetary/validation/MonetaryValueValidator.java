package com.amberlight.test.web.apps.domain.monetary.validation;

import com.amberlight.test.web.apps.domain.monetary.MonetaryAmount;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


/**
 * Programmatic validation of monetary amounts. The term <i>amount</i> here implies
 * something that fits within the valid range of a {@link MonetaryAmount}, whereas
 * the term <i>number</i> can be any number.
 *
 * The type of the value to validate can be either {@link BigDecimal} or {@link String}.
 *
 * <b>Note:</b> Direct usage of this class is <b>discouraged</b>, the preferred way to
 * validate monetary values should be to use JSR 303 annotations. This can be done with
 * {@link MonetaryValue} (in combination with standard annotations like {@link NotNull},
 * {@link NotBlank}, {@link Positive}, {@link PositiveOrZero} etc where applicable).
 */
public class MonetaryValueValidator {

	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 * This class is only a bearer of validation annotations.
	 */
	private static class NumericStringContainer {
		@NotBlank
		@Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE)
		@SuppressWarnings("unused")
		private String value;
	}

	/**
	 * This class is only a bearer of validation annotations.
	 */
	private static class BigDecimalContainer {
		@NotNull
		@Positive(groups = PositiveAmount.class)
		@PositiveOrZero(groups = NotNegativeAmount.class)
		@SuppressWarnings("unused")
		private BigDecimal value;
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotBlank}
	 *  {@code @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE)}
	 * </pre>
	 */
	public static BigDecimal validNumber(String name, String number) {
		return validateNumber(name, number);
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotNull}
	 * </pre>
	 */
	public static BigDecimal validNumber(String name, BigDecimal number) {
		return validateNumber(name, number);
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotBlank}
	 *  {@code @MonetaryValue}
	 * </pre>
	 */
	public static BigDecimal validAmount(String name, String amount) {
		return validAmount(name, validNumber(name, amount));
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotNull}
	 *  {@code @MonetaryValue}
	 * </pre>
	 */
	public static BigDecimal validAmount(String name, BigDecimal amount) {
		return validateAmount(name, amount);
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotBlank}
	 *  {@code @Positive}
	 *  {@code @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE)}
	 * </pre>
	 */
	public static BigDecimal positiveNumber(String name, String number) {
		return validateNumber(name, validateNumber(name, number), PositiveAmount.class);
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotBlank}
	 *  {@code @Positive}
	 *  {@code @MonetaryValue}
	 * </pre>
	 */
	public static BigDecimal positiveAmount(String name, String amount) {
		return positiveAmount(name, validNumber(name, amount));
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotNull}
	 *  {@code @Positive}
	 *  {@code @MonetaryValue}
	 * </pre>
	 */
	public static BigDecimal positiveAmount(String name, BigDecimal amount) {
		return validateAmount(name, amount, PositiveAmount.class);
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotBlank}
	 *  {@code @PositiveOrZero}
	 *  {@code @Digits(integer = Integer.MAX_VALUE, fraction = Integer.MAX_VALUE)}
	 * </pre>
	 */
	public static BigDecimal notNegativeNumber(String name, String number) {
		return validateNumber(name, validateNumber(name, number), NotNegativeAmount.class);
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotBlank}
	 *  {@code @PositiveOrZero}
	 *  {@code @MonetaryValue}
	 * </pre>
	 */
	public static BigDecimal notNegativeAmount(String name, String amount) {
		return notNegativeAmount(name, validNumber(name, amount));
	}

	/**
	 * Validation equivalent to
	 * <pre>
	 *  {@code @NotNull}
	 *  {@code @PositiveOrZero}
	 *  {@code @MonetaryValue}
	 * </pre>
	 */
	public static BigDecimal notNegativeAmount(String name, BigDecimal amount) {
		return validateAmount(name, amount, NotNegativeAmount.class);
	}

	private static BigDecimal validateAmount(String name, BigDecimal amount, Class<?>... groups) {
		Set<ConstraintViolation<MonetaryAmount>> violations = VALIDATOR
				.validateValue(MonetaryAmount.class, "value", amount, groups);
		checkViolations(name, violations);
		return amount;
	}

	private static BigDecimal validateNumber(String name, String number) {
		Set<ConstraintViolation<NumericStringContainer>> violations = VALIDATOR
				.validateValue(NumericStringContainer.class, "value", number);
		checkViolations(name, violations);
		return new BigDecimal(number);
	}

	private static BigDecimal validateNumber(String name, BigDecimal number, Class<?>... groups) {
		Set<ConstraintViolation<BigDecimalContainer>> violations = VALIDATOR
				.validateValue(BigDecimalContainer.class, "value", number, groups);
		checkViolations(name, violations);
		return number;
	}

	private static void checkViolations(String name, Set<? extends ConstraintViolation<?>> violations) {
		if (violations != null && !violations.isEmpty()) {
			String defaultMessage = new ConstraintViolationException(violations).getMessage();
			throw new ConstraintViolationException(name + " is invalid; " + defaultMessage, violations);
		}
	}
}
