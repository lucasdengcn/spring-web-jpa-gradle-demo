
## Naming Convention

* entity : for persistence
* model : for response and input

## Tech Stack
* JDK 21
* Kotlin 1.9
* Gradle 8.5
* Spring Boot 3.2.3
* JUnit 5
* Mockk 1.12.0
* Maven 3.9.6

## Practices
* entity
* modeling
* input validation
* exception handler
* unit test
* integration test
* mapstruct for mapper (input to entity, entity to model)
* declarative http clients
* jpa repository

## Tools
* Open API Generator
* Jacoco

## Testing
* https://medium.com/backyard-programmers/kotlin-spring-boot-unit-testing-integration-testing-with-junit5-and-mockk-a2977bbe5711
* https://www.baeldung.com/spring-boot-testing

## Open API Generator
* https://openapi-generator.tech/docs/installation
* https://openapi-generator.tech/docs/generators/spring
* https://openapi-generator.tech/docs/generators/kotlin-spring
* https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc
* https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin.md
* https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/kotlin-spring.md
* https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md
* example shell as following
```shell
openapi-generator generate \
    -i policies-openapi-v1_2.yaml \
    -g kotlin-spring \
    -o output \
    --package-name com.prudential.symphony.domains.policy \
    --additional-properties=library=spring-boot,useSpringBoot3=true,useBeanValidation=true \
    --import-mappings=DateTime=java.time.LocalDateTime \
    --type-mappings=DateTime=java.time.LocalDateTime
```

## Jacoco
* https://reflectoring.io/jacoco/

## Building
* keywords: BUILD FAILED

## Signing
* https://docs.github.com/en/actions/learn-github-actions/signing-artifacts
* https://docs.gradle.org/current/userguide/signing_plugin.html#sec:signing_publications

## Questions
* Kotlin constructor with optional parameters

## Reference

https://mkyong.com/spring-boot/spring-boot-spring-data-jpa-postgresql/

https://thorben-janssen.com/hibernate-features-with-spring-data-jpa/

https://www.51cto.com/article/766636.html

https://lexcao.io/zh/posts/spring-data-jpa-join-table/

https://www.baeldung.com/spring-data-jpa-batch-inserts

https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-jpa-crud/src/main/java/com/baeldung/softdelete/Product.java

https://docs.spring.io/spring-framework/reference/integration/rest-clients.html

https://www.baeldung.com/spring-data-jpa-dynamicupdate

https://www.baeldung.com/spring-graphql-error-handling

https://www.baeldung.com/kotlin/kotest-spring-boot-test

https://techdozo.dev/spring-for-graphql-pagination-with-code-example/
