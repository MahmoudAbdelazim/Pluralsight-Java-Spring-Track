# Algorithms and Data Structuers - Part 2

# Sorting and Searching
Sorting items in an array can be done using more than one algorithm that vary in their time complexity and implementations.

## Bubble Sort
Bubble sort is a sorting algorithm that sorts data using this process:
- Iterate over the elements in the array from start to finish.
- If two neighboring items are out of order, swap them.
- Repeat this process until the array is sorted.

Bubble sort complexity: O(n^2) worst case, O(n^2) average case, O(n) best case.

## Insertion Sort
Insertion sort process:
- Iterate over the elements in the array from start to finish.
- If an item is out of order (smaller than the item on its left), then find the proper insertion point on the left and insert it there.

Insertion sort complexity: O(n^2) worst case, O(n^2) average case, O(n) best case.

Insertion sort and bubble sort work well when data is nearly sorted.

Insertion sort and bubble sort are in-place, they require O(1) additional space.

## Merge Sort
Merge sort is a recursive divide and conquor algorithm that has the following process:
- Recursively split the array into halfs until the array is split into single item subarrays.
- Compare indiviual items.
- Merge the items into a sorted array.

Merge sort complexity: O(n log n) worst, average and best case.

Merge sort requires O(n) additional space (not in-place).

Merge sort is a stable sorting algorithm.

## Quicksort
Quicksort is a divide and conquor algorithm that has the following process:
- Pick a pivot value in the array (can be random).
- Partition: Reorder the elements around this pivot point (less items to the left, more items to the right).
- Repeat the process for each partition.

Quicksort complexity: O(n^2) worst case, O(n log n) average case, O(n log n) best case.

Although the worst case is O(n^2), this rarely happens especially if the algorithm is well-implemented.

Quicksort is the default sorting algorithm for many programming languages.

Quicksort has great support for caching and parallel sorting.

<hr>

# Searching Algorithms
To search for an items in an array, there's more than one approach:

## Linear Search
Simply iterate over the array from start to end until you reach the element or reach end of the array.

Time complexity: O(n)

## Binary Search
Starting with a sorted array, check the middle value, if it's a match then return, if it's less than target, then ignore the right half, it it's more than target, then ignore the left half, keep doing this until you reach the target or end up with an empty array.

Time Complexity: O(log n)

Note that binary search requires the array to be sorted, if it's not then you'll have to sort it first, resulting in a complexity of O(n log n).

<hr>

# Balanced Binary Search Trees
A balanced binary search tree is a binary search tree that has the following property:
For each node, the height of the subtree to its left is nearly equal to the height of the subtree to its right.

A balanced BST ensures that addition, removal and retrieval of items take only O(log n) time (the height of the tree is always O(log n)).

Example of a Balanced BST: AVL Tree and Red-Black Tree.

<hr>

# B-Trees
A B-Tree is a sorted, balanced tree structure that is typically used to access data on slow mediums such as disks or tape drives.

A node in a B-Tree may have multiple values, not only one like in a BST, and also a node a number of childs equals number of values + 1.

Addition, removal and retrieval of items in a B-Tree takes O(log n) time.

<hr>

# Heaps and Priority Queues
A tree-based container type that provides O(1) access to
the minimum (min-heap) or maximum (max-heap) value
while satisfying the heap property.

## Heap Property
The value in the current tree node is greater than, or
equal to, its children (max-heap).

## Heap Data Structure
A heap can be implemented using in array, eliminating all structural overhead and providing O(1) accesss.

- Index of partent item: (idx - 1) / 2
- Index of left child: 2 * idx + 1
- Index of right child: 2 * idx + 2

A heap supports the following operations:
- Push: Add a value to the heap
- Top: Retrieves min or max
- Pop: Removes min or max

All these operations take O(1) time.

## Priority Queue
A queue that pops item in priority, not FIFO, order.
It can be implemented as a max-heap or min-heap, with the priority value to be the sorting value in the heap.

<hr>

# Concurrency Collections
What happens in a concurrent system?
- Multiple threads executing within a single process accessing a shared resource (e.g., a shared List<T>)
- Multiple processes running on the same computer accessing a shared resource (e.g., a shared file)
- Multiple processes running on different computers accessing a shared resource (e.g., a shared database table)

## Problem with concurrency:
The problem with concurrency is that when two threads or processes are accessing the same data structure, they may modify this data in some way that the other thread/process currently accessing the structure may not be able to see, resulting in data loss or corruption.

How to deal with concurrent access:
## 1- Caller Synchronization
The caller is responsible for ensuring all access to the
collection is performed in a concurrency-safe manner

Allows concurrent-safe access to non-concurrency-safe collections by using Lock objects.

The caller can determine the optimal synchronization approach

### Pros:
- Non-concurrent-safe collections can be
used in concurrent environments
- Easy to implement

### Cons:
- Caller is responsible for all thread
synchronization
- Readers block other readers
- Easy to implement wrong

## 2- Collection Synchronization
Synchronization techniques are done inside the collection itself using locks, ensuring safe concurrent access.

Every language, including Java provides some easy-to-use concurrent-safe Collections to work with in parrallel systems.

<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)