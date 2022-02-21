package com.amberlight.test.web.apps.domain.ipaddress.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IpAddressValidatorTests {

	IpAddressValidator validator = new IpAddressValidator();

	@Test
	void testValidIpV4Address_returns_true() {
		assertTrue(validator.isValid("1.1.1.1", null));
	}

	@Test
	void testInvalidIpv4Address_returns_false() {
		assertFalse(validator.isValid("11111.1.1.1", null));
	}

	@Test
	void testValidIpv6Address_returns_true() {
		assertTrue(validator.isValid("2001:4860:4860::8888", null));
	}

	@Test
	void testInvalidIpv6Address_returns_false() {
		assertFalse(validator.isValid("fe80:2030:31:24", null));
	}

	@Test
	void testNullIpAddress_returns_true() {
		assertTrue(validator.isValid(null, null));
	}
}
