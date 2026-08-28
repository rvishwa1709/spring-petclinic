# Feature Specification: Owners for Spring Petclinic

**Feature Branch**: `007-owners-spring-petclinic`

**Created**: 2026-08-28

**Status**: Draft

**Input**: User description: "owners for spring-petclinic"

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Find Owners by Last Name (Priority: P1)

As a clinic staff member, I want to search for owners by their last name so that I can quickly access their information.

**Why this priority**: This is a core functionality for managing customer information and is essential for daily operations.

**Independent Test**: Can be fully tested by entering a last name prefix in the search field and verifying the displayed list of owners.

**Acceptance Scenarios**:

1. **Given** there are multiple owners in the system with last names starting with "Smi", **When** a user searches for owners with the last name prefix "Smi", **Then** a list of owners whose last names start with "Smi" (e.g., Smith, Smither) is displayed.
2. **Given** there are no owners in the system with the last name "XYZ", **When** a user searches for owners with the last name "XYZ", **Then** a "not found" error message is displayed.
3. **Given** there is exactly one owner in the system with the last name "Doe", **When** a user searches for owners with the last name "Doe", **Then** the user is redirected to that owner's details page.

---

### User Story 2 - Create a New Owner (Priority: P2)

As a new user, I want to create a new owner profile so that I can register myself or a new client.

**Why this priority**: This allows for the expansion of the customer base and is a fundamental data entry operation.

**Independent Test**: Can be fully tested by filling out the new owner form with valid data and verifying the owner is created and their details page is displayed.

**Acceptance Scenarios**:

1. **Given** a user is on the new owner form, **When** they submit a valid owner form with all required fields (first name, last name, address, city, telephone), **Then** the owner is created and the user is redirected to the newly created owner's details page.

---

### User Story 3 - View Owner List (Priority: P3)

As a clinic staff member, I want to view a list of all owners so that I can get an overview of registered clients.

**Why this priority**: Provides a general overview and is useful for browsing or when a specific owner is not known.

**Independent Test**: Can be fully tested by navigating to the owners list page and verifying that all existing owners are displayed.

**Acceptance Scenarios**:

1. **Given** there are 5 owners in the system, **When** a user navigates to the owners list page, **Then** all 5 owners are displayed with their basic information.

---

### Edge Cases

- What happens when an owner's last name is blank during search? → System treats it as an empty string for searching and displays "not found" if no other criteria match.
- How does system handle an invalid telephone number format during owner creation/update? → System rejects the input with a validation error.
- What happens when a non-existent owner ID is used for editing or viewing? → System throws an `IllegalArgumentException`.

## Requirements *(mandatory)*

### Functional Requirements

- **FR-001**: System MUST allow the creation of new owners with first name, last name, address, city, and telephone.
- **FR-002**: System MUST validate owner data during creation and update, ensuring first name, last name, address, and city are not blank, and telephone is exactly 10 digits.
- **FR-003**: System MUST allow searching for owners by last name prefix.
- **FR-004**: System MUST display a "not found" message if no owners match the search criteria.
- **FR-005**: System MUST redirect to the owner's details page if exactly one owner matches the search criteria.
- **FR-006**: System MUST display a list of all owners when requested.
- **FR-007**: System MUST allow viewing an owner's details by their ID.
- **FR-008**: System MUST allow updating an existing owner's information.
- **FR-009**: System MUST allow the creation of new pets for an owner.
- **FR-010**: System MUST validate pet data (name, type, birth date) during creation or update.
- **FR-011**: System SHOULD allow updating an existing pet's name.
- **FR-012**: System SHOULD allow retrieving an owner's details by ID.
- **FR-013**: System SHOULD allow finding owners by last name.

### Key Entities *(include if feature involves data)*

- **Owner**: Represents a pet owner. Attributes include first name, last name, address, city, and telephone. Has a one-to-many relationship with `Pet`.
- **Pet**: Represents a pet belonging to an owner. Attributes include name, birth date, and type. Has a many-to-one relationship with `Owner` and a one-to-many relationship with `Visit`.
- **PetType**: Represents the type of a pet (e.g., Cat, Dog). Has a one-to-many relationship with `Pet`.
- **Visit**: Represents a visit to the clinic for a pet. Attributes include date and description. Has a many-to-one relationship with `Pet`.

## Success Criteria *(mandatory)*

### Measurable Outcomes

- **SC-001**: Users can find owners by last name prefix in under 3 seconds.
- **SC-002**: New owner creation is completed successfully for 99% of valid submissions.
- **SC-003**: The owner list page loads within 2 seconds for up to 100 owners.
- **SC-004**: System successfully validates all owner and pet data according to defined business rules.

## Assumptions

- Users have stable internet connectivity.
- The system will be accessed via a web browser.
- Existing authentication mechanisms (if any) are handled separately and are not part of this feature's scope.
- Data persistence is handled by an underlying database and repository layer.
- The system will use standard date formats for input and display.