package com.amberlight.test.web.apps.domain.monetary;

import com.amberlight.test.web.apps.domain.monetary.validation.MonetaryValueValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * A utility class for dealing with {@link BigDecimal} instances. This class is
 * useful for general {@link BigDecimal} handling and computations, but it
 * should not be used for calculating final monetary values. In that case
 * {@link MonetaryAmount} and {@link MonetaryAmounts} should be used, which
 * ensures proper precision, scale and rounding for monetary amounts.
 */
public final class BigDecimalUtil {

	private static final int DEFAULT_SCALE = 8;

	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

	/**
	 * Private constructor to prevent instances.
	 */
	private BigDecimalUtil() {
	}

	/**
	 * Get a {@link BigDecimal} from a {@link String} value.
	 *
	 * @param value
	 *            The value
	 *
	 * @return A {@link BigDecimal} instance
	 *
	 * @throws NullPointerException
	 * 			If {@code value} is {@code null}
	 * @throws IllegalArgumentException
	 *			If the {@code value} is not a valid representation of a {@code BigDecimal}
	 */
	public static BigDecimal valueOf(String value) {
		return MonetaryValueValidator.validNumber("value", value);
	}

	/**
	 * Set the default scale and rounding mode on an value.
	 *
	 * @param value
	 *            The value
	 *
	 * @return A default scaled {@link BigDecimal} instance
	 */
	public static BigDecimal setScale(BigDecimal value) {
		return setScale(value, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	/**
	 * Set a custom scale and rounding mode on an value.
	 *
	 * @param value
	 *            The value
	 *
	 * @param scale
	 *            The scale to use
	 *
	 * @param roundingMode
	 *            The rounding mode to use
	 *
	 * @return A custom scaled {@link BigDecimal} instance
	 */
	public static BigDecimal setScale(BigDecimal value, int scale,
			RoundingMode roundingMode) {

		try {
			return value.setScale(scale, roundingMode);
		}
		catch (ArithmeticException e) {
			throw new IllegalArgumentException("Failed to set the scale", e);
		}
	}

	/**
	 * Get if {@code value} is positive (greater than zero).
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is positive
	 */
	public static boolean isPositive(BigDecimal value) {
		return value.signum() > 0;
	}

	/**
	 * Get if {@code value} is zero.
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is zero
	 */
	public static boolean isZero(BigDecimal value) {
		return value.signum() == 0;
	}

	/**
	 * Get if {@code value} is negative (less than zero).
	 *
	 * @param value
	 *            The value
	 *
	 * @return {@code true} if {@code value} is negative
	 */
	public static boolean isNegative(BigDecimal value) {
		return value.signum() < 0;
	}

	/**
	 * Get if {@code value} is greater than {@code otherAmount}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param otherAmount
	 *            The other value to compare to
	 *
	 * @return {@code true} if {@code value} is greater
	 */
	public static boolean isGreaterThan(BigDecimal value, BigDecimal otherAmount) {
		return value.compareTo(otherAmount) > 0;
	}

	/**
	 * Get if {@code value} is less than {@code otherAmount}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param otherAmount
	 *            The other value to compare to
	 *
	 * @return {@code true} if {@code value} is less
	 */
	public static boolean isLessThan(BigDecimal value, BigDecimal otherAmount) {
		return value.compareTo(otherAmount) < 0;
	}

	/**
	 * Get if {@code value} is equal to {@code otherAmount}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param otherAmount
	 *            The other value to compare to
	 *
	 * @return {@code true} if they are equal
	 */
	public static boolean isEqualTo(BigDecimal value, BigDecimal otherAmount) {
		return value.compareTo(otherAmount) == 0;
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
	public static boolean isEqualToOrGreaterThan(BigDecimal value,
			BigDecimal otherAmount) {
		return !isLessThan(value, otherAmount);
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
	public static boolean isEqualToOrLessThan(BigDecimal value,
			BigDecimal otherAmount) {
		return !isGreaterThan(value, otherAmount);
	}

	/**
	 * Add {@code augend} to {@code value}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param augend
	 *            The value to be added
	 *
	 * @return The new updated value
	 */
	public static BigDecimal add(BigDecimal value, String augend) {
		return value.add(valueOf(augend));
	}

	/**
	 * Subtract {@code subtrahend} from {@code value}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param subtrahend
	 *            The value to be subtracted
	 *
	 * @return The new updated value
	 */
	public static BigDecimal subtract(BigDecimal value, String subtrahend) {
		return value.subtract(valueOf(subtrahend));
	}

	/**
	 * Divide {@code value} by the {@code divisor}, using the default scale and
	 * rounding mode.
	 *
	 * @param value
	 *            The value
	 *
	 * @param divisor
	 *            The value by which the value is to be divided
	 *
	 * @return A new updated value
	 */
	public static BigDecimal divide(BigDecimal value, String divisor) {
		return divide(value, valueOf(divisor));
	}

	/**
	 * Divide {@code value} by the {@code divisor}, using a custom scale and
	 * rounding mode.
	 *
	 * @param value
	 *            The value
	 *
	 * @param divisor
	 *            The value by which the value is to be divided
	 *
	 * @param scale
	 *            The scale to use
	 *
	 * @param roundingMode
	 *            The rounding mode to use
	 *
	 * @return A new updated value
	 */
	public static BigDecimal divide(BigDecimal value, String divisor,
			int scale, RoundingMode roundingMode) {
		return divide(value, valueOf(divisor), scale, roundingMode);
	}

	/**
	 * Divide {@code value} by the {@code divisor}, using the default scale and
	 * rounding mode.
	 *
	 * @param value
	 *            The value
	 *
	 * @param divisor
	 *            The value by which the value is to be divided
	 *
	 * @return A new updated value
	 */
	public static BigDecimal divide(BigDecimal value, BigDecimal divisor) {
		return divide(value, divisor, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
	}

	/**
	 * Divide {@code value} by the {@code divisor}, using a custom scale and
	 * rounding mode.
	 *
	 * @param value
	 *            The value
	 *
	 * @param divisor
	 *            The value by which the value is to be divided
	 *
	 * @param scale
	 *            The scale to use
	 *
	 * @param roundingMode
	 *            The rounding mode to use
	 *
	 * @return A new updated value
	 */
	public static BigDecimal divide(BigDecimal value, BigDecimal divisor,
			int scale, RoundingMode roundingMode) {
		return value.divide(divisor, scale, roundingMode);
	}

	/**
	 * Multiply {@code value} with the {@code multiplicand}.
	 *
	 * @param value
	 *            The value
	 *
	 * @param multiplicand
	 *            The value to be multiplied
	 *
	 * @return A new updated value
	 */
	public static BigDecimal multiply(BigDecimal value, String multiplicand) {
		return value.multiply(valueOf(multiplicand));
	}

	/**
	 * Converts from cents.
	 * <p>
	 * 1 = <code>new BigDecimal("0.01")</code>
	 * <p>
	 * 100 = <code>new BigDecimal("1.00")</code>
	 *
	 * @param cents
	 *            The cents
	 *
	 * @return The big decimal value
	 */
	public static BigDecimal convertFromCents(int cents) {
		return BigDecimal.valueOf(cents / 100.0);
	}

	/**
	 * Converts to cents.
	 * <p>
	 * 0.01 = <code>new BigDecimal("1")</code>
	 * <p>
	 * 1.00 = <code>new BigDecimal("100")</code>
	 *
	 * @param value
	 *            The, two decimal, value to convert
	 *
	 * @throws ArithmeticException If the resulting value does not fit in a {@code int}.
	 *
	 * @return The integer value
	 */
	public static int convertToCents(BigDecimal value) {
		return value.movePointRight(2).toBigInteger().intValueExact();
	}
}
