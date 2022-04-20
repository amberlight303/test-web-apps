package com.amberlight.test.web.apps.domain.monetary;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;

import static java.math.BigDecimal.ROUND_UNNECESSARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonetaryAmountsTests {

	@Test
	public void testGetAmount_fromString() {

		BigDecimal amountValue = scaledValue("0.22");

		String amountString = amountValue.toPlainString();

		MonetaryAmount amount = MonetaryAmounts.getAmount(amountString);

		assertNotNull(amount);
		assertNotNull(amount.getValue());
		assertEquals(amount.getValue(), amountValue);
		assertEquals(amount.getValue().toString(), amountString);
		assertEquals(amount.getValue().scale(), MonetaryAmount.SCALE);
	}

	@Test
	public void testGetAmount_fromString_null() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryAmounts.getAmount((String) null));
	}

	@Test
	public void testGetAmount_fromString_maxExceeded() {

		assertThrows(ConstraintViolationException.class,
				() -> MonetaryAmounts.getAmount(MonetaryAmount.MAX_MONETARY_AMOUNT.add(
						BigDecimal.ONE).toPlainString()));
	}

	@Test
	public void testGetAmount_fromBigDecimal() {

		BigDecimal amountValue = scaledValue("0.1111");

		MonetaryAmount amount = MonetaryAmounts.getAmount(amountValue);

		assertNotNull(amount);
		assertNotNull(amount.getValue());
		assertEquals(amount.getValue(), amountValue);
		assertEquals(amount.getValue().scale(), MonetaryAmount.SCALE);
	}

	@Test
	public void testGetAmount_fromBigDecimal_null() {

		assertThrows(ConstraintViolationException.class,
				() -> MonetaryAmounts.getAmount((BigDecimal) null));
	}

	@Test
	public void testGetAmount_fromBigDecimal_maxExceeded() {

		assertThrows(ConstraintViolationException.class,
				() -> MonetaryAmounts.getAmount(MonetaryAmount.MAX_MONETARY_AMOUNT
				.add(BigDecimal.ONE)));
	}

	@Test
	public void testAdd() {

		MonetaryAmount amount = MonetaryAmount.ONE;
		MonetaryAmount augend = MonetaryAmount.TEN;

		BigDecimal result = scaledValue(amount.getValue()
				.add(augend.getValue()).toPlainString());

		assertEquals(result, MonetaryAmounts.add(amount, augend)
				.getValue());
	}

	@Test
	public void testSubtract() {

		MonetaryAmount amount = MonetaryAmount.ONE;
		MonetaryAmount subtrahend = MonetaryAmount.TEN;

		BigDecimal result = scaledValue(amount.getValue()
				.subtract(subtrahend.getValue()).toPlainString());

		assertEquals(result, MonetaryAmounts
				.subtract(amount, subtrahend).getValue());
	}

	@Test
	public void testMultiply() {

		MonetaryAmount amount = MonetaryAmount.HUNDRED;
		MonetaryAmount multiplicand = MonetaryAmount.TEN;

		BigDecimal result = scaledValue(amount.getValue()
				.multiply(multiplicand.getValue()).toPlainString());

		assertEquals(result, MonetaryAmounts
				.multiply(amount, multiplicand).getValue());
	}

	@Test
	public void testDivide() {

		MonetaryAmount dividend = MonetaryAmount.HUNDRED;
		MonetaryAmount divisor = MonetaryAmount.TEN;

		BigDecimal result = scaledValue(dividend.getValue()
				.divide(divisor.getValue(), RoundingMode.UNNECESSARY).toPlainString());

		assertEquals(result, MonetaryAmounts
				.divide(dividend, divisor).getValue());
	}

	@Test
	public void testIsPositive() {
		assertTrue(MonetaryAmounts.isPositive(MonetaryAmount.ONE));
	}

	@Test
	public void testIsPositive_notPositive() {
		assertFalse(MonetaryAmounts.isPositive(MonetaryAmount.ZERO));
	}

	@Test
	public void testIsZero() {
		assertTrue(MonetaryAmounts.isZero(MonetaryAmount.ZERO));
	}

	@Test
	public void testIsZero_notZero() {
		assertFalse(MonetaryAmounts.isZero(MonetaryAmount.ONE));
	}

	@Test
	public void testIsNegative() {
		assertTrue(MonetaryAmounts.isNegative(MonetaryAmount.ONE.negate()));
	}

	@Test
	public void testIsNegative_notNegative() {
		assertFalse(MonetaryAmounts.isNegative(MonetaryAmount.ONE));
	}

	@Test
	public void testIsEqualToOrGreaterThan_equal() {
		assertTrue(MonetaryAmounts.isEqualToOrGreaterThan(
				new MonetaryAmount("1"), new MonetaryAmount("1")));
	}

	@Test
	public void testIsEqualToOrGreaterThan_greater() {
		assertTrue(MonetaryAmounts.isEqualToOrGreaterThan(
				new MonetaryAmount("2"), new MonetaryAmount("1")));
	}

	@Test
	public void testIsEqualToOrGreaterThan_lesser() {
		assertFalse(MonetaryAmounts.isEqualToOrGreaterThan(
				new MonetaryAmount("1"), new MonetaryAmount("2")));
	}

	@Test
	public void testIsEqualToOrLessThan_equal() {
		assertTrue(MonetaryAmounts.isEqualToOrLessThan(
				new MonetaryAmount("1"), new MonetaryAmount("1")));
	}

	@Test
	public void testIsEqualToOrLessThan_lesser() {
		assertTrue(MonetaryAmounts.isEqualToOrLessThan(
				new MonetaryAmount("1"), new MonetaryAmount("2")));
	}

	@Test
	public void testIsEqualToOrLesserThan_greater() {
		assertFalse(MonetaryAmounts.isEqualToOrLessThan(
				new MonetaryAmount("2"), new MonetaryAmount("1")));
	}

	@Test
	public void testIsGreaterThanMaxMonetaryValue() {
		assertTrue(MonetaryAmounts
				.isGreaterThanMaxMonetaryValue(MonetaryAmount.MAX_MONETARY_AMOUNT
						.add(BigDecimal.ONE)));
	}

	@Test
	public void testIsGreaterThanMaxMonetaryValue_notGreater() {
		assertFalse(MonetaryAmounts
				.isGreaterThanMaxMonetaryValue(MonetaryAmount.MAX_MONETARY_AMOUNT));
	}

	BigDecimal scaledValue(String value) {
		return new BigDecimal(value).setScale(MonetaryAmount.SCALE, ROUND_UNNECESSARY);
	}

	@Test
	void convertToCents() {
		assertEquals(100, MonetaryAmounts.convertToCents(MonetaryAmount.ONE));
		assertEquals(1000, MonetaryAmounts.convertToCents(MonetaryAmount.TEN));
		assertEquals(10000, MonetaryAmounts.convertToCents(MonetaryAmount.HUNDRED));
		assertEquals(-10000, MonetaryAmounts.convertToCents(MonetaryAmount.HUNDRED.negate()));
	}

	@Test
	void convertToCentsMaxValue() {
		MonetaryAmount maxAllowedAmount = MonetaryAmounts.convertFromCents(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, MonetaryAmounts.convertToCents(maxAllowedAmount));
	}

	@Test
	void convertToCentsOverflow() {
		MonetaryAmount maxAllowedAmount = MonetaryAmounts.convertFromCents(Integer.MAX_VALUE);
		assertThrows(ArithmeticException.class,
				() -> MonetaryAmounts.convertToCents(maxAllowedAmount.add(MonetaryAmount.ONE)));
	}

	@Test
	void convertFromCents() {
		assertEquals(MonetaryAmount.ONE, MonetaryAmounts.convertFromCents(100));
		assertEquals(MonetaryAmount.TEN, MonetaryAmounts.convertFromCents(1000));
		assertEquals(MonetaryAmount.HUNDRED, MonetaryAmounts.convertFromCents(10000));
		assertEquals(MonetaryAmount.HUNDRED.negate(), MonetaryAmounts.convertFromCents(-10000));
	}
}
