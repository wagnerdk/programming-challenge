# Adopt Hamcrest for improved Test Assertions

## Context

In software development projects, writing and maintaining test cases is a critical part of ensuring the quality and reliability of the code.
JUnit supports basic assertions out of the box, but [Hamcrest](https://hamcrest.org/) provides a fluent way to express readable and maintainable assertions. 
Furthermore, Hamcrest uses a permissive BSD-3 license.


## Decision

I adopt the Hamcrest framework to leverage the readable assertions Hamcrest provides. This choice will improve the readability and maintainability of the test code. Hamcrest's flexible and extensible matching capabilities will also allow us to create custom matchers if the need arises. 

## Consequences

Test assertions written with Hamcrest will be more natural and expressive, making it easier for developers to understand the purpose of each test case.


Based on the ADR template from [Decision record template by Michael Nygard](https://github.com/joelparkerhenderson/architecture-decision-record/blob/main/locales/en/templates/decision-record-template-by-michael-nygard/index.md)