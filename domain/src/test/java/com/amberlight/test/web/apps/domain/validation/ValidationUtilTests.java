package com.amberlight.test.web.apps.domain.validation;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;


import com.amberlight.test.web.apps.domain.config.ValidationAutoConfig;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {ValidationAutoConfig.class})
public class ValidationUtilTests {

	@Resource
	private ValidationUtil validationUtil;

	@Test
	void validateValidObject() {
		TestEntity testEntity = new TestEntity(new Object());
		assertSame(testEntity, validationUtil.validateObject(testEntity));
	}

	@Test
	void validateInvalidObject() {
		assertThrows(ConstraintViolationException.class, () ->
				validationUtil.validateObject(new TestEntity(null)));
	}

	private static class TestEntity {
		@NotNull
		private final Object obj;

		TestEntity(Object obj) {
			this.obj = obj;
		}
	}
}
