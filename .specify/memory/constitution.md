# Spring Petclinic Constitution

## Core Principles

### I. Layered Architecture Adherence
Every component MUST reside within its designated architectural layer (Controller, Repository, Domain/Model, Configuration, Service, Test). Cross-layer dependencies MUST follow a strict top-down flow (e.g., Controllers depend on Services, Services depend on Repositories). Direct dependencies between non-adjacent layers are prohibited unless explicitly justified and documented.

### II. Spring Boot Convention Over Configuration
The project MUST leverage Spring Boot's auto-configuration capabilities wherever possible. Custom configurations (e.g., `WebConfiguration.java`, `CacheConfiguration.java`) MUST be minimal, well-documented, and only introduced when standard conventions are insufficient or require explicit override for performance or specific functional requirements.

### III. Comprehensive Test Coverage (NON-NEGOTIABLE)
All new features and bug fixes MUST be accompanied by unit and integration tests. Unit tests MUST target individual components (e.g., Controllers, Services, Models) in isolation. Integration tests (e.g., `OwnerControllerIntegrationTests`, `ClinicServiceTests`) MUST verify the interaction between multiple components and the persistence layer, ensuring end-to-end functionality. Test coverage metrics MUST be maintained and reviewed.

### IV. Domain Model Integrity
Domain entities (e.g., `Owner.java`, `Pet.java`, `Vet.java`) MUST be POJOs with clear responsibilities. They MUST adhere to JPA standards for persistence and utilize validation annotations (`jakarta.validation`) for data integrity. Relationships between entities MUST be explicitly defined and managed.

### V. Observability and Debuggability
The application MUST be designed with observability in mind. While explicit logging configurations are not detailed in the provided snippets, the expectation is that standard Spring Boot logging mechanisms are utilized. The use of `CrashController.java` suggests a mechanism for handling and reporting unexpected errors.

## Development Workflow

The development workflow for the Spring Petclinic project will adhere to the following practices:

*   **Feature Branching:** All development will occur on feature branches, branched from the main development branch.
*   **Code Reviews:** All pull requests MUST undergo a thorough code review by at least one other team member. Reviews will focus on adherence to architectural principles, code quality, test coverage, and correctness.
*   **Automated Testing:** A CI/CD pipeline MUST be in place to automatically run all unit and integration tests on every commit and pull request. Builds that fail tests MUST be flagged and prevented from merging.
*   **Dependency Management:** Dependencies MUST be managed via Maven (or an equivalent build tool). Updates to dependencies MUST be carefully considered and tested to avoid introducing regressions.
*   **Database Integration:** Integration tests that interact with the database (e.g., `MySqlIntegrationTests.java`, `PostgresIntegrationTests.java`) MUST be clearly marked and executed within the CI pipeline. Test data management strategies should be employed to ensure test independence.

## Governance

This constitution supersedes all other development practices and guidelines for the `rvishwa1709/spring-petclinic` repository. Amendments to this constitution require a formal proposal, a documented justification for the change, and approval by a majority of the core development team. Any approved amendments MUST include a migration plan to ensure existing code and practices are brought into compliance. All pull requests and code reviews MUST verify compliance with this constitution.

**Version**: 1.0.0 | **Ratified**: 2026-08-28 | **Last Amended**: 2026-08-28