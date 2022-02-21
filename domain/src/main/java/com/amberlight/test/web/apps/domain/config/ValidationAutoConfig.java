package com.amberlight.test.web.apps.domain.config;

import javax.validation.Validation;


import com.amberlight.test.web.apps.domain.validation.ValidationUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationAutoConfig {

	@Bean
	@ConditionalOnMissingBean
	public ValidationUtil validationUtil() {
		return new ValidationUtil(Validation.buildDefaultValidatorFactory().getValidator());
	}
}
