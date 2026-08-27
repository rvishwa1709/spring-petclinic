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
 * Unit tests for {@link Visit} entity validation.
 */
class VisitTests {

	private Validator validator;

	@BeforeEach
	void setUp() {
		try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
			this.validator = factory.getValidator();
		}
	}

	@Test
	void validateDescription_when1Character_thenValid() {
		Visit visit = new Visit();
		visit.setDescription("a");

		Set<ConstraintViolation<Visit>> constraintViolations = this.validator.validate(visit);

		assertThat(constraintViolations).isEmpty();
	}

	@Test
	void validateDescription_when500Characters_thenValid() {
		Visit visit = new Visit();
		visit.setDescription("a".repeat(500));

		Set<ConstraintViolation<Visit>> constraintViolations = this.validator.validate(visit);

		assertThat(constraintViolations).isEmpty();
	}

	@Test
	void validateDescription_whenBlank_thenInvalid() {
		Visit visit = new Visit();
		visit.setDescription("   ");

		Set<ConstraintViolation<Visit>> constraintViolations = this.validator.validate(visit);

		assertThat(constraintViolations).isNotEmpty();
		assertThat(constraintViolations).anyMatch(v -> v.getPropertyPath().toString().equals("description"));
	}

	@Test
	void validateDescription_when501Characters_thenInvalid() {
		Visit visit = new Visit();
		visit.setDescription("a".repeat(501));

		Set<ConstraintViolation<Visit>> constraintViolations = this.validator.validate(visit);

		assertThat(constraintViolations).isNotEmpty();
		assertThat(constraintViolations).anyMatch(v -> v.getPropertyPath().toString().equals("description"));
	}

}
