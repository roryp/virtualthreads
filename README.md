![Virtual Threads](https://github.com/roryp/virtualthreads/blob/main/salesman.jpg?raw=true)

## Java Virtual Threads

Virtual threads are a new feature that allow for lightweight concurrency without the overhead of context switching, making them ideal for tasks that require a large number of threads. This repository contains two examples - a basic and complex Virtual thread example.

## Running the Examples in Visual Studio Code
To run the examples in Visual Studio Code, follow these steps:

1. Install JDK 20
2. Install the Java Extension Pack for Visual Studio Code.
3. Open the example file in Visual Studio Code.
4. Above the main method for example, you will have the option to run/debug the class.
5. Debug the class and you will see the output in the debug console.

## Basic Example

The [BasicVirtualThread](https://github.com/roryp/virtualthreads/blob/main/src/BasicVirtualThread.java) demonstrates how to use virtual threads in Java to perform a large number of tasks concurrently. The class creates a new virtual thread executor and submits 20,000 virtual threads to it. Each virtual thread prints a message to the console. This example is meant to showcase the use of virtual threads in Java and how they can be used to improve concurrency performance.

## Traveling Salesman problem

In the [TravelingSalesman](https://github.com/roryp/virtualthreads/blob/main/src/TravelingSalesman.java), the program solves the Traveling Salesman Problem (TSP) using a brute-force approach. The TSP is a problem in which a salesman must visit a set of cities exactly once and return to the starting city, with the goal of minimizing the total distance traveled.

The program defines a map of distances between cities, creates a list of all cities, and then calculates the total distance and route for each starting city in parallel using an executor. The program then prints the total distance and route for each starting city, and finds the best city to start based on the shortest total distance. Finally, the program prints the best city to start.

There are many other algorithms for solving the Traveling Salesman Problem, each with its own strengths and weaknesses. The choice of algorithm depends on the specific problem instance and the desired trade-off between solution quality and computational efficiency.
