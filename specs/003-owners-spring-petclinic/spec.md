# Feature Specification: Owners for Spring Petclinic

**Feature Branch**: `003-owners-spring-petclinic`

**Created**: 2026-08-28

**Status**: Draft

**Input**: User description: "owners for spring-petclinic"

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Find Owners by Last Name (Priority: P1)

As a clinic staff member, I want to search for owners by their last name so that I can quickly find and access their information.

**Why this priority**: This is a core functionality for managing owner data and is essential for daily operations.

**Independent Test**: Can be fully tested by entering a last name prefix in the search field and verifying the returned list of owners, delivering the ability to locate specific owners.

**Acceptance Scenarios**:

1. **Given** a list of owners exists with various last names, **When** I enter "Dav" into the owner search field, **Then** a list of owners whose last names start with "Dav" (e.g., David, Davis) is displayed.
2. **Given** no owners match a specific last name prefix, **When** I enter "XYZ" into the owner search field, **Then** a message indicating no owners were found is displayed.

---

### User Story 2 - Create a New Owner (Priority: P1)

As a clinic staff member, I want to create a new owner record so that I can register new clients and their pets.

**Why this priority**: Essential for onboarding new customers and expanding the clinic's client base.

**Independent Test**: Can be fully tested by filling out the new owner form with valid data and submitting it, verifying that the owner is created and their details page is displayed.

**Acceptance Scenarios**:

1. **Given** I am on the "New Owner" form, **When** I enter valid first name, last name, address, city, and telephone number, and click "Save", **Then** the new owner is created and I am redirected to the owner's details page.
2. **Given** I am on the "New Owner" form, **When** I leave the address field blank and click "Save", **Then** a validation error message is displayed for the address field, and the owner is not created.

---

### User Story 3 - View Owner Details (Priority: P2)

As a clinic staff member, I want to view the details of an existing owner so that I can access all their information, including their pets and visits.

**Why this priority**: Allows for comprehensive understanding of a client's history and needs.

**Independent Test**: Can be fully tested by navigating to an existing owner's details page and verifying all associated information is displayed correctly.

**Acceptance Scenarios**:

1. **Given** an owner exists with associated pets and visits, **When** I navigate to the owner's details page, **Then** all owner attributes (name, address, city, telephone), their pets (name, birth date, type), and their visits (date, description) are displayed.

---

### User Story 4 - Add a New Pet for an Owner (Priority: P2)

As a clinic staff member, I want to add a new pet for an existing owner so that I can register their animal companions.

**Why this priority**: Crucial for maintaining accurate records of a client's pets.

**Independent Test**: Can be fully tested by selecting an owner, navigating to their pet management section, and adding a new pet with valid details.

**Acceptance Scenarios**:

1. **Given** I am viewing an owner's details page, **When** I click "Add New Pet", fill in the pet's name, birth date, and select a pet type, and click "Save", **Then** the new pet is associated with the owner and displayed on their details page.
2. **Given** I am on the "New Pet" form for an owner, **When** I attempt to create a pet with a name that already exists for that owner, **Then** a validation error is displayed indicating the pet name must be unique for the owner.

---

### User Story 5 - Add a New Visit for a Pet (Priority: P3)

As a clinic staff member, I want to add a new visit for a pet so that I can record veterinary appointments and treatments.

**Why this priority**: Important for tracking a pet's health history and medical interventions.

**Independent Test**: Can be fully tested by selecting a pet, navigating to its visit history, and adding a new visit with valid details.

**Acceptance Scenarios**:

1. **Given** I am viewing a pet's details page, **When** I click "Add New Visit", enter a visit date and description, and click "Save", **Then** the new visit is recorded and displayed in the pet's visit history.
2. **Given** I am on the "New Visit" form for a pet, **When** I enter a blank description and click "Save", **Then** a validation error message is displayed for the description field, and the visit is not created.

---

### Edge Cases

- **Blank Owner Address**: Owner creation/update with a blank address will result in a validation error for the `address` field.
- **Blank Owner City**: Owner creation/update with a blank city will result in a validation error for the `city` field.
- **Invalid Owner Telephone**: Owner creation/update with a telephone number not matching the `\d{10}` pattern will result in a validation error for the `telephone` field.
- **Non-existent Owner ID**: Attempting to find an owner with an ID that does not exist will result in an `IllegalArgumentException`.
- **Blank Pet Name**: Pet creation/update with a blank name will result in a validation error for the `name` field.
- **Missing Pet Type**: Pet creation with a missing pet type will result in a validation error for the `type` field.
- **Duplicate Pet Name**: Attempting to create a pet with a name that already exists for the same owner will result in a validation error indicating duplication.
- **Invalid Pet Birth Date**: Pet creation/update with an invalid birth date format (e.g., "2015/02/12") will result in a validation error for the `birthDate` field with `typeMismatch`.
- **Blank Visit Date**: Visit creation with a blank date will result in a validation error for the `date` field.
- **Visit Date in the Past**: Visit creation with a date that is not after the current date will result in a validation error for the `date` field with `typeMismatch.visitDate`.
- **Non-existent Owner ID for Pet Operations**: Attempting to perform pet-related operations for an owner ID that does not exist will result in an `IllegalArgumentException`.
- **Non-existent Pet ID for Visit Operations**: Attempting to perform visit-related operations for a pet ID that does not exist for a given owner will result in an `IllegalArgumentException`.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: System MUST allow the creation of new pets for an owner.
- **FR-002**: System MUST validate pet data during creation or update.
- **FR-003**: System SHOULD allow owners to be searched by last name.
- **FR-004**: System SHOULD allow updating a pet's name.
- **FR-005**: System SHOULD allow adding new visits for a pet.
- **FR-006**: System MUST enforce that owner first names are not blank.
- **FR-007**: System MUST enforce that owner last names are not blank.
- **FR-008**: System MUST enforce that owner addresses are not blank.
- **FR-009**: System MUST enforce that owner cities are not blank.
- **FR-010**: System MUST enforce that owner telephone numbers are exactly 10 digits.
- **FR-011**: System MUST enforce that pet names are not blank.
- **FR-012**: System MUST enforce that visit descriptions are not blank.
- **FR-013**: System MUST enforce that a pet's name is unique for a given owner.

### Key Entities *(include if feature involves data)*

- **Owner**: Represents a client of the veterinary clinic. Attributes include first name, last name, address, city, and telephone number. An owner can have multiple pets.
- **Pet**: Represents an animal owned by a client. Attributes include name, birth date, and type. A pet belongs to one owner and can have multiple visits.
- **PetType**: Represents the type of animal (e.g., Dog, Cat, Hamster). A pet type can be associated with multiple pets.
- **Visit**: Represents a veterinary visit for a pet. Attributes include date and description. A visit is associated with one pet.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: Users can find owners by last name prefix in under 2 seconds.
- **SC-002**: New owner creation is completed successfully for 99% of valid submissions.
- **SC-003**: Owner details are displayed completely and accurately within 1 second of navigation.
- **SC-004**: Adding a new pet for an owner is completed successfully for 99% of valid submissions.
- **SC-005**: Adding a new visit for a pet is completed successfully for 99% of valid submissions.
- **SC-006**: Validation errors for all mandatory fields are displayed to the user within 0.5 seconds of submission.

## Assumptions

- Users interacting with the system are clinic staff members.
- The system will be used with a stable internet connection.
- Data persistence will be handled by an underlying database, managed via Spring Data JPA.
- The project will use standard date and time formats for input and display.
- The telephone number format `\d{10}` is sufficient for all regional requirements.
- The system will leverage existing Spring Boot and Spring Framework conventions.
- Internationalization (i18n) will be handled using Spring's mechanisms, with all user-facing strings externalized.