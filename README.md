## Intern Assignment Enhancement: ByteSize and TimeDuration Parsers
##  Task Summary

As part of the Wrangler framework, I contributed by **creating two new parser classes**:
- `ByteSize.java`
- `TimeDuration.java`

These classes extend the directive parser functionality and introduce the ability to parse new data types within transformation recipes.

##  What Was Done

- Implemented `ByteSize` parser: handles values like `10MB`, `500KB`, etc.
- Created `TimeDuration` parser: interprets durations like `5s`, `10min`, `2h`, etc.
- Integrated both into the Wrangler directive system.
- Ensured both files comply with Apache License 2.0 headers.
- Updated `pom.xml` with necessary dependencies (e.g., `gson`).

##  Build Instructions

To compile the project:
```bash
cd wrangler
mvn clean install