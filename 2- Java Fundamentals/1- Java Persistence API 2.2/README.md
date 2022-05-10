# Java Persistence API 2.2
Java Persistence API is an API that makes working with relational databases easier, and helps build applications that require Object-Relational Mapping (ORM).
<hr>

# 1- Introduction

## Before JPA
If we're not working with JPA, we'll have to work with JDBC (Java Database Connectivity), which is a uniform but low level and verbose way of working with databases in Java, but it's the base of any database operations that JPA supports.

## Working with JDBC
In order to make database queries using JDBC, the process is quite verbose and we'll have to write much boilerplate code.

Example: Writing a query to persist Book object in H2 database:

```
public class Book {
    private Long id;
    private String title;
    private String description;
    private Float unitCost;
    private String isbn;
    // Constructors, getters & setters
}
```

1- We'll have to create a DriverManager to connect to the database:
```
private static ConnectiongetConnection() throws SQLException{
    return DriverManager.getConnection(
    "jdbc:h2:mem:module01-db");
}
```
2- Write the SQL Query we need to perform:
```
String query = "INSERT INTO BOOK (ID, TITLE, DESCRIPTION,UNITCOST, ISBN) VALUES (?, ?, ?, ?, ?)";
```
3- create and excute a prepared statment:
```
try (PreparedStatement stmt = getConnection().prepareStatement(query)){
    stmt.setLong(1, book.getId());
    stmt.setString(2, book.getTitle());
    stmt.setString(3, book.getDescription());
    stmt.setFloat(4, book.getUnitCost());
    stmt.setString(5, book.getIsbn());
    stmt.executeUpdate();
}
```
4- Close the connection and catch possible SQL Exceptions.

## What is wrong with JDBC?
- SQL is not Java, you'll have to write code in another programming language inside your Java code.
- JDBC is low level API.
- SQL is not easy to refactor.
- JDBC is verbose and requires much boilerplate code and Exception Hanlding.
- Hard to read.
- Hard to maintain.

## How would this be different in JPA?
Looking at the same example:

1- We'll have to annotate Book with ``@Entity`` and an ``@Id`` annotation:
```
@Entity
public class Book {
    @Id
    private Long id;
    private String title;
    private String description;
    private Float unitCost;
    private String isbn;
    // Constructors, getters & setters
}
```

2- We'll create an EntityManager using an EntityManagerFactory:
```
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("module01PU");
private static EntityManager em = emf.createEntityManager();
```
3- We'll use the EntityManager's persist method to save the object:
```
em.persist(book);
```
### Advantages of JPA:
- No manual mapping.
- No SQL statements.
- Non intrusive.
- Higher level of abstraction
- Lightweight.
- Elegant API.
- Easier to mainain.

<hr>

# 2- Understanding JPA

## What is Object Relational Mapping?
Object Relational Mapping (ORM) tools help in bringing relational databases and objects together.

They help in persisting objects that are created using Object-Oriented Programming into tables in relational databases.

## What is JPA?
JPA is an abstraction above JDBC, it's an ORM Framework that brings an easy mechanism to map objects to relational databases.

It has an EntityManager API to perform relational database operations such as CRUD.

It also comes with a powerful language called Java Persistence Query Language.

### Note:
JPA only works with relational databases.

## JPA Specification:
JPA is a standard Java Specification that specifies some APIs to work with, Java doesn't provide the implementations for JPA.

JPA needs a runtime provider that implements this specifications.

## JPA Implementations (Providers):
Currently there are 3 major JPA implementations which are:
- Hibernate
- EclipseLink
- Apache Open JPA

<hr>

# 3- Mapping Entities with JPA
The central service to manipulate instances of Entities is an `EntityManager`, which is created using an EntityManagerFactory in JavaSE, and can be injected in JavaEE and Spring.

The configuration for a set of Entities is called a Persistence Unit, defined in a file called `persistence.xml`.

`EntityManager` is an interface that is responsible for creating, removing, and finding entities by their primary keys.

## CRUD Operations with JPA
This is how we can apply CRUD using JPA EntityManager:

We'll first create the EntityManager:
```
private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
private EntityManager em = emf.createEntityManager();
private EntityTransaction tx = em.getTransaction();
```

### 1- Create
```
public Book createBook(Book book) {
    tx.begin();
    em.persist(book);
    tx.commit();
    return book;
}
```

### 2- Find
```
public Book findBook(Long id) {
    return em.find(Book.class, id);
}
```

### 3- Delete
```
public void removeBook(Long id) {
    Book book = em.find(Book.class, id);
    if (book != null) {
        tx.begin();
        em.remove(book);
        tx.commit();
    }
}
```
or
```
public void removeBook(Book book) {
    Book bookToBeDeleted = em.merge(book);
    tx.begin();
    em.remove(bookToBeDeleted);
    tx.commit();
}
```
### 4- Update
```
public Book raiseUnitCost(Book book, Float raise) {
    Book bookToBeUpdated = em.merge(book);
    tx.begin();
    bookToBeUpdated.setUnitCost(bookToBeUpdated.getUnitCost() + raise);
    tx.commit();
    return book;
}
```

## Default Entity Mapping
With JPA, the provider has default set of rules that are applied to control the mapping of relational entities.
A table's name will be the same as the Entity name in the code, as well as the column names.

Also, data types are mapped by default, a string is mapped to a varchar(255), and a long is mapped to a bigint in database.

These defaults can be changed using annotations:
- `@Table(name = "_")` allows you to change table name
- `@Column(name = "_")` allows you to change column name, and we can also change many aspects of the column here like length, nullable, etc.
- An enumerated type should be annotated with `@Enumerated`.

<hr>

# 4- Relationships and Inheritance
Mapping Object-Oriented Relationships to relational databases is done easier with JPA.

## One-To-One Mapping
in a unidirectional and directional one-to-one relationship, JPA automatically applies annotations `@OneToOne` and `@JoinColumn` with a default column name of the other entity's name + id, which can be changed if we want.

## One-To-Many Mapping
in a one-to-many relationship, an Entity has a collection of instances of the other Entity.

Example:
```
@Entity
public class CD {
    @OneToMany
    private Set<Musician> musicians = new HashSet<>();
}

@Entity
public class Musician {
    @ManyToOne
    private CD cd;
}
```

## Many-To-Many Mapping
in a many-to-many relationship, an Entity may have a collection of instances of the other Entity, and the other Entity may have a collection of instances of the Entity.

Example:
```
@Entity
public class CD {
    @ManyToMany
    private Set<Musician> musicians = new HashSet<>();
}

@Entity
public class Musician {
    @ManyToMany
    private Set<CD> cd = new HashSet<>;
}
```
## Cascading Events
IN JPA, events (persist and remove) by default are not cascaded for any relationships.
This means that if an object contains a collection of another object, calling persist on the root object will not call persist on the objects inside, same for remove.

To apply cascading, we add a `cascade` parameter to the relationship annotation.

Example:
```
@Entity
public class CD {
    @OneToMany(cascade = {PERSIST, REMOVE, MERGE}) // OR cascade = ALL
    private Set<Musician> musicians = new HashSet<>();
}
```

## Fetching Relationships
What about the find method?
When we call find on a CD will it also fetch the Musicians inside it? this depends on fetching mode.
### Eager Fetching
Eager fetching means that when the object is loaded, this also propagates to the objects it relates to, this requires one database access through join queries and the objects get loaded into memory.

### Lazy Fetching
Laze fetching means that the objects it relates to don't get loaded eagerly, instead, they get loaded when a getter method is called on them.

By default in JPA, `@OneToOne` and `ManyToOne` relationships are eagerly fetched, while `@OneToMany` and `ManyToMany` relationships are lazily fetched.

To change this default, a `fetch` attribute is added to the relationship with values `LAZY` or `EAGER`.

## Inheritance in JPA
inheritance is completely unkown in relational databases.

That's why it needs mapping of a structural inheritance model to a flat relational structure.

JPA has three strategies for mapping inheritance:

### 1- Single Type Strategy
In this strategy, when a subclass inherits from a superclass, the database will contain a single table for both of them called by default with the name of the superclass, and has the columns of all the attributes in both the superclass and the subclass, and also has a column for the type of object.

This strategy also requires that columns of the subclass to be nullable, in order to be able to add new rows with the values of the parent only.

This is the default strategy for inheritance mapping in JPA.
### 2- Joined-Subclass Strategy
In this strategy, when a subclass inherits from a superclass, the database will contain two tables, one for the superclass columns and the other for the subclass columns.

When an object from the subclass is persisted, it will actually be saved in database as two rows, one in the superclass table defining the superclass attributes, and the other in the subclass table, that's why it's called joined.
### 3- Table-per-concrete-class Strategy
In this strategy, each Entity is mapped to its own table with the full list of attributes it has (from itself and parent).

To change the inheritance strategy in JPA, an `@Inheritance` annotation is added to the superclass entity, with an attribute `strategy = SINGLE_TABLE or JOINED_CLASS or TABLE_PER_CLASS`'.

<hr>

# 5- Querying Entities

JPA has two sides:
- the first is to map objects to relational database
- the second is to query these mapped objects

The EntityManager has different abilities in terms of querying entities using JPQL (Java Persistance Query Language).

## JPQL
JPQL is a query language that is similar to SQL but in Java and in an object-oriented approach, and these queries are translated into SQL.

JPQL has a syntax very similar to SQL, but instead of calling entities with table names we call them with their Class names, and using their attributes and relations as ordinary objects with the "`.`" notation.

Example:

SQL Query:
```
SELECT title AS Title, COUNT(*) AS Authors
FROM item
JOIN book_author
ON item.id = book_author.book_fk
GROUP BY item.title;
```
Using JPQL:
```
SELECT b.title, COUNT(a)
FROM Book b
LEFT JOIN b.authors a
GROUP BY b
```

<hr>

# 6- Entity Lifecycle

Entities have states depending on the operations performed, and these states allow us to do certain operations and event callbacks to add business logic to our entities.

1- When an entity object is created with the `new` keyword, its state is "Transient", meaning that it's a normal POJO.

2- When the `persist()` method is called on this POJO, its state become "Managed", meaning that this object is managed by the database, and changes to it will by synchronized with the database.

3- When the `find()` method is called it also returns a managed object.

4- When the `merge()` method is called on an object, the object's state is "Detached" and the object returned is "Managed".

5- When the `remove()` method is called on a managed object, its state becomes "Removed".

## Callback Annotations

Depending on these states, we can apply certain business logic to control our entities depending on what state they're in or about to be in.

We can do this by creating methods annotated with:

- `@PrePersist`: the method will be called automatically before a `persist()` is excuted on an unmanaged entity.
- `@PostPersist`: will be called after the `persist()`.
- `@PreUpdate`: Will be called before any update happens to the managed entity.
- `@PostUpdate`
- `@PreRemove`
- `@PostRemove`
- `@PostLoad`: Will be called after a call to the `find()` method, or a JPQL select query.

These annotations allow us to apply certain business logic at different entity states, for example, we can have a `@Transient` age attribute in the entity, and a `calculateAge()` callback method that calculates the age depending on the dateOfBirth, and this method is annotated with `@PostLoad`, `@PostPersist` and `@PostUpdate@` so that it's called whenever an persist, load or update happens.

## Java EE and Bean Validation

In Java EE, we can use the Bean Validation Annotations in order to validate our entities at runtime before committing them to a database, with multiple annotations available for validations.

Example:
```
public class Artist {
    private Long id;

    @NotNull @Size(min=2, max=50)
    private String firstName;
}

public class ArtistService {
    @Inject
    private Validator validator;

    public void createArtist(Artist artist) {
        Set<ConstraintViolation<Artist>> violations
         = validator.validate(artist);
        if (violations.size() > 0) {
            throw new ConstraintViolationException(violations);
        }
    }
}
```
This way, an exception will be thrown if the firstName of Artist is null or it's size is not in the specified range.

<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)