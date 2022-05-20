# Spring Fundamentals

# What is Spring?
Spring at its core is just an Inversion of Control container for Dependency Injection, which is, instead of instantiating the dependencies between objects ourselves inside the objects, Spring injects those dependencies into our objects for us.

<hr>

# Spring Java Configuration
Java configuration is the latest and most popular way of configuring a Spring application, instead of XML configuration which required writing XML code to configure Spring applications.

## The `@Configuration` Annotation
To create a Java configuration class, simply create a class and annotate it with `@Configuration`.

## Setter Injection
Setter injection is simply about injecting dependencies into our Beans using their setter methods.
For example, we can do this inside our config class:
```
@Bean(name = "speakerService")
public SpeakerService getSpeakerService() {
    SpeakerServiceImpl service = new SpeakerServiceImpl();
    service.setRepository(getSpeakerRepository());
    return service;
}

@Bean(name = "speakerRepository")
public SpeakerRepository getSpeakerRepository() {
    return new HibernateSpeakerRepositoryImpl();
}
```

## Constructor Injection
Just like setter injection, dependencies can be injected using the constructor.
For example, we can do this inside our config class:
```
@Bean(name = "speakerService")
public SpeakerService getSpeakerService() {
    return newSpeakerServiceImpl(getSpeakerRepository());
}

@Bean(name = "speakerRepository")
public SpeakerRepository getSpeakerRepository() {
    return new HibernateSpeakerRepositoryImpl();
}
```

[Why you should use Constructor Injection in Spring?](https://reflectoring.io/constructor-injection/)

<hr>

# Spring Bean Scopes
There are 5 scopes for Beans in Spring:
- Singleton (Default)
- Prototype
- Request
- Session
- Global

The last 3 Bean scopes are only used in Spring Web projects.

Defining the scope of a Bean is done using the `@Scope(value = "scope")` annotation to the class or the Bean method.

A Singleton Bean is a Bean that is created once per Spring container, so whenever this bean is referenced, the same object in the application context gets injected.

A Prototype Bean is a Bean that is created uniquely once per request.

<hr>

# Autowiring
Autowiring and Component Scanning lets Spring scan our objects for dependencies and auto-inject them into our objects without writing the configuration code ourselves.

Component Scanning is done using the `@ComponentScan` annotation on the configuration class, giving it the name of the package to scan components from.

Classes can be scanned by using one of the stereotype annotations which are `@Component`, `@Service`, and `@Repository`.

Autowiring is done using the `@Autowired` Annotation, either on the constructor for constructor injection, or on the setter for setter injection.






<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)