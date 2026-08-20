package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

class PetValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldValidateWhenBirthDateIsInThePast() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Pet pet = new Pet();
		pet.setName("Lucky");
		pet.setBirthDate(LocalDate.now().minusDays(1));

		Validator validator = createValidator();
		Set<ConstraintViolation<Pet>> constraintViolations = validator.validate(pet);

		assertThat(constraintViolations).isEmpty();
	}

	@Test
	void shouldValidateWhenBirthDateIsPresent() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Pet pet = new Pet();
		pet.setName("Lucky");
		pet.setBirthDate(LocalDate.now());

		Validator validator = createValidator();
		Set<ConstraintViolation<Pet>> constraintViolations = validator.validate(pet);

		assertThat(constraintViolations).isEmpty();
	}

	@Test
	void shouldNotValidateWhenBirthDateIsInTheFuture() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Pet pet = new Pet();
		pet.setName("Lucky");
		pet.setBirthDate(LocalDate.now().plusDays(1));

		Validator validator = createValidator();
		Set<ConstraintViolation<Pet>> constraintViolations = validator.validate(pet);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Pet> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("birthDate");
		assertThat(violation.getMessage()).isEqualTo("Birth date cannot be in the future");
	}

}