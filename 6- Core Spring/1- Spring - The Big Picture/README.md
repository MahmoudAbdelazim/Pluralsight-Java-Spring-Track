# Spring: The Big Picture
Spring is a family of open-source projects that helps create enterprise-level Java applications.

<hr>

# The Spring Ecosystem
## The Spring Framework
The Spring framework is a Java framework that was created to make writing java code for Web and Data access easier and less challenging than Java EE, althought it's now so much more than just an alternative to Java EE.

It also helped greatly in reducing boilerplate code in Java applications in order to focus on business logic.

## Spring Projects
Many Spring projects were built on top of the Spring Framework, some of them are:
- Spring Data
- Spring Batch
- Spring Security
- Spring Session
- Spring Integration
- Spring Web Services
- And many others

## Spring Boot
On top of these projects, Spring Boot was created in order to make code far more easier, with less configuration code and boilerplate code.

## Spring Cloud
Spring Cloud was built on top of Spring Boot to provide easier building of distributed architectures such as microservice architecture.

<hr>

# What is Spring Boot?
Spring Boot makes getting started with Spring Quick and Easy.

Spring Boot's notable features:
- Supports <b>Auto-configuration</b>: It tries to automatically auto-configure a Spring application based on the dependencies added.
- Creates <b>Standalone applications</b>: unlike typical running of Java web applications, Spring Boot apps can just run! packaged as a normal jar file.
- Spring Boot is <b>Opinionated</b>: Spring Boot favors convention over configuration.

<hr>

# Spring's Foundation: The Spring Framework
The Spring Framework is the basis of all other Spring projects in the Spring family, it's composed of Six key areas:

1. <b>Spring Core</b>: Acts the basis for the other areas, with its main feature being a <b> Dependency Injection</b> container.
2. <b>Spring Web</b>: A framework for handling web requests, which is divided to <b>Spring MVC</b> and <b>Spring Webflux</b>, which supports reactive programming in Spring.
3. <b>Spring AOP</b>: Supports Aspect-Oriented Programming in Spring.
4. <b>Spring Data Access</b>: Makes data access easier than regular JDBC code in Java, notice that this is different than the Spring Data project, which extends Spring Data Access and also provides support for non-relational database models.
5. <b>Spring Integration</b>: Makes talking to other applications or systems easier using Web Services and Messaging Services.
6. <b>Spring Testing</b>: Spring utilizes Dependency Injection which makes testing easier while mocking dependencies, which makes unit and integration testing easier.

<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)