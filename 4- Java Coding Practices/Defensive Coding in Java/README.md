# Defensive Coding in Java
Defensive Programming is an approach to improve software and source code in terms of:
- <b>General quality</b> - Reducing bugs and problems
- Making code comprehensive - <b>readable</b> and understandable
- Making software behave in a <b>predictable</b> manner, despite <b>unexpected</b> inputs or user actions.

<i>The whole point of defensive programming is guarding against errors you don't expect.</i>

Defensive Coding is all about:
1. Reacting Early
2. Preventing

<hr>

# 1- Validating Method Input

## Fail Fast with Guard Clauses
Errors should be detected as soon as possible.

In case that the method receives an invalid input (The Guard Clause), there are three options:
- Return Early: return false, or just return if it's void
- Fail Fast: throw an appropriate exception
- Alternative Execution: Display a user-friendly message of what went wrong

Always place Guard Clauses at the very beginning of the method.

## Class Invariant
A class invariant is a property that always remains true for all instances of a class no matter what happens.

Having many gaurd clauses at the start of the method is not good, sometimes the defensive code gets even bigger than the actual code inside the method.

The idea of class invariants is to capture the input parameters inside a Request object, and this Request's constructor checks for the appropriate validations, and this Request object, if valid, is passed to the method, guaranteeing that the inputs passed to the method are valid and the method can now contain only business logic and way fewer validation logic.

This way, we're <i> preventing </i> errors rather than <i> reacting.</i>

## Choosing the Right Exceptions
Don't
- Throw Error, Exception, RuntimeException or Throwable

Do
- Throw specific Exceptions such as:
- IllegalArgumentException
- IllegalStateException
- NullPointerException
- UnsupportedOperationException

## Use Enums
Use enums instead of Strings when appropriate, you can easily type a mistake in a String while that can't happen in an Enum.

<hr>

# 2- Using Frameworks for Validation

## The native Objects API
The java `Objects` class contains utility methods for checking and validation.

For example: Checking for null:
```
Objects.requireNonNull(input);
```
This method will check if input is null, and if it is, it will throw a `NullPointerException`.

There are many other useful methods in Objects, including `equals()`, `deepEquals()`, `requireNonNullElseGet()`, `checkIndex()`.

## Google Guava
Guava is a library that facilitates best practices in code and helps reduce errors.

Guava has a class that is similar to Objects called `Preconditions`, that contains many static utility methods, including `checkNotNull()`, `checkArgument()`, `checkState()`.

Guava is a big library for many functionalities, it's not just for validation.

## Apache Commons
Has validations similar to Guava.

## Test Libraries: Hamcrest and AsserJ
These are testing libraries that are used for Unit testing, just like JUnit, but they're relatively easier and more readable.

These libraries are competing, not complementary.

# 3- Improving Method Return Values
<i>The user of a component should never be surprised by its behavior. </i>

## Magic Numbers
DON'T return magic numbers, they're vague and require the person using the method to read the documentation to know the meaning of return values.

## Valid Return Values
- A method can return boolean, true endicates success and false endicates failure.
- A method can be void, nothing means success and Exception means failure.

Don't:
- Mix these options
- Return null

## Alternatives to Null
- Throw an exception
- Return a sensible default value
- Return an empty collection
- Return an `Optional<T>` object

## Java Optional
Optional is a Java object that can hold at most one value, either one or empty.

Optional forces the user to confront the fact that there may be no value returned.

<hr>

# 4- Other Defensive Practices

## Better Encapsulation
Make all class members as inaccessible as possible, always strive for Better Encapsulation. 

Don't automatically create public getters and setters when you don't have to.

Less exposed code means less hassle.

## Method Side Effects
Methods can return values, or can also have side effects changing a state in the system, such as saving to the database.

The rule is, don't create methods that return values AND produce side effects. (Command-Query Separation CQS).

There are exceptions to the CQS rule:
- Logging
- Intentional design decision (like stack's pop() method)

## Improve Exception Handling
Do:
- Use Java 7 try-with-resources
- pass useful information to the exceptions

Don't:
- catch top-level Throwable or Exception
- catch NullPointerException
- catch and swallow the exception (do nothing in the catch block)

## Use Static Analysis Tools
such as SonarLint plugin in the IDE


<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)