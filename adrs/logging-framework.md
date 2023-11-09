# Using SLF4J for logging

## Context

There are multiple different logging frameworks with different advantages and drawbacks.
I propose adopting [SLF4J (Simple Logging Facade for Java)](https://www.slf4j.org/) to allow the usage of different logging backends, so it is possible to support multiple backends and 
change the selected one later. SLF4J uses the permissive [MIT](https://www.slf4j.org/license.html) license. Furthermore, SLF4J is actively maintained.


## Decision

Use SLF4J as a flexible and compatible logging solution.
Start with the [simple backend implementation](https://www.slf4j.org/manual.html#swapping) and change the backend if further requirements arise.
Furthermore, SLF4J provides a simple and readable logging API which helps with the maintainability of the project. 

## Consequences

Logging will be easier and integrate well with other libraries and frameworks that support SLF4J. If one backend implementation is no longer maintained, swapping to a different one is simple which helps with the maintainability of the project. 



Based on the ADR template from [Decision record template by Michael Nygard](https://github.com/joelparkerhenderson/architecture-decision-record/blob/main/locales/en/templates/decision-record-template-by-michael-nygard/index.md)