# Java Refactoing - Best Practices
Refactoring is about improving the quality of existing code without changing the way it behaves.

Refactoring means <i>"A change made to the internal structure of software to make it easier to understand and cheaper to modify without changing its observable behavior"</i>.

Refactoring should be done continuously.

Boy Scout Rule: <i> "Leave the campground cleaner than you found it" </i>.

## Refactoring Process:
1. Verify existing behavior
2. Ensure tests are valid and passing
3. Refactor
4. Verify behavior again

<hr>

# Bloaters
Bloaters are very large methods or classes that are hard to work with, they accumulate over time as software evoloves.

A method should not exceed 20 lines of code.
A class is measured by responsibility, it should have only one responsibility (RSP).

## 1- Long Parameter List
A method that has 4 or more parameters needs refactoring.

This smell indicates the presence of other smells, such as: Long Method, Primitive Obsession, and Data Clumps. Refactoring these smells will almost always fix the Long Parameter List smell.

## 2- Long Method
A method that has <= 10 lines of code is good, up to 20 is OK, more than that, it probably needs refactoring.

There are two methods to fix Long Method:
### Extract Method
Split the method into several smaller methods.

### Decompose Conditional
If we have an if statement that is hard to read or is too complex, the condition can be moved to another method that may start with "is", this is another way of solving Long Method and also makes code more readable.

## 3- Contrived Complexity
Code that achieves an objective but is too complex. An easier, shorter or more elegant way exists to achieve the same goal.

## 4- Primitive Obession
Use of primitives too much instead of objects.

There are two ways to solve this:
### Preserve The Whole Object
Just pass in the whole object as a parameter instead of passing the attributes inside it.
### Introduce Parameter Object
Create an object (create a class we don't already have), move the parameters there, and pass in the object.

Also, Strings should be converted into Enums, or even classes if they should have other parameters related to them.

## 5- Data Clumps
A group of variables (can be objects) which are passed around together throughout various parts of the program.

The solution is the same as with primitive obsession, these variables should be encapsulated into an object and pass this object around instead.

Another solution is to combine an object inside another (Combine Entities), if they are needed together a lot throughout the code. 

## 6- Large Class
A large class is a class thas has many responsibilities (does many things).

Large class should be split into several smaller classes, and have dependencies between these classes.

<hr>

# Object-Oriented Abusers
Code that doesn't follow OOP principles.

## 1- Conditional Complexity
Complex switch operator or complex sequence of if-else statements.

It often means:
- Missing domain objects
- Not using polymorphism
- Not using inheritance

It also breaks the open/closed principle as it needs to change too often.

To solve conditional complexity:

### Replace with Polymorphism
Create an abstract class or an interface in make classes that implement this interface in their own way, and let the runtime decide which method to run by polymorphism.

This also allows the extension of more code into out logic without changing existing code, adhering to the open/closed principle.

### Replace with Delegation (Strategy Design Pattern)
another solution is to implement the strategy design pattern which greatly reduced conditional complexity.

## 2- Bad Bequest
Bequest is the act of giving or leaving personal property by a will.

Refused Bequest is when a subclass inherits fields and methods it doesn't need.

This also breaks the Liskov's substitution principle.

To solve this smell, there are multiple strategies:

### Rename
rename the method in the parent class or interface to a more general way, but this is not always a valid solution.

### Push Down
Move the method or field down to the subclasses that implement it, introduce a new subclass in the hierarchy if necessary.

### Restructure Inheritance Tree
maybe the design of the inheritance tree all needs to be changed, we should always favor composition over inheritance when we can.

## 3- Temporary Fields
Fields that have values only under certain circumstances and needed by only certain methods, they are empty the rest of the time (null).

This indicates low cohesion in the class.

Two ways to solve this:

### Extract Class
Move these fields into a separate class

### Replace Method with Method Object
Move the method implementatino to a new separate class that only has this method (An Algorithm Object).

## 4- Two or more Classes with Different Interfaces
Two or more methods exist across multiple classes that do the same thing, recurring in code duplication.

It can be considered also as inheritance misuse.

A solution would be to create an abstract class or an interface that these classes implement

Another is to combine, change until both methods are identical and leave just one.

<hr>

# Change Preventers
When code change in one place forces you to change code in many other places.

## 1- Divergent Change
Changing several unrelated things within the same class.

The solution is the either extract method (split) or extract class.

## 2- Solution Sprawl
A solution is broken into multiple classes or places.

## 3- Shotgun Surgery
An update requires additional changes in muliple classes or modules, this is a result of Solution Sprawl, they can be used interchangeably.

The solution is usually to combine into one, change until you have a class with a single responsibility that encapsulates related changes.

## 4- Parallel Inheritance Hierarchies
You create a subclass in one inhertance tree, this forces you to create another subclass in another tree. This is a special case of shotgun surgery.

One solution is to merge the hierarchies, moving methods from one of the trees to the other, and them removing it.

Consider applying the Visitor Pattern or the Bridge Pattern.

<hr>

# Couplers
Couplers are code smells that result in tightly coupled classes.

Usually preventing couplers is done through improving encapsulation and applying SRP.

## 1- Feature Envy
 Class uses methods or accesses data of another class more than its own.

 A solution would be either to move methods that should belong together to the same class, or encapsulate the functionality in a single call.

 ## 2- Inappropriate Intimcay
 A class knows too much, or has to deal with, internal details of another class.

 Always try to hide as much as possible, mark fields and methods private until they're publicly needed.

 ## 3- Excessive Exposure
 A class or a module exposes too many internal details about itself.
It's when a class forces you to know or care too much about its internal details and state in order to be able to deal with it.

There is more than one solution, one is to code to make this class implement an interface that forces it to be minimal, another is to create simpler method signatures that internally do the hard work instead of the client having to deal with much details.

## 4- Message Chain
Code that calls mutliple methods to get to the required data.

Ex: `customer.getAddress().getCountry().toString();`

A possible solution would be to move the call chain to a new method `getCountry()` inside the Customer class.

## 5- Middle Man
A class that performs one action, which is delegating work to other classes. (can be a dispensable code smell also)

Delegation is fine, but if the class only delegates and does nothing else, it's usually not.

The solution is to remove the middle man, and make the client deal with the actuall classes that do the work, and refactor them in some way.

Note that there are design patterns that use the middle man, such as Facade, Proxy and Adapter pattern.

<hr>

# Dispensables
Dispensables are code that is not needed and can be removed. Their solution is usually just remove themm.

## 1- Comments
Misused or misplaced comments.

Comments shouldn't compensate for bad code. Avoid redundant, wiki, misleading comments or commented-out code.

## 2- Dead Code
Unused code that will never run.

## 3- Duplicate Code
Same code written multiple times in several places.

DRY

## 4- Speculative Generality
Code that is created "just in case".

Like public setters and getters that will pobably not be used or needed.

YAGNI: You Ain't Gonna Need It

## 5- Lazy Class and Data Class
A class that doesn't do enough to have a reason to exist, like the Middle Man described before.

Solution is either to remove it or add more functionality to it (give it responsibility).

Data Classes are debatable whether they're a bad smell or not, depending on how they're used.

<hr>

# Refactoring Tips & Principles
- Understand your code well before refactoring.
- Create or run existing tests.
- Keep changes small, and commit often.
- Manage the scope of your refactoring.
- Seek help and advice.
- Use code review.



<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)