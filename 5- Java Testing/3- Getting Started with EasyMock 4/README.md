# Getting Started with EasyMock

Demo Application: [Pension Ready](https://github.com/MahmoudAbdelazim/Pluralsight-Java-Spring-Track/tree/main/5-%20Java%20Testing/3-%20Getting%20Started%20with%20EasyMock%204/pension-ready).

<hr>
When writing unit tests, mocking can be very helpful, if not essential.

Dependencies in code make it hard to unit test code, because it's hard to separate the unit we're trying to test from the rest of the system, and that's where mocking is useful.

## Mocking
One of the most popular scenarios for using mock objects is database, because in almost all cases, it's not a good idea to use an actual database inside our unit tests.

Also another possible scenario is when our code uses external web services/objects, where mock objects enable us to completely remove the network usage from our tests.

### Mocking Benefits
- Eliminates non-determinism and randomness
- Reduces complexity and increases flexibility
- Improves tests speed and reliability.
- Supports collaboration, even if a dependency is not yet ready.

## Test Doubles
A double is a generic term for any case where you replace a production object for testing purposes.

Test Doubles have multiple types:
### Dummy
- Not used in test (not important in the testing context)
- Just fills in parameters
- Allows us to focus on the important aspect of the test

### Fake
- Implementation that is only suitable for tests, not for production
- Ex: In-memory database, fake web service

### Stub
- An object that provides "Canned" answers, or pre-programmed answers.
- Multiple variations are possible and configurable.

### Spy
- A more intelligent Stub
- Keeps notes of how it was called
- Thus helps with verification

### Mock
- Configured with Expectations
- Can fail the tests if unexpected calls are made
- Focuses on <b>behavior</b> verification, not just state assertions like stubs. 

### Mocking at class and interface level
EasyMock can mock objects at the concrete class level or interface level, but it's better to work at interface level because that adheres to the object oriented principles.

## The stages of using Mocks in EasyMock
1. Create the mock objects (either using `@Mock` annotation or using the `mock()` method).
2. Record mocks: Register expected behavior (expected method calls), using the `expect()` method followed by `andReturn()` or `andThrow()` for exceptions.
3. Replay mocks: check that the expected 'recordings' actually happened during test execution, using the `replay()` method.
4. Asserting
5. Verifying: using the `verify()` method, to make sure that the expected methods were called and called correctly.

## Mock Types in EasyMock
1. Nice Mock: Created with `niceMock()` method, which expects recorded calls in any order and returns default empty values for unexpected calls.
2. Default Mocks: The are Strick Mocks created with `mock()` method, which fails if a call is not explicitly expected, but doesn't fail if any recorded calls are skipped or in different order.
3. Strick Mocks: Created with `strictMock()` method, which Expects only recorded calls and in the same order they have been recorded.

## Argument Matchers
Argument Matchers allow for increased flexibility for configuring mock expectations.

There are multiple types of Argument Matchers:
- equals --> `eq()` : The default argument matcher in EasyMock, which matches objects based on the `equals()` method of the object.
- same --> `same()` : Compares the objects in terms of their addresses in memory, confirming that they're the exact same object reference.
- any --> `anyString()`: Matches any object of this type
- is A --> `isA()` : matches with any object of this class
- is null --> `isNull()`

## State vs Behavior Verification

### State Testing
- Verifies that the unit-under-test returns the correct result.
- We examine the state of the unit-under-test once the funcionality has been exercised.
- Phases are: Setup (unit-under-test), Exercise, Verify, Teardown.

### Behavior Testing
- Verifies that the unit-under-test calls certain methods correctly.
- The checks are carried out at verification stage, often used in combination with state testing.
- Mocks always uses behavior-based testing
- Phases are: Setup (Mocks, expectations, and unit-under-test), Exercise, Verify, Teardown.

## Verification

### Verifying Expectations
The `verify()` method is used to verify that the expected methods were called, and called correctly, and with the right number of calls. It's usually written at the end of the test.

To make sure that a method is called, for example two times, then after the `andReturn()` method call `.times(2)`.

To make a method be called 0 or more number of times, then call `.anyTimes()`.

### Verifying the Unexpected
To verify that no unexpected methods were called on the mock objects.

In Strict Mocks, this is automatically provided, but not for Nice Mocks.

It's usually a bad practice to specify unexpected behaviors explicitly, but we can do this with the `.andAnswer()` method, which accepts a lambda expression that calls `fail()` and returns `null`. But we shouldn't be doing this.

### Method Stubbing without verification
To exclude a method in a mock object from verification, instead of calling `.andReturn()`, call `.andStubReturn()` or `.andStubThrow()`, which will not verify the method against test excusion.





<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)