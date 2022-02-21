package com.amberlight.test.web.apps.domain.monetary.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintViolationException;


import com.amberlight.test.web.apps.domain.monetary.MonetaryAmount;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MonetaryValueValidatorTests {

	@Test
	public void testNumber_valid() {
		MonetaryValueValidator.validNumber("name", "100");
		MonetaryValueValidator.validNumber("name", "10.343434");
	}

	@Test
	public void testNumber_null() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validNumber("name", (String)null));
	}

	@Test
	public void testNumber_empty() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validNumber("name", ""));
	}

	@Test
	public void testNumber_notNumber() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validNumber("name", "a"));
	}

	@Test
	public void testPositiveNumber_valid() {
		String number = "10.343434";
		BigDecimal validated = MonetaryValueValidator.positiveNumber("name", number);
		assertThat(validated, comparesEqualTo(new BigDecimal(number)));
	}

	@Test
	public void testPositiveNumber_null() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.positiveNumber("name", null));
	}

	@Test
	public void testPositiveNumber_zero() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.positiveNumber("name", "0"));
	}


	@Test
	public void testPositiveNumber_negative() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.positiveNumber("name", "-1"));
	}

	@Test
	public void testNotNegativeNumber_valid() {
		String number = "10.343434";
		BigDecimal validated = MonetaryValueValidator.notNegativeNumber("name", number);
		assertThat(validated, comparesEqualTo(new BigDecimal(number)));
	}


	@Test
	public void testNotNegativeNumber_null() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.notNegativeNumber("name", null));
	}

	@Test
	public void testNotNegativeNumber_zero() {
		BigDecimal validated = MonetaryValueValidator.notNegativeNumber("name", "0");
		assertThat(validated, comparesEqualTo(BigDecimal.ZERO));
	}

	@Test
	public void testNotNegativeNumber_negative() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.notNegativeNumber("name", "-1"));
	}

	@Test
	public void testAmount_valid() {
		String amount = "10.343434";
		BigDecimal validated = MonetaryValueValidator.validAmount("name", amount);
		assertThat(validated, comparesEqualTo(new BigDecimal(amount)));
	}

	@Test
	public void testAmount_nullString() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validAmount("name", (String) null));
	}


	@Test
	public void testAmount_empty() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validAmount("name", ""));
	}

	@Test
	public void testAmount_notNumber() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validAmount("name", "f89"));
	}

	@Test
	public void testAmount_validScale() {
		String amount = "0.00000001";
		BigDecimal validated = MonetaryValueValidator.validAmount("name", amount);
		assertThat(validated, comparesEqualTo(new BigDecimal(amount)));
	}

	@Test
	public void testAmount_scaleTooLarge() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validAmount("name", "0.000000001"));
	}

	@Test
	public void testAmount_validMaxAmount() {
		String argument = MonetaryAmount.MAX_MONETARY_VALUE;
		BigDecimal validated = MonetaryValueValidator.validAmount("name", argument);
		assertThat(validated, comparesEqualTo(MonetaryAmount.MAX_MONETARY_AMOUNT));
	}

	@Test
	public void testAmount_amountTooLarge() {
		String argument = MonetaryAmount.MAX_MONETARY_AMOUNT
				.add(BigDecimal.ONE).toString();

		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.validAmount("name", argument));
	}

	@Test
	public void testPositiveAmount_valid() {
		String amount = "10.343434";
		BigDecimal validated = MonetaryValueValidator.positiveAmount("name", amount);
		assertThat(validated, comparesEqualTo(new BigDecimal(amount)));
	}

	@Test
	public void testPositiveAmount_zero() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.positiveAmount("name", "0"));
	}

	@Test
	public void testPositiveAmount_negative() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.positiveAmount("name", "-1"));
	}

	@Test
	public void testNotNegativeAmount_valid() {
		String amount = "10.2452";
		BigDecimal validated = MonetaryValueValidator.notNegativeAmount("name", amount);
		assertThat(validated, comparesEqualTo(new BigDecimal(amount)));
	}

	@Test
	public void testNotNegativeAmount_zero() {
		MonetaryValueValidator.notNegativeAmount("name", "0");
	}

	@Test
	public void testNotNegativeAmount_negative() {
		assertThrows(ConstraintViolationException.class,
				() -> MonetaryValueValidator.notNegativeAmount("name", "-1"));
	}

}