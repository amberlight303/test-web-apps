package com.amberlight.test.web.apps.domain.ipaddress.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.InetAddressValidator;


public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

	private final InetAddressValidator inetAddressValidator;

	public IpAddressValidator() {
		this.inetAddressValidator = InetAddressValidator.getInstance();
	}

	public void initialize(IpAddress constraintAnnotation) {
	}

	public boolean isValid(String ipAddress, ConstraintValidatorContext cvContext) {
		return ipAddress == null || inetAddressValidator.isValid(ipAddress);
	}
}
