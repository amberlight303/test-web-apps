package com.amberlight.test.web.apps.domain.monetary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


import com.amberlight.test.web.apps.domain.monetary.validation.MonetaryValue;
import com.amberlight.test.web.apps.domain.monetary.validation.MonetaryValueValidator;
import com.amberlight.test.web.apps.domain.monetary.validation.NotNegativeAmount;
import com.amberlight.test.web.apps.domain.monetary.validation.PositiveAmount;
import lombok.Getter;
import lombok.NonNull;

import static com.amberlight.test.web.apps.domain.monetary.validation.MonetaryValueValidator.validAmount;
import static com.amberlight.test.web.apps.domain.monetary.validation.MonetaryValueValidator.validNumber;


/**
 * Represents an immutable monetary amount that is valid to use for monetary
 * calculations and long-time storage. A monetary amount is represented by a
 * {@link BigDecimal} with a fixed scale of {@link #SCALE}. All operations on a
 * monetary amount should yield a new monetary amount that is properly scaled
 * and rounded according to the {@link #ROUNDING_MODE}.
 * <p>
 * A valid monetary amount can only be constructed from an input string or
 * numeric value that has a precision and scale equal or below the defined
 * {@link #PRECISION} and {@link #SCALE}, otherwise an exception is raised to
 * indicate that the construction is unsafe as monetary values might be lost.
 * Further, a monetary amount may also not exceed the
 * {@link #MAX_MONETARY_VALUE}. Any of these violations will yield an
 * {@link ConstraintViolationException} either at construction time or if the result of
 * an operation would violate these constraints.
 * <p>
 * To perform a sequence of loss-less computations where the final result is a
 * monetary amount you should use raw big decimal representations and then only
 * construct the final result as a {@link MonetaryAmount}, after applying proper
 * scaling and rounding to ensure that the construction of the result does not
 * fail.
 */
public class MonetaryAmount implements Comparable<MonetaryAmount> {

	/**
	 * The database columns for monetary decimal values accept DECIMAL(19,8).
	 * <p>
	 * The column definition follows the format DECIMAL(M, D) where M is the
	 * maximum number of digits (the precision) and D is the number of digits to
	 * the right of the decimal point (the scale).
	 * <p>
	 * This means that the decimal columns accept a max value of
	 * 99999999999.99999999 (11 digits in precision and 8 in scale)
	 */
	public static final String MAX_MONETARY_VALUE = "99999999999.99999999";

	/**
	 * A {@link BigDecimal} representation of the {@link #MAX_MONETARY_VALUE}.
	 */
	public static final BigDecimal MAX_MONETARY_AMOUNT = new BigDecimal(
			MAX_MONETARY_VALUE);

	public static final int PRECISION = 19;

	public static final int SCALE = 8;

	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_DOWN;

	public static final MonetaryAmount ZERO = new MonetaryAmount(BigDecimal.ZERO);
	public static final MonetaryAmount ONE = new MonetaryAmount(BigDecimal.ONE);
	public static final MonetaryAmount TEN = new MonetaryAmount(BigDecimal.TEN);
	public static final MonetaryAmount HUNDRED = new MonetaryAmount(BigDecimal.TEN.multiply(BigDecimal.TEN));

	public static final MonetaryAmount MAX = new MonetaryAmount(MAX_MONETARY_AMOUNT);

	@Getter
	@NonNull
	@NotNull
	@MonetaryValue
	@Positive(groups = PositiveAmount.class)
	@PositiveOrZero(groups = NotNegativeAmount.class)
	private final BigDecimal value;

	/**
	 * Creates a new monetary amount instance from a {@link String}
	 * representation.
	 *
	 * @param amount
	 *            string representation of an amount.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code amount} is {@code null}.
	 * @throws ConstraintViolationException
	 *             Thrown if the {@code amount} is not a valid
	 *             representation of a monetary amount. A valid monetary amount
	 *             can only be constructed from an input string that has a
	 *             precision and scale equal or below the defined
	 *             {@link #PRECISION} and {@link #SCALE}, otherwise an exception
	 *             is raised to indicate that the construction is unsafe as
	 *             precision might be lost. A monetary amount may also not
	 *             exceed the {@link #MAX_MONETARY_VALUE}.
	 */
	public MonetaryAmount(String amount) {
		this(validNumber("amount", amount));
	}

	/**
	 * Creates a new monetary amount instance from a {@link BigDecimal}
	 * representation.
	 *
	 * @param amount
	 *            {@link BigDecimal} representation of an amount.
	 * @throws ConstraintViolationException
	 *             Thrown if the {@code amount} is {@code null} or not a valid
	 *             representation of a monetary amount. A valid monetary amount
	 *             can only be constructed from an input big decimal that has a
	 *             precision and scale equal or below the defined
	 *             {@link #PRECISION} and {@link #SCALE}, otherwise an exception
	 *             is raised to indicate that the construction is unsafe as
	 *             precision might be lost.A monetary amount may also not exceed
	 *             the {@link #MAX_MONETARY_VALUE}.
	 */
	public MonetaryAmount(BigDecimal amount) {
		this.value = validAmount("amount",
				withDefaultScaleAndRounding(validNumber("amount", amount)));
	}

	/**
	 * Construct a big decimal that has the proper scale and rounding mode set.
	 *
	 * @param amount
	 *            amount.
	 *
	 * @return properly scaled and rounded (rounding should never occur though)
	 *         value.
	 */
	private BigDecimal withDefaultScaleAndRounding(BigDecimal amount) {
		return BigDecimalUtil.setScale(amount, SCALE, ROUNDING_MODE);
	}

	/**
	 * Adds the provided addend to this value and constructs a new
	 * {@link MonetaryAmount} with the result.
	 *
	 * @param addend
	 *            addend, not {@code null}.
	 *
	 * @return a new {@link MonetaryAmount} holding the result of the
	 *         computation.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code addend} is {@code null}.
	 * @throws ConstraintViolationException
	 *             If the result of the operation would
	 *             violate any of the {@link MonetaryAmount} constraints.
	 */
	public MonetaryAmount add(MonetaryAmount addend) {

		Objects.requireNonNull(addend, "addend");

		return new MonetaryAmount(this.value.add(addend.getValue()));
	}

	/**
	 * Subtracts the provided subtrahend from this value and constructs a new
	 * {@link MonetaryAmount} with the result.
	 *
	 * @param subtrahend
	 *            subtrahend, not {@code null}.
	 *
	 * @return a new {@link MonetaryAmount} holding the result of the
	 *         computation.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code subtrahend} is {@code null}.
	 * @throws ConstraintViolationException
	 *             If the result of the operation
	 *             would violate any of the {@link MonetaryAmount} constraints.
	 */
	public MonetaryAmount subtract(MonetaryAmount subtrahend) {

		Objects.requireNonNull(subtrahend, "subtrahend");

		return new MonetaryAmount(this.value.subtract(subtrahend.getValue()));
	}

	/**
	 * Divides this monetary value with the provided divisor and constructs a
	 * new {@link MonetaryAmount} with a possibly rounded result.
	 *
	 * @param divisor
	 *            divisor, not {@code null}.
	 *
	 * @return a new {@link MonetaryAmount} holding the result of the
	 *         computation. Note that if the result would yield a value with a
	 *         scale that exceeds the {@link #SCALE}, the constructed result
	 *         will be scaled down to {@link #SCALE} rounded using the default
	 *         {@link #ROUNDING_MODE}.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code divisor} is {@code null}.
	 * @throws ConstraintViolationException
	 *             If the result of the operation would
	 *             violate any of the {@link MonetaryAmount} constraints.
	 */
	public MonetaryAmount divide(MonetaryAmount divisor) {
		Objects.requireNonNull(divisor, "divisor");
		MonetaryValueValidator.positiveAmount("divisor", divisor.getValue());

		return divideWithScale(divisor.getValue());
	}

	/**
	 * Divides this monetary value with the provided divisor and constructs a
	 * new {@link MonetaryAmount} with a possibly rounded result.
	 *
	 * @param divisor
	 *            divisor, not {@code null}. The divisor may be of arbitrary
	 *            precision and scale, and the division will be performed using
	 *            the divisors original precision and scale. I.e. any required
	 *            scaling and rounding will be applied to the result, not the
	 *            computation itself.
	 *
	 * @return a new {@link MonetaryAmount} holding the result of the
	 *         computation. Note that if the result would yield a value with a
	 *         scale that exceeds the {@link #SCALE}, the constructed result
	 *         will be scaled down to {@link #SCALE} rounded using the default
	 *         {@link #ROUNDING_MODE}.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code divisor} is {@code null}.
	 * @throws ConstraintViolationException
	 *             If the result of the operation would
	 *             violate any of the {@link MonetaryAmount} constraints.
	 */
	public MonetaryAmount divide(BigDecimal divisor) {
		Objects.requireNonNull(divisor, "divisor");
		MonetaryValueValidator.positiveAmount("divisor", divisor);

		return divideWithScale(divisor);
	}

	/**
	 * Performs division of this monetary amount with a {@link BigDecimal}
	 * divisor using the default scale and rounding mode for the result.
	 *
	 * @param divisor
	 *            divisor
	 *
	 * @return {@link MonetaryAmount} result.
	 */
	private MonetaryAmount divideWithScale(BigDecimal divisor) {

		BigDecimal quotient = this.value.divide(divisor, SCALE, ROUNDING_MODE);
		return new MonetaryAmount(quotient);
	}

	/**
	 * Multiplies this monetary value with the provided multiplicand and
	 * constructs a new {@link MonetaryAmount} with a possibly rounded result.
	 *
	 * @param multiplicand
	 *            multiplicand, not {@code null}.
	 *
	 * @return a new {@link MonetaryAmount} holding the result of the
	 *         computation. Note that if the result would yield a value with a
	 *         scale that exceeds the {@link #SCALE}, the constructed result
	 *         will be scaled down to {@link #SCALE} rounded using the default
	 *         {@link #ROUNDING_MODE}.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code multiplicand} is {@code null}.
	 * @throws ConstraintViolationException
	 *             If the result of the operation
	 *             would violate any of the {@link MonetaryAmount} constraints.
	 */
	public MonetaryAmount multiply(MonetaryAmount multiplicand) {

		Objects.requireNonNull(multiplicand, "multiplicand");
		return multiplyAndScale(multiplicand.getValue());
	}

	/**
	 * Multiplies this monetary value with the provided multiplicand and
	 * constructs a new {@link MonetaryAmount} with a possibly rounded result.
	 *
	 * @param multiplicand
	 *            multiplicand, not {@code null}. The multiplicand may be of
	 *            arbitrary precision and scale, and the multiplication will be
	 *            performed using the divisors original precision and scale.
	 *            I.e. any required scaling and rounding will be applied to the
	 *            result, not the computation itself.
	 *
	 * @return a new {@link MonetaryAmount} holding the result of the
	 *         computation. Note that if the result would yield a value with a
	 *         scale that exceeds the {@link #SCALE}, the constructed result
	 *         will be scaled down to {@link #SCALE} rounded using the default
	 *         {@link #ROUNDING_MODE}.
	 *
	 * @throws NullPointerException
	 * 			   Thrown if the {@code multiplicand} is {@code null}.
	 * @throws ConstraintViolationException
	 *             If the result of the operation
	 *             would violate any of the {@link MonetaryAmount} constraints.
	 */
	public MonetaryAmount multiply(BigDecimal multiplicand) {

		Objects.requireNonNull(multiplicand, "multiplicand");
		return multiplyAndScale(multiplicand);
	}

	/**
	 * Performs multiplication of this monetary amount with a {@link BigDecimal}
	 * multiplicand and applies appropriate scaling to the result before a new
	 * {@link MonetaryAmount} is created.
	 *
	 * @param multiplicand
	 *            multiplicand
	 *
	 * @return {@link MonetaryAmount} result.
	 */
	private MonetaryAmount multiplyAndScale(BigDecimal multiplicand) {

		BigDecimal product = this.value.multiply(multiplicand);
		return new MonetaryAmount(product.setScale(SCALE, ROUNDING_MODE));
	}

	/**
	 * Get the value rounded down to only two decimals.
	 *
	 * @return The rounded value
	 */
	public BigDecimal getValueWithTwoDecimals() {
		return getValue().setScale(2, RoundingMode.DOWN);
	}

	/**
	 * Returns a monetary amount whose value is (-this).
	 *
	 * @return negated monetary amount.
	 */
	public MonetaryAmount negate() {
		return new MonetaryAmount(getValue().negate());
	}

	/**
	 * Returns a custom string representation of this monetary amount.
	 *
	 * @return a string representation of this amount.
	 */
	@Override
	public String toString() {
		return this.value.toPlainString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof MonetaryAmount
				&& this.value.compareTo(((MonetaryAmount) obj).getValue()) == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	/**
	 * See how this monetary amount compares to another monetary amount.
	 * @param other the other monetary amount.
	 * @return returns -1 if less, 0 if same and 1 if greater than the other.
	 */
	@Override
	public final int compareTo(@NotNull final MonetaryAmount other) {
		return this.value.compareTo(other.getValue());
	}

	/**
	 * Are this monetary amount value greater than the other value?
	 * @param other the other monetary amount.
	 * @return returns TorF.
	 */
	public final boolean isGreaterThan(final MonetaryAmount other) {
		return this.compareTo(other) > 0;
	}

	/**
	 * Are this monetary amount value less than the other value?
	 * @param other the other monetary amount.
	 * @return returns TorF.
	 */
	public final boolean isLessThan(final MonetaryAmount other) {
		return this.compareTo(other) < 0;
	}
}
