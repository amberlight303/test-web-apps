package com.amberlight.test.web.apps.domain.validation;


import com.amberlight.test.web.apps.domain.monetary.MonetaryAmount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * A custom constraint validator that checks that a monetary amount is valid.
 */
public class MonetaryAmountValidator implements	ConstraintValidator<Amount, MonetaryAmount> {

	private String min;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(Amount constraintAnnotation) {
		this.min = constraintAnnotation.min();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(MonetaryAmount amount,
						   ConstraintValidatorContext cvContext) {

		if (amount == null) {
			return true;
		}

		if (this.min == null) {
			return true;
		}

		return ArgumentPredicates.minValue(Integer.parseInt(this.min)).apply(
				amount.getValue());
	}
}
