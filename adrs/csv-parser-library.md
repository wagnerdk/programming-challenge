# CSV parsing library

## Context

Parsing CSV data is a well-known challenge, and there exist multiple tested and actively maintained libraries to address this issue. Therefore, it is necessary to select an appropriate library. 
This decision evaluates two popular Java libraries, [OpenCSV](https://opencsv.sourceforge.net/) and [Apache CSV](https://commons.apache.org/proper/commons-csv/), to determine which one best aligns with the project's requirements. The library should be maintained and allow writing robust, correct and readable code. Furthermore, existing expertise should be leveraged. 
Both libraries feature a permissive open source license and regular bug fixes and feature releases. 


## Decision

I decided to use Opencsv because it allows to leverage the existing expertise with the library. Moreover, the use of annotations for data-to-object mapping in Opencsv follows a more declarative and intuitive approach, resulting in better readable code. This declarative approach simplifies the codebase, making it more maintainable for a small project.

## Consequences

The use of Opencsv will speed up development efforts and reduces testing needs. The annotation based approach allows for readable and maintainable code.



Based on the ADR template from [Decision record template by Michael Nygard](https://github.com/joelparkerhenderson/architecture-decision-record/blob/main/locales/en/templates/decision-record-template-by-michael-nygard/index.md)