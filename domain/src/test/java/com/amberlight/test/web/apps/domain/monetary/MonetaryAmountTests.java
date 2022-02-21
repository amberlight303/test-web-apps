package com.amberlight.test.web.apps.domain.monetary;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonetaryAmountTests {

	BigDecimal scaledValue(String value) {
		return new BigDecimal(value).setScale(MonetaryAmount.SCALE);
	}

	@Test
	public void testCreate_fromString() {

		BigDecimal amountValue = scaledValue("0.22");

		String amountString = amountValue.toPlainString();

		MonetaryAmount amount = new MonetaryAmount(amountString);

		assertNotNull(amount);
		assertNotNull(amount.getValue());
		assertEquals(amount.getValue(), amountValue);
		assertEquals(amount.getValue().toString(), amountString);
		assertEquals(amount.getValue().scale(), MonetaryAmount.SCALE);
	}

	@Test
	public void testCreate_fromString_null() {

		assertThrows(ConstraintViolationException.class,
				() -> new MonetaryAmount((String) null));
	}

	@Test
	public void testCreate_fromString_maxExceeded() {

		assertThrows(ConstraintViolationException.class,
				() -> new MonetaryAmount(MonetaryAmount.MAX_MONETARY_AMOUNT.add(
						BigDecimal.ONE).toPlainString()));
	}

	@Test
	public void testCreate_fromBigDecimal() {

		BigDecimal amountValue = scaledValue("0.1111");

		MonetaryAmount amount = new MonetaryAmount(amountValue);

		assertNotNull(amount);
		assertNotNull(amount.getValue());
		assertEquals(amount.getValue(), amountValue);
		assertEquals(amount.getValue().scale(), MonetaryAmount.SCALE);
	}

	@Test
	public void testCreate_fromBigDecimal_null() {

		assertThrows(ConstraintViolationException.class,
				() -> new MonetaryAmount((BigDecimal) null));
	}

	@Test
	public void testCreate_fromBigDecimal_maxExceeded() {

		assertThrows(ConstraintViolationException.class,
				() -> new MonetaryAmount(MonetaryAmount.MAX_MONETARY_AMOUNT.add(BigDecimal.ONE)));
	}

	@Test
	public void testComparableImplementation() {
		MonetaryAmount one = MonetaryAmount.ONE;
		MonetaryAmount ten = MonetaryAmount.TEN;

		assertEquals(one.compareTo(ten), -1);
		assertEquals(ten.compareTo(one), 1);
		assertEquals(one.compareTo(one), 0);
	}

	@Test
	public void testIsGreaterThan() {
		MonetaryAmount one = MonetaryAmount.ONE;
		MonetaryAmount ten = MonetaryAmount.TEN;

		assertTrue(ten.isGreaterThan(one));
		assertFalse(one.isGreaterThan(ten));
	}

	@Test
	public void testIsLessThan() {
		MonetaryAmount one = MonetaryAmount.ONE;
		MonetaryAmount ten = MonetaryAmount.TEN;

		assertFalse(ten.isLessThan(one));
		assertTrue(one.isLessThan(ten));
	}

	@Test
	public void testMultiply() {

		MonetaryAmount amount = new MonetaryAmount(BigDecimal.ONE);
		MonetaryAmount multiplicand = new MonetaryAmount(BigDecimal.TEN);

		BigDecimal result = scaledValue(amount.getValue()
				.multiply(multiplicand.getValue()).toPlainString());

		assertEquals(result, amount.multiply(multiplicand).getValue());
		assertEquals(result, amount.multiply(multiplicand.getValue()).getValue());
	}

	@Test
	public void testDivide() {

		MonetaryAmount amount = new MonetaryAmount(BigDecimal.ONE);
		MonetaryAmount dividend = new MonetaryAmount(BigDecimal.TEN);

		BigDecimal result = scaledValue(amount.getValue()
				.divide(dividend.getValue(), RoundingMode.UNNECESSARY).toPlainString());

		assertEquals(result, amount.divide(dividend).getValue());
		assertEquals(result, amount.divide(dividend.getValue()).getValue());
	}


	@Test
	public void testIsEqualTo() {
		assertEquals(new MonetaryAmount("1"), new MonetaryAmount("1"));
	}

	@Test
	public void testIsEqualTo_notEqual() {
		assertNotEquals(new MonetaryAmount("1"), new MonetaryAmount("2"));
	}

}
