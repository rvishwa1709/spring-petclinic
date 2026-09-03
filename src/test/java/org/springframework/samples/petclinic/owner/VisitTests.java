/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Visit} validation constraints.
 */
class VisitTests {

	private Validator validator;

	@BeforeEach
	void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	@Test
	void shouldValidateValidDescription() {
		Visit visit = new Visit();
		visit.setDescription("a");

		Set<ConstraintViolation<Visit>> violations = this.validator.validate(visit);
		assertThat(violations).isEmpty();
	}

	@Test
	void shouldValidateDescriptionWith500Characters() {
		Visit visit = new Visit();
		visit.setDescription("a".repeat(500));

		Set<ConstraintViolation<Visit>> violations = this.validator.validate(visit);
		assertThat(violations).isEmpty();
	}

	@Test
	void shouldNotValidateDescriptionWith501Characters() {
		Visit visit = new Visit();
		visit.setDescription("a".repeat(501));

		Set<ConstraintViolation<Visit>> violations = this.validator.validate(visit);
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("description");
	}

	@Test
	void shouldNotValidateBlankDescription() {
		Visit visit = new Visit();
		visit.setDescription("");

		Set<ConstraintViolation<Visit>> violations = this.validator.validate(visit);
		assertThat(violations).isNotEmpty();
	}

}
