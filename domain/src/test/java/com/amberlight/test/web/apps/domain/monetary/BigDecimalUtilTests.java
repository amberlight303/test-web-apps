package com.amberlight.test.web.apps.domain.monetary;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.*;

class BigDecimalUtilTests {

	@Test
	void setScale() {
	}

	@Test
	void testSetScale() {
	}

	@Test
	void isPositive() {
		assertTrue(BigDecimalUtil.isPositive(BigDecimal.ONE));
		assertFalse(BigDecimalUtil.isPositive(BigDecimal.ZERO));
		assertFalse(BigDecimalUtil.isPositive(BigDecimal.ONE.negate()));
	}

	@Test
	void isZero() {
		assertTrue(BigDecimalUtil.isZero(BigDecimal.ZERO));
		assertFalse(BigDecimalUtil.isZero(BigDecimal.ONE));
	}

	@Test
	void isNegative() {
		assertTrue(BigDecimalUtil.isNegative(BigDecimal.ONE.negate()));
		assertFalse(BigDecimalUtil.isNegative(BigDecimal.ZERO));
		assertFalse(BigDecimalUtil.isNegative(BigDecimal.ONE));
	}

	@Test
	void isGreaterThan() {
		assertTrue(BigDecimalUtil.isGreaterThan(BigDecimal.ONE, BigDecimal.ZERO));
		assertFalse(BigDecimalUtil.isGreaterThan(BigDecimal.ZERO, BigDecimal.ONE));
		assertFalse(BigDecimalUtil.isGreaterThan(BigDecimal.ONE, BigDecimal.ONE));
	}

	@Test
	void isLessThan() {
		assertTrue(BigDecimalUtil.isLessThan(BigDecimal.ZERO, BigDecimal.ONE));
		assertFalse(BigDecimalUtil.isLessThan(BigDecimal.ONE, BigDecimal.ZERO));
		assertFalse(BigDecimalUtil.isLessThan(BigDecimal.ONE, BigDecimal.ONE));
	}

	@Test
	void isEqualTo() {
		assertTrue(BigDecimalUtil.isEqualTo(BigDecimal.ZERO, BigDecimal.ZERO));
		assertTrue(BigDecimalUtil.isEqualTo(BigDecimal.ONE, BigDecimal.ONE));
		assertFalse(BigDecimalUtil.isEqualTo(BigDecimal.ONE, BigDecimal.ZERO));
	}

	@Test
	void isEqualToOrGreaterThan() {
		assertTrue(BigDecimalUtil.isEqualToOrGreaterThan(BigDecimal.ONE, BigDecimal.ZERO));
		assertTrue(BigDecimalUtil.isEqualToOrGreaterThan(BigDecimal.ONE, BigDecimal.ONE));
		assertFalse(BigDecimalUtil.isEqualToOrGreaterThan(BigDecimal.ZERO, BigDecimal.ONE));
	}

	@Test
	void isEqualToOrLessThan() {
		assertTrue(BigDecimalUtil.isEqualToOrLessThan(BigDecimal.ZERO, BigDecimal.ONE));
		assertTrue(BigDecimalUtil.isEqualToOrLessThan(BigDecimal.ONE, BigDecimal.ONE));
		assertFalse(BigDecimalUtil.isEqualToOrLessThan(BigDecimal.ONE, BigDecimal.ZERO));
	}

	@Test
	void add() {
		assertThat(BigDecimalUtil.add(BigDecimal.ONE, "10"), comparesEqualTo(new BigDecimal("11")));
	}

	@Test
	void subtract() {
		assertThat(BigDecimalUtil.subtract(BigDecimal.TEN, "2"), comparesEqualTo(new BigDecimal("8")));
	}

	@Test
	void divide() {
		assertThat(BigDecimalUtil.divide(BigDecimal.ONE, "10"), comparesEqualTo(new BigDecimal("0.1")));
	}


	@Test
	void multiply() {
		assertThat(BigDecimalUtil.multiply(BigDecimal.TEN, "2"), comparesEqualTo(new BigDecimal("20")));
	}

	@Test
	void convertFromCents() {
		assertThat(BigDecimalUtil.convertFromCents(100), comparesEqualTo(BigDecimal.ONE));
		assertThat(BigDecimalUtil.convertFromCents(1000), comparesEqualTo(BigDecimal.TEN));
	}

	@Test
	void convertToCents() {
		assertEquals(100, BigDecimalUtil.convertToCents(BigDecimal.ONE));
		assertEquals(1000, BigDecimalUtil.convertToCents(BigDecimal.TEN));
	}
}