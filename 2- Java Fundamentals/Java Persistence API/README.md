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

# Relationships and Inheritance
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