# Java: Writing Readable and Maintainable Code

<i>"Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live"</i> - John Woods

<hr>

# 1. Naming Matters.
Naming is one of the most important aspects of coding.

## Classes
A class name should be:
- Noun: Concrete or Abstract
- Specific: has a specific meaning

Having a class name that is not specific in itself breaks the SRP (Single Responsibility Principle).

Avoid class names that end with Coordinator or Manager, such as FlightManager or StoreManager as they're too vague, instead,  use some alternatives with more scope such as:
- Builder
- Writer
- Reader
- Handler
- Container

## Variables
Variable Naming Convenctions:
- Never a single letter
- Always specific
- Ideally 1-2 words
- booleans prefixed with "is"
- use camelCase
- use ALL_CAPS for constants

## Methods
Method Naming Convenctions:
- Should reveal intent
- Functionality is fully understandable from the name

<i>"If you have to look inside the method to understand what it does, the name probably needs improvement."</i>

Method name is usually a verb followed by a noun, such as setPrice, convertCurrency, etc.

### Method name anti-patterns
Avoid these situations:
- Method does more than the name says
- Name contains "and", "or" or "if", etc.

## Abbreviations and Spelling
Don't use abbreviations, unless their meaning is really clear, Also be careful with spelling and typos.

<hr>

# 2- Better Constructors
## Static Factory Methods
Consider using static factory methods instead of constructors with a long list of arguments

Static factory methods encapsulate the construction logic, hiding the complexity of the constructors.

## Constructor Chaining
If you need to create multiple constructors for your class, instead of creating different ones and duplicating code in them (breaking the DRY principle), use chaining instead.

chaining means that each constructor instead of implementing its logic itself, calls another constructor with the arguments it received, and that constructor calls another until the constructor that has the implemented logic with the full list of arguments is called.

Example: `BankAccount()` calls `this(0)` --> `BankAccount(double balance)` calls `this(balance, 0.01)` --> `BankAccount(double balance, double interest)` is finally called.

## The Builder Pattern
If the constructor contains a long list of parameters (that may get even longer in the future), this is called the Constructor Telescoping Anti-Pattern, and can be solved using the Builder Pattern, that uses a chain of method calls in order to set the needed parameters.

[Builder Pattern - Refactoring.guru](https://refactoring.guru/design-patterns/builder)

<hr>

# 3- Implementing Methods

## Clean Code Concepts
- DRY vs WET
- Cyclomatic Complexity
- Signal vs Noise

## What (not) to return
Try to avoid returning:
- Null: Because null inceases the complexity by having to check if the return value is not null whenever we call the method.
- Special codes (-1, 0, 1, etc): Because it's vague, it's noise not signal, instead consider throwing an exception if you want to report an error.

## Mehtod Parameters
Generally, fewer method arguments is better, try to have 0-2 or maximum 3 arguments, if you have more, refactor.

## Flag Arguments
Avoid having flag boolean parameters, they usually indicate that code inside the method does more than one thing depending on this flag, instead, split the method into one or more method and remove those flag arguments.

## Fail Fast
Immediately report any failure and let the method fail, because this leads to faster debugging and troubleshooting.

Example: throw an `IllegalArgumentException` at the beginning of the method of an argument is not valid (like checking for zero before division).

## Return Early
Check for simple conditions and return immediately, leading to better readability and less lines of code.

## Refactor Duplication
Whenever you have duplicate code inside or across your methods, consider separating this duplicate code in a separate method, and call this method instead, following the DRY principle.

## Conditionals
Instead of creating an if statement that checks for a boolean value like: `if (!doorClosed == false)`, you should avoid this and just check for the boolean itself: `if (isDoorOpen)`.

Also, if you have a complex conditional statement, you can wrap it inside a method and give this method a proper name and call it instead, in order to make it more readable and understandable.

## Ternary Expressions
Ternary operators can be useful, but don't nest them, as they become more complicated.

<hr>

# 4- Handling Exceptions
## Catch Specific Exceptions
Don't catch any exceptions, like Throwable and RuntimeException, instead handle CheckedExceptions.
Prevent unchecked exceptions instead of handling them in code.

## Catch Block
Catch blocks shouldn't:
- Be empty
- Have only comments
- Contain unhelpful code
- return null

catch block should, for example, log the exception to a logging framework, or rethrow it and pass useful information.

## Finally Block
No exception should be thrown in a finally block.

Finally block is meant for cleanup, so avoid calling a method that also might throw an exception inside the finally block.

Try-with-resource is a preferred way to handle cleanup starting from Java 7.

<hr>

# 5- Class Organization

## Single Responsibility Principle (SRP)
A class should have only one reason to change.

Each class should only one specific role that it can handle correctly, a class that has many roles should be broken down to multiple classes instead.

SRP leads to higher cohesion.

## Cohesion
Cohesion means that things are logically connected together.

Cohesion refers to the degree to which  the elements inside a class or a module belong together

High cohesion means that the class is focused on what it should be doing, fields and methods are co-dependent and form a logical whole.

SRP != Cohesion, a class can be highly cohesive and also have multiple responsiblities.

## Coupling
Coupling is the degree of interdependence between software classes or modules, a measure of how interconnected they are, or how much a component knows about the details of another.

We should always aim for loose coupling and high cohesion.

Loose coupling means that changing something in one class requires minimal change in other classes, while tight coupling means the opposite.

### To redue coupling:
- Adhere to SRP
- Increase Cohesion
- Program to an Interface
- Maintain Strong Encapsulation (Keep things private as possible)
- Use Dependency Injection

## Principle of Proximity
The proximity rule says that your code should be easy to read from top to bottom, just like a story or a book.

Placing interconnected methods in the right order increases readability of the code.

<hr>

# 6- Writing Comments
Comments are in most cases more evil than good, they're usually a patch for poorly-written code.

## Useless Comments
Comments that don't add any information, just stating the obvious, should be avoided.

## TODOs
A TODO comment is a comment that lets others know that this functionality will be (but not yet) implemented or updated.

TODOs shouldn't be just // TODO or // TODO: fix this

They should express what to do? with what? by whom? by when? just writing TODO by itself is a bad comment.

## Misleading Comments
Comments often lie, like a comment that says the method returns String while it's updated to return another type.

## Commented-Out Code
DON'T COMMENT OUT CODE.

Commented-Out-Code usually stays there for a long time that you won't know whether to delete it, uncomment it or just leave it as is, avoid it in the first place.

## Useful Comments
Good comments can be:
- JavaDoc
- Compensate for factors outside your control (like using 3rd party software).
- TODOs with an explainer or issue tracker number

# 7- Improving Tests

## DAMP Tests
In tests, the most important principle is not DRY or WET, it's something in between called DAMP (Descriptive and Meaningful Phrases).

You should strive for maximum readability in your tests, even if it means some duplication in your test code.

Duplication in tests is more acceptable than production code, because you may write multiple tests that test the same functionality, and you don't want your tests to be complex because that will require you to write test code for your test code.

## Keep Tests Focused
Test one thing at a time (Apply SRP to tests).
- Verify one thing per test
- 1 assertion per test (some exceptions exist)
- No "if" statements

## Use a Test Template
Such as AAA (Arrange, Act, Assert) or BDD (Given, When, Then).


<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)