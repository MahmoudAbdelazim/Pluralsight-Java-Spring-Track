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

## Divergent Change
Changing several unrelated things within the same class.

The solution is the either extract method (split) or extract class.

## Solution Sprawl
A solution is broken into multiple classes or places.

## Shotgun Surgery
An update requires additional changes in muliple classes or modules, this is a result of Solution Sprawl, they can be used interchangeably.

The solution is usually to combine into one, change until you have a class with a single responsibility that encapsulates related changes.

## Parallel Inheritance Hierarchies
You create a subclass in one inhertance tree, this forces you to create another subclass in another tree. This is a special case of shotgun surgery.

One solution is to merge the hierarchies, moving methods from one of the trees to the other, and them removing it.

Consider applying the Visitor Pattern or the Bridge Pattern.






<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)