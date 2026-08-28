# Feature Specification: Owners for Spring Petclinic

**Feature Branch**: `012-owners-spring-petclinic`

**Created**: 2026-08-28

**Status**: Draft

**Input**: User description: "owners for spring-petclinic"

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Find Owners by Last Name (Priority: P1)

As a clinic staff member, I want to search for owners by their last name so that I can quickly access their information and their pets' details.

**Why this priority**: This is a core functionality for managing customer information and is essential for daily operations.

**Independent Test**: Can be fully tested by entering a known last name in the search field and verifying the correct owner(s) are displayed, delivering immediate access to owner data.

**Acceptance Scenarios**:

1. **Given** there are owners in the system, **When** a user searches for owners by a last name starting with "Franklin", **Then** the system displays a list of owners whose last names start with "Franklin" and redirects to the owner's detail page.
2. **Given** there are no owners with a specific last name, **When** a user searches for that last name, **Then** the system displays a "not found" error message.

---

### User Story 2 - Create a New Owner (Priority: P1)

As a clinic staff member, I want to add new owners to the system so that I can register new clients and their pets.

**Why this priority**: Essential for onboarding new customers and expanding the client base.

**Independent Test**: Can be fully tested by filling out the new owner form with valid data and verifying the owner is created and displayed, delivering the ability to add new clients.

**Acceptance Scenarios**:

1. **Given** a user is on the new owner creation form, **When** they submit a valid owner form, **Then** the owner is created and a success message is displayed.
2. **Given** a user is on the new owner creation form, **When** they submit a form with missing required fields (e.g., first name, last name, address, city, telephone), **Then** the system rejects the submission and displays validation errors for each missing field.

---

### User Story 3 - Add a New Pet for an Existing Owner (Priority: P2)

As a clinic staff member, I want to add a new pet for an existing owner so that I can keep track of all their animals.

**Why this priority**: Important for maintaining a complete record of an owner's pets.

**Independent Test**: Can be fully tested by selecting an existing owner, navigating to the add pet form, filling it with valid data, and verifying the pet is associated with the owner, delivering the ability to record new pets.

**Acceptance Scenarios**:

1. **Given** an owner exists in the system, **When** a user adds a new pet with a unique name, valid birth date, and selects a pet type, **Then** the pet is successfully added to the owner's record.
2. **Given** an owner exists, **When** a user attempts to add a pet with a name that already exists for that owner, **Then** the system rejects the new pet creation and displays an "already exists" error message for the pet's name.

---

### User Story 4 - Add a New Visit for a Pet (Priority: P2)

As a clinic staff member, I want to add a new visit for a pet so that I can record the details of their appointments.

**Why this priority**: Crucial for tracking pet health history and appointment management.

**Independent Test**: Can be fully tested by selecting an existing pet, navigating to the add visit form, filling it with valid data, and verifying the visit is recorded for that pet, delivering the ability to log appointments.

**Acceptance Scenarios**:

1. **Given** a pet exists in the system, **When** a user adds a new visit with a valid date and description, **Then** the visit is successfully recorded for the pet.
2. **Given** a pet exists, **When** a user attempts to add a visit with an invalid date (e.g., a date in the past), **Then** the system rejects the visit creation and displays a validation error for the date.

---

### User Story 5 - Update an Existing Owner's Details (Priority: P3)

As a clinic staff member, I want to update an existing owner's details so that I can ensure their contact information is current.

**Why this priority**: Important for maintaining accurate contact information for owners.

**Independent Test**: Can be fully tested by selecting an owner, modifying their details (e.g., address, phone number), saving the changes, and verifying the updated information is displayed, delivering the ability to correct owner data.

**Acceptance Scenarios**:

1. **Given** an owner exists, **When** a user updates their address and telephone number with valid information, **Then** the owner's details are updated successfully.
2. **Given** an owner exists, **When** a user attempts to update their address with a blank value, **Then** the system rejects the update and displays a validation error for the address.

---

### Edge Cases

- What happens when an owner's telephone number does not match the 10-digit pattern? The system rejects the update/creation with a validation error.
- How does the system handle attempts to create a pet with a birth date in an incorrect format? The system rejects the creation with a validation error.
- What happens when a user tries to access or modify data for a non-existent owner ID? The system throws an `IllegalArgumentException`.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: System MUST allow the creation of a new owner with first name, last name, address, city, and telephone.
- **FR-002**: System MUST allow the updating of an existing owner's details.
- **FR-003**: System MUST allow owners to be searched by last name.
- **FR-004**: System MUST allow the creation of a new pet for an existing owner, including pet name, birth date, and type.
- **FR-005**: System MUST allow the updating of an existing pet's details.
- **FR-006**: System MUST allow the addition of new visits for a pet, including date and description.
- **FR-007**: System MUST validate owner data upon creation or update, enforcing non-blank fields for address and city, and a 10-digit pattern for telephone.
- **FR-008**: System MUST validate pet data upon creation or update, enforcing non-blank names and valid birth dates.
- **FR-009**: System MUST enforce that a pet's name is unique for a given owner.
- **FR-010**: System SHOULD validate pet data upon creation or update.
- **FR-011**: System SHOULD allow owners to be searched by last name.
- **FR-012**: System SHOULD allow the addition of new visits for a pet.

### Key Entities *(include if feature involves data)*

- **Owner**: Represents a client of the pet clinic. Includes first name, last name, address, city, and telephone number. Can own multiple pets.
- **Pet**: Represents an animal belonging to an owner. Includes name, birth date, and type. Can have multiple visits.
- **PetType**: Represents the species of a pet (e.g., Cat, Dog).
- **Visit**: Represents a veterinary visit for a pet. Includes date and a description of the visit.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: Users can find owners by last name in under 3 seconds.
- **SC-002**: New owners can be created with all required fields in under 1 minute.
- **SC-003**: New pets can be added to an owner's record in under 45 seconds.
- **SC-004**: New visits can be recorded for a pet in under 30 seconds.
- **SC-005**: 99% of data entry operations (owner creation/update, pet creation/update, visit creation) complete successfully without validation errors when valid data is provided.
- **SC-006**: Validation errors are clearly displayed to the user for all invalid data submissions.

## Assumptions

- Users have stable internet connectivity.
- The system will be accessed via a web browser.
- Standard date formats will be used for input.
- The system will reuse existing `BaseEntity` and `NamedEntity` classes from the `org.springframework.samples.petclinic.model` package.
- Validation annotations (`jakarta.validation`) will be used for data integrity.
- JPA annotations will be used for entity mapping.
- The system will leverage Spring Boot's auto-configuration.
- Error handling for non-existent IDs will follow the pattern of throwing `IllegalArgumentException`.