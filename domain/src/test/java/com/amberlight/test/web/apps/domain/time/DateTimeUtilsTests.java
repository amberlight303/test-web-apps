package com.amberlight.test.web.apps.domain.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeUtilsTests {

	@Test
	void fromToMillis() {
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
		assertEquals(now, DateTimeUtils.fromMillis(DateTimeUtils.toMillis(now)));
	}
}
