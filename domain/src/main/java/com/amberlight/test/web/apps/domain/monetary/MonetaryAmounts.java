package com.amberlight.test.web.apps.domain.monetary;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.ConstraintViolationException;

/**
 * A utility class for dealing with {@link MonetaryAmount} instances.
 */
public final class MonetaryAmounts {

	/**
	 * Private constructor to prevent instances.
	 */
	private MonetaryAmounts() {
	}

	/**
	 * Get a {@link MonetaryAmount} from a {@link String} amount.
	 *
	 * @param amount
	 *            The amount
	 *
	 * @return A {@link MonetaryAmount} instance
	 *
	 * @throws ConstraintViolationException
	 *             Thrown if the {@code amount} is {@code null} or not a valid
	 *             representation of a monetary amount according to the
	 *             specification of {@link MonetaryAmount}
	 */
	public static MonetaryAmount getAmount(String amount) {
		return new MonetaryAmount(amount);
	}

	/**
	 * Get a {@link MonetaryAmount} from a {@link BigDecimal} amount.
	 *
	 * @param amount
	 *            The amount
	 *
	 * @return A {@link MonetaryAmount} instance
	 *
	 * @throws ConstraintViolationException
	 *             Thrown if the {@code amount} is {@code null} or not a valid
	 *             representation of a monetary amount according to the
	 *             specification of {@link MonetaryAmount}
	 */
	public static MonetaryAmount getAmount(BigDecimal amount) {
		return new MonetaryAmount(amount);
	}

	/**
	 * Add {@code augend} to {@code amount}.
	 *
	 * @param augend
	 *            The monetary amount, {@code not null}
	 *
	 * @param addend
	 *            A {@link MonetaryAmount} representation of a monetary amount
	 *            value to be added.
	 *
	 * @return A new monetary amount representing the computed value.
	 *
	 * @throws NullPointerException
	 *             Thrown if any of the parameters are {@code null}
	 * @throws ConstraintViolationException
	 *             Thrown if the <i>result</i> of the computation is not
	 *             a valid representation of a {@link MonetaryAmount}.
	 */
	public static MonetaryAmount add(MonetaryAmount augend, MonetaryAmount addend) {

		Objects.requireNonNull(augend, "augend");
		return augend.add(addend);
	}

	/**
	 * Subtracts {@code subtrahend} from the provided {@code amount}.
	 *
	 * @param amount
	 *            The monetary amount
	 *
	 * @param subtrahend
	 *            A {@link MonetaryAmount} representation of a amount value to
	 *            be subtracted
	 *
	 * @return A new monetary amount representing the computed value.
	 *
	 * @throws NullPointerException
	 *             Thrown if any of the parameters are {@code null}
	 * @throws ConstraintViolationException
	 *             Thrown if the <i>result</i> of the computation is not
	 *             a valid representation of a {@link MonetaryAmount}.
	 */
	public static MonetaryAmount subtract(MonetaryAmount amount, MonetaryAmount subtrahend) {

		Objects.requireNonNull(amount, "amount");
		return amount.subtract(subtrahend);
	}

	/**
	 * Multiplies {@code amount} with the provided {@code multiplicand}.
	 *
	 * @param amount
	 *            The amount
	 *
	 * @param multiplicand
	 *            A {@link MonetaryAmount} representation of a amount value to
	 *            multiply with.
	 *
	 * @return A new monetary amount representing the computed value.
	 *
	 * @throws NullPointerException
	 *             Thrown if any of the parameters are {@code null}
	 * @throws ConstraintViolationException
	 *             Thrown if the <i>result</i> of the computation is not
	 *             a valid representation of a {@link MonetaryAmount}.
	 *
	 * @see MonetaryAmount#multiply(MonetaryAmount)
	 */
	public static MonetaryAmount multiply(MonetaryAmount amount, MonetaryAmount multiplicand) {

		Objects.requireNonNull(amount, "amount");
		return amount.multiply(multiplicand);
	}

	/**
	 * Divides {@code dividend} by the provided {@code divisor}.
	 *
	 * @param dividend
	 *            The dividend
	 *
	 * @param divisor
	 *            A {@link MonetaryAmount} representation of a amount value to
	 *            divide by.
	 *
	 * @return A new monetary amount representing the computed value.
	 *
	 * @throws NullPointerException
	 *             Thrown if any of the parameters are {@code null}
	 * @throws ConstraintViolationException
	 *             Thrown if the <i>result</i> of the computation is not
	 *             a valid representation of a {@link MonetaryAmount}.
	 *
	 * @see MonetaryAmount#divide(MonetaryAmount)
	 */
	public static MonetaryAmount divide(MonetaryAmount dividend, MonetaryAmount divisor) {

		Objects.requireNonNull(dividend, "dividend");
		return dividend.divide(divisor);
	}

	/**
	 * Get if {@code value} is positive (greater than zero).
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is positive
	 */
	public static boolean isPositive(MonetaryAmount value) {
		return BigDecimalUtil.isPositive(value.getValue());
	}

	/**
	 * Get if {@code value} is zero.
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is zero
	 */
	public static boolean isZero(MonetaryAmount value) {
		return BigDecimalUtil.isZero(value.getValue());
	}

	/**
	 * Get if {@code value} is negative (less than zero).
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is negative
	 */
	public static boolean isNegative(MonetaryAmount value) {
		return BigDecimalUtil.isNegative(value.getValue());
	}

	/**
	 * Get if {@code value} is equal to or greater than {@code otherAmount}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param otherAmount
	 *            The other value to compare to
	 *
	 * @return {@code true} if they are equal or if {@code value} is greater
	 *         than {@code otherAmount}
	 */
	public static boolean isEqualToOrGreaterThan(MonetaryAmount value, MonetaryAmount otherAmount) {
		return !value.isLessThan(otherAmount);
	}

	/**
	 * Get if {@code value} is equal to or less than {@code otherAmount}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param otherAmount
	 *            The other value to compare to
	 *
	 * @return {@code true} if they are equal or if {@code value} is less than
	 *         {@code otherAmount}
	 */
	public static boolean isEqualToOrLessThan(MonetaryAmount value, MonetaryAmount otherAmount) {
		return !value.isGreaterThan(otherAmount);
	}

	/**
	 * Get if {@code value} is greater than
	 * {@link MonetaryAmount#MAX_MONETARY_AMOUNT}.
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is greater
	 */
	public static boolean isGreaterThanMaxMonetaryValue(BigDecimal value) {
		return BigDecimalUtil.isGreaterThan(value,
				MonetaryAmount.MAX_MONETARY_AMOUNT);
	}

	/**
	 * Converts cents to a {@link MonetaryAmount}.
	 *
	 * @param cents
	 *            The cents
	 *
	 * @return The monetary amount
	 */
	public static MonetaryAmount convertFromCents(int cents) {
		return new MonetaryAmount(BigDecimalUtil.convertFromCents(cents));
	}

	/**
	 * Converts a {@link MonetaryAmount} to cents.
	 *
	 * @param amount
	 *            The amount
	 *
	 * @throws ArithmeticException If the resulting value does not fit in a {@code int}.
	 *
	 * @return The amount represented as cents
	 */
	public static int convertToCents(MonetaryAmount amount) {
		return BigDecimalUtil.convertToCents(amount.getValueWithTwoDecimals());
	}
}
