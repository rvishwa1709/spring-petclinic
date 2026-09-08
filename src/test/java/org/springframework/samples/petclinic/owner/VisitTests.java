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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Test class for {@link Visit}.
 */
class VisitTests {

	private Validator validator;

	@BeforeEach
	void setUp() {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			validator = factory.getValidator();
		}
	}

	@Test
	void validateWithValidDescriptionSingleCharacter() {
		Visit visit = new Visit();
		visit.setDescription("a");
		Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
		assertThat(violations).isEmpty();
	}

	@Test
	void validateWithValidDescription500Characters() {
		Visit visit = new Visit();
		visit.setDescription("a".repeat(500));
		Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
		assertThat(violations).isEmpty();
	}

	@Test
	void validateWithBlankDescription() {
		Visit visit = new Visit();
		visit.setDescription("   ");
		Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
		assertThat(violations).isNotEmpty();
	}

	@Test
	void validateWithNullDescription() {
		Visit visit = new Visit();
		visit.setDescription(null);
		Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
		assertThat(violations).isNotEmpty();
	}

	@Test
	void validateWithDescriptionExceeding500Characters() {
		Visit visit = new Visit();
		visit.setDescription("a".repeat(501));
		Set<ConstraintViolation<Visit>> violations = validator.validate(visit);
		assertThat(violations).isNotEmpty();
	}

}
