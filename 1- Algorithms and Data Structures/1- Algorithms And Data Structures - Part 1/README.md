# Algorithms and Data Structures - Part 1

## Arrays
An array is a collection that has the following:
- can contain multiple instances of a type
- Can be indexed by numbers
- Can access individual items (Random Access) in O(1) time
- Has fixed size once created
- Inserting and deleting elements costs O(n) time (because you'll have to reallocate the whole array in order to manipulate its size)

<hr>

## Linked Lists
A Linked list is a collection that consists of nodes and these each node links to another node (Node chain).

### Singly Linked Lists
A singly linked list provides forward iteration from the start to the end of the list, each node contains a link to the node next to it only.

### Doubly Linked Lists
A doubly linked list provides both forward and backward iteration, each node contains two links, one to the next node and one the previous node.

### Linked List Operations Runtime
In a doubly linked list:
- Adding a value at the head -> O(1)
- Adding a value at the tail -> O(1)
- Adding a value inside the list -> O(n)
- Finding a value -> O(n)
- Removing a head value -> O(1)
- Removing a tail value -> O(1)
- Removing a value inside the list -> O(n)

Linked lists can be useful when adding and deleting items from both ends of the list, and can also be useful when deleting items while traversing the list, as deletion of a node we currently hold only takes O(1) because it's just about manipulating the pointers.

<hr>

## Stacks
A last-in-first-out (LIFO) collection.

Supports three operations:
- Push: Adds an item to the top of the stack
- Pull: Removes the item at the top of the stack
- Peak: Retrieves the item at the top of the stack

All these operations take O(1) runtime.

## Queue
A first-in-first-out (FIFO) collection.

Supports three operations:
- Enqueue: Adds an item to the back of the queue
- Dequeue: Removes an item from the front of the queue
- Peak: Retrieves the item at the front of the queue

All these operations take O(1) runtime.

## Deque
A queue-like container which is both FIFO and LIFO.

It supports the following operations:
- EnqueueFront
- EnqueueBack
- DequeueFront
- DequeueBack
- PeakFront
- PeakBack

All these operations take O(1) runtime.

### Note
A stack, a queue and a deque can all be created with a linked list as the underlying data structure, because a linked list allows addition and removal of items at the head and tail in O(1) time.

<hr>

## Binary Search Trees
A tree is a data structure where nodes have a 1:N partent-child relationship.

A binary tree is a tree where each node has at most two children, left and right.

A binary search tree is a binary tree where nodes with lesser values are placed to the left of the root, and nodes with equal or greater values are placed to the right of the root, and this property holds for all subtrees inside the tree.

A binary search tree supports adding, removing and retrieving items from the tree.

### BST Runtime Complexity

What is the runtime complexity for these operations? 
It depends on the balancing of the tree.

A balanced binary search tree would cost O(log n) per operation, while a not-balanced binary search tree may take up to O(n) per operation, because a binary search tree can eventually be just like a linked list.


### BST Traversal
#### Pre-Order Traversal
In a pre-order traversal, each node is visited before its children.

#### In-Order Traversal
In an in-order traversal, the left child is visited before the node, then the right child.
It basically visits the elements in order from the smallest to the highest.

#### Post-Order Traversal
In a post-order traversal, the left child is visited first, then the right child, then the node itself.

### Traversal Complexity
Traversing a BST takes O(n) time.
