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

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)