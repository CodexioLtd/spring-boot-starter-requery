<h1 align="center">Spring Boot Starter Requery</h1>

<p align="center">
    <img src="./codexio-logo.png" width="555" height="90"/>
    <br/>
    <em>
        May your queries be requested.
    </em>
</p>

<div align="center">

[![Maven Central](https://img.shields.io/maven-central/v/bg.codexio.springframework.boot/spring-boot-starter-requery?color=EE5A9C)](https://central.sonatype.com/artifact/bg.codexio.springframework.boot/spring-boot-starter-requery)
[![Build](https://github.com/CodexioLtd/spring-boot-starter-requery/actions/workflows/maven.yml/badge.svg)](https://github.com/CodexioLtd/spring-boot-starter-requery/actions/workflows/maven.yml)
[![License](https://img.shields.io/github/license/CodexioLtd/spring-boot-starter-requery.svg)](./LICENSE)

</div>

<hr/>

## Preambule

The Spring <img src="https://spring.io/img/logos/spring-initializr.svg" width="18px" height="18px"/> Boot starter
provides autoconfiguration for the [Spring Requery by Codexio](https://github.com/CodexioLtd/spring-requery) provides
autoconfiguration to seamlessly integrate dynamic query capabilities into your Spring Boot applications. This starter
automatically sets up all necessary beans, allowing you to directly inject `Specification` objects into your controllers
and services.

## Table of Contents

* [Preambule](#preambule)
* [Table of Contents](#table-of-contents)
* [Features](#features)
* [Quick Start](#quick-start)
* [Autoconfiguration Details](#autoconfiguration-details)
* [Usage](#usage)
    * [Basic Usage Example](#basic-usage-example)
    * [JSON Filter Request Example](#json-filter-request-example)
* [Contributing](#contributing)
* [License](#license)

## Features

* **Auto Configuration:** Automatically configures your Spring Boot project to integrate with `Spring Requery`.
* **Simplified Setup:** Reduces setup complexity and configuration overhead.
* **Enhanced Productivity:** Focus more on business logic rather than boilerplate code.

## Quick Start

1. Open your Spring Boot Project
2. Add the `Spring Boot Starter Requery` as a dependency:

```xml

<dependency>
    <groupId>bg.codexio.springframework.boot</groupId>
    <artifactId>spring-boot-starter-requery</artifactId>
    <version>1.0.4</version>
</dependency>
```

3. Spring Boot autoconfiguration will take care of the rest, setting up all necessary components to use `Requery` in
   your application.
4. Now, that everything is set up, you can start utilizing `Requery`'s ability to convert JSON formatted filters into
   `JPA Specifications`.

## Autoconfiguration Details

* `DialectAutoConfiguration` ensures that the appropriate Hibernate dialect is used based on the configured database
  type (e.g.,
  PostgreSQL, MySQL). This automation helps maintain efficient SQL generation and optimize database interaction without
  manual configuration.
    * **Extensibility:** While default configurations cover common database types using enhanced dialects for PostgreSQL
      and MySQL, you can easily extend this setup to use custom dialects for other databases. By providing your own
      `Function<String, Class<? extends Dialect>>` bean, you can override the default dialect determination logic to
      include additional databases or customized dialect behaviors. Example to extend the custom dialect configuration:
        ```java
        @Bean
        @ConditionalOnMissingBean
        public Function<String, Class<? extends Dialect>> dialectDeterminer() {
            return driverName -> switch (driverName) {
                case "org.h2.Driver" -> YourCustomH2Dialect.class;
                default -> null;
            };
        }
        ```
* `SwaggerAutoConfiguration` enhances your project by automatically configuring Swagger documentation for APIs that use
  **Requery**.

## Usage

After integrating the **Spring Boot Starter Requery** into your project, you can begin using its features to simplify
the implementation of dynamic queries. Here's a simple example to get you started:

### Basic Usage Example

Imagine you have a Spring Boot application where you need to query user data based on dynamically specified conditions.
Below is how you could set up a controller to handle such requests:

```java

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findUsers(Specification<User> spec) {
        return ResponseEntity.ok(this.userRepository.findAll(spec));
    }
}
```

In this setup:

* The `UserController` uses Spring Data JPA’s Specification to filter data, which is autoconfigured by the starter to
  convert JSON formatted filters into database queries.
* A client could send a request with a filter specifying attributes to query by, which are then parsed into
  a `Specification`.

### JSON Filter Request Example

To filter users by first name starting with "John" in a case-insensitive manner, the client sends the following JSON:

```json
{
  "field": "firstName",
  "operation": "BEGINS_WITH_CASEINS",
  "value": "John"
}
```

It's important to note that the requests should be properly URL encoded and the given example request would look like
this:

```
GET /users?filter=%5B%7B%22field%22:%22firstName%22,%22value%22:%22John%22,%22operation%22:%22BEGINS_WITH_CASEINS%22%7D%5D&page=0&size=100
```

This request leverages the autoconfiguration provided by the **Spring Boot Starter Requery** to process and execute the
filter on the server side.

For more comprehensive examples and detailed usage instructions, please refer to
the [Spring Requery by Codexio](https://github.com/CodexioLtd/spring-requery) documentation. This
documentation provides extensive examples on implementing complex queries, custom filters, and leveraging the full
capabilities of the **Spring Requery** library.

By exploring these resources, you can enhance your application's functionality and fully utilize the dynamic query
generation features provided by **Spring Requery**.

## Contributing

This project could use a support and contributors are very welcomed. If you feel that something has to be
changed or a bug to be fixed, you can report
a [new issue](https://github.com/CodexioLtd/spring-boot-starter-requery/issues/new), and
we can take care of it.

If you want to submit directly a code fix, we will be more than glad to see it. Fork the repository and start a clean
branch out of the version you want to patch. When you are finished, make sure all your tests are passing and the
coverage remains in decent level by executing `mvn clean test jacoco:report -Pmvn-deploy`.

Please use the [code style](./codestyle.xml)
in the project root folder. If your IDE does not support it, we strongly encourage you just to follow
the code styling in the rest of the classes and methods.

After all, your tests are passing and the coverage seems good to you, create a
[pull request](https://github.com/CodexioLtd/spring-boot-starter-requery/compare). We will review the request and either
leave
some meaningful suggestions back or maybe merge it and release it with the next release.

...

## License

Copyright 2024 [Codexio Ltd.](https://codexio.bg)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
