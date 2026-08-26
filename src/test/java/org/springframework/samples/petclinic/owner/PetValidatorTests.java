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

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link PetValidator}
 */
class PetValidatorTests {

	private PetValidator petValidator;

	private Pet pet;

	@BeforeEach
	void setUp() {
		this.petValidator = new PetValidator();
		this.pet = new Pet();
		this.pet.setName("Betty");
		PetType petType = new PetType();
		petType.setName("hamster");
		this.pet.setType(petType);
	}

	@Test
	void shouldAcceptPastBirthDate() {
		this.pet.setBirthDate(LocalDate.now().minusDays(1));
		Errors errors = new BeanPropertyBindingResult(this.pet, "pet");
		this.petValidator.validate(this.pet, errors);

		assertThat(errors.hasFieldErrors("birthDate")).isFalse();
	}

	@Test
	void shouldAcceptCurrentBirthDate() {
		this.pet.setBirthDate(LocalDate.now());
		Errors errors = new BeanPropertyBindingResult(this.pet, "pet");
		this.petValidator.validate(this.pet, errors);

		assertThat(errors.hasFieldErrors("birthDate")).isFalse();
	}

	@Test
	void shouldRejectFutureBirthDate() {
		this.pet.setBirthDate(LocalDate.now().plusDays(1));
		Errors errors = new BeanPropertyBindingResult(this.pet, "pet");
		this.petValidator.validate(this.pet, errors);

		assertThat(errors.hasFieldErrors("birthDate")).isTrue();
		assertThat(errors.getFieldError("birthDate").getCode()).isEqualTo("typeMismatch.birthDate");
	}

	@Test
	void shouldRejectNullBirthDate() {
		this.pet.setBirthDate(null);
		Errors errors = new BeanPropertyBindingResult(this.pet, "pet");
		this.petValidator.validate(this.pet, errors);

		assertThat(errors.hasFieldErrors("birthDate")).isTrue();
		assertThat(errors.getFieldError("birthDate").getCode()).isEqualTo("required");
	}

}
