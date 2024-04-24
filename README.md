<h1 align="center">Spring Boot Starter Requery</h1>

<p align="center">
    <img src="./project-logo.png" width="240" height="120"/>
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
provides autoconfiguration for the [Spring Requery by Codexio](https://github.com/CodexioLtd/spring-requery).

This means, you receive an autoconfigured set of beans, so you can just start accepting `Specification`
objects into your `@RestController`s.

## Table of Contents

* [Preambule](#preambule)
* [Table of Contents](#table-of-contents)
* [Quick Start](#quick-start)
* [Contributing](#contributing)
* [License](#license)

## Quick Start

## Contributing

This project could use a support and contributors are very welcomed. If you feel that something has to be
changed or a bug to be fixed, you can report a [new issue](https://github.com/CodexioLtd/spring-boot-starter-requery/issues/new), and
we can take care of it.

If you want to submit directly a code fix, we will be more than glad to see it. Fork the repository and start a clean
branch out of the version you want to patch. When you are finished, make sure all your tests are passing and the
coverage remains in decent level by executing `mvn clean test jacoco:report -Pmvn-deploy`.

Please use the [code style](./codestyle.xml)
in the project root folder. If your IDE does not support it, we strongly encourage you just to follow
the code styling in the rest of the classes and methods.

After all, your tests are passing and the coverage seems good to you, create a
[pull request](https://github.com/CodexioLtd/spring-boot-starter-requery/compare). We will review the request and either leave
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