# PrimsAlgorithm
Prim's Algorithm for Minimum Spanning Tree in Java
Graph Representation: The graph is represented using an adjacency list.

MinHeap Implementation:

Used to efficiently get the minimum weight edge at each step

Contains operations like extractMin, decreaseKey, and heapify

Prim's Algorithm Steps:

Initialize all keys as INFINITE and parent array as -1

Set key of root vertex (0) to 0

While minHeap is not empty:

Extract the vertex with minimum key value (u)

For each adjacent vertex (v) of u:

If v is in heap and weight of u-v is less than key[v], update key[v] and set parent[v] = u

Output: The program prints the edges of the MST along with their weights.
