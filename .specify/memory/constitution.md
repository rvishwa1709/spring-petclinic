# Spring Petclinic Constitution

## Core Principles

### I. Layered Architecture Adherence
Every component MUST reside within its designated architectural layer (Controller, Repository, Domain/Model, Configuration, Service, Test). Cross-layer dependencies MUST follow the defined hierarchy (e.g., Controllers depend on Services, Services depend on Repositories).

### II. Spring Boot Convention Compliance
The project MUST leverage Spring Boot's auto-configuration and conventions. Configuration MUST be managed via `application.properties` or `application.yml`, and Spring Beans MUST be declared using standard annotations (`@Component`, `@Service`, `@Repository`, `@Controller`, `@Configuration`).

### III. Test-Driven Development (TDD) and Comprehensive Testing
All new features and bug fixes MUST be accompanied by unit and integration tests. Unit tests MUST focus on individual components, while integration tests MUST verify interactions between components and with external systems (e.g., database). The project MUST maintain a high level of test coverage, as evidenced by the presence of numerous test files across various layers.

### IV. JPA and Spring Data Repository Usage
Data persistence MUST be handled using JPA entities and Spring Data repositories. Repositories MUST expose standard CRUD operations and custom query methods as needed, adhering to Spring Data conventions.

### V. RESTful API Design
Controllers MUST expose RESTful endpoints following standard HTTP methods (GET, POST, PUT, DELETE) and status codes. Data transfer between client and server SHOULD utilize JSON.

## Additional Constraints

The project MUST utilize Java as the primary programming language.
The project MUST be built using Maven.
The project MUST support internationalization (i18n) as demonstrated by the `I18nPropertiesSyncTest` and `WebConfiguration`.

## Development Workflow

Code changes MUST be submitted via Pull Requests.
All Pull Requests MUST undergo a code review by at least one other team member.
Automated checks (e.g., CI builds, static analysis) MUST pass before merging.
Database schema changes MUST be managed through appropriate migration tools if applicable (though not explicitly detailed in the provided files, it's a standard practice for JPA projects).

## Governance
This Constitution supersedes all other development practices for the `rvishwa1709/spring-petclinic` repository. Amendments to this Constitution require a formal proposal, review by core contributors, and a documented migration plan for affected code. Compliance with this Constitution is a mandatory requirement for all code merged into the main branch.

**Version**: 1.0.0 | **Ratified**: 2026-08-28 | **Last Amended**: 2026-08-28