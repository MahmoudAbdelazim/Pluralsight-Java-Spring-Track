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