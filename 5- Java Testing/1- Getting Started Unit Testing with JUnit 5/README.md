# Getting Started Unit Testing with JUnit 5
Demo Application: [Patient Intake System](https://github.com/MahmoudAbdelazim/Pluralsight-Java-Spring-Track/tree/main/5-%20Java%20Testing/1-%20Getting%20Started%20Unit%20Testing%20with%20JUnit%205/Patient%20Intake%20System/patient-intake).
<hr>

Test is just code that excutes target code.

Unit testing tests a single unit of code at a time, this unit can be a method, a function or a class.

Benefits of unit tests:
- Quick feedback on whether the unit of code behaves as expected.
- Serve as automated regression checking for the entire system.
- Helps with Design, if calling a target unit from inside a unit test is complex, it might be confusing for client code that actually uses this code.
- Improves confidence.
- Acts as a form of documentation, a unit test can explain what a certain method does and how it is used.

## Writing Tests

Unit testing consists simply of these steps:
1. Setup (Create the required resources to test).
2. Excute (Excute the method or unit of code to be tested).
3. Verify (Assert the result of the execution using any of the `assert` methods).

JUnit lifecylce method annotations:
1. `@BeforeAll`: runs before all class methods (sets up whole class resources for testing).
2. `@BeforeEach`: runs before each test method.
3. `@Test`: contains the tests themselves using assertion methods.
4. `@AfterEach`: used to tear down resources after each test method.
5. `@AfterAll`: tears down all test class resources 

## Making Existing Code Testable
There are two concerns that affect the testability of our code:

### 1- Mixed Concerns
Accoring to the SRP, a unit of code should have only one reason to change and should have high cohesion.

Mixed concerns make code harder to test because of many possible reasons:
- Inaccessible system output
- Inaccessible inputs
- Undesirable side effects

The solution is often Extraction, separate concerns by moving code around, to new methods, classes and functions.

The IDE can help in code extraction.

### 2- Problematic Dependencies
Code usually calls other code and rarely works in isolation.

Testing code that calls other code can be difficult, because it may for example:
- Have side effects
- Talk to external services that are not always present (like a database)
- Cause inconsistent behavior.

The common solution is to create a Test Double, an alternative to the dependency but doesn't have these side effects or external dependencies of the real dependency, but looks like the real dependency to the target code.

This is a sort of the concept of Dependency Injection, we inject the target code with the dependency istead of the target code instantiating the dependency itself.

Creating a Test Double can be easier using a mock library, like Mockito and EasyMock.

## Writing Tests First

### Test-Driven Development (TDD)
TDD is about writing tests first, before target code is written, it's about deriving development of the feature from a test.

Works by frequent switching from test to production code and back.

Benefits of TDD:
- Keeps you focused
- Produces testable designs
- Helps produce clear interfaces
- Helps produce clean code

Steps:
1. Write just enough test code to fail
2. Write just enough production code to pass the test and no more.
3. Refactor what was just written.




<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)