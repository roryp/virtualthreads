# Virtual threads and the Traveling Salesman problem

The TravelingSalesman class uses virtual threads to calculate the shortest route for each starting city in parallel. The for loop on line 14 iterates over each starting city and creates a virtual thread to calculate the shortest route.
The demo creates virtual threads that are scheduled by the virtual machine instead of the operating system. This allows the virtual machine to optimize the scheduling of threads and reduce the overhead of context switching. Virtual threads are lightweight and can be created and destroyed quickly, making them ideal for tasks that require a large number of threads.

## Running the Example with the JDK 19+

1. Install jdk 19+
2. Open a terminal or command prompt and navigate to the directory containing the `TravelingSalesman.java` file.
3. Compile the source code by running the following command:

   ```java
   javac --enable-preview TravelingSalesman.java
   ```

   The `--enable-preview` flag is needed for Virtual Threads in Java 19 or 20.
   
4. Run the program by running the following command:

   ```java
   java --enable-preview TravelingSalesman
   ```

The program will calculate the shortest route for each starting city and print the total distance to the console.

## Running the Example in Visual Studio Code

To run the `TravelingSalesman` class in Visual Studio Code, follow these steps:

1. Install JDK 19+
2. Install the Java Extension Pack for Visual Studio Code.
3. Open the `TravelingSalesman.java` file in Visual Studio Code.
4. Press `F5` to open the Run panel.
5. Select `Java` as the environment and `TravelingSalesman` as the configuration.
6. Press `Enter` to run the configuration.

   This will run the `TravelingSalesman` class and output the total distance for each starting city in the Debug Console.

Note: If you are using virtual threads in Java 19 or 20, Make sure to enable virtual threads by adding the `--enable-preview` flag to the `java.compilerArgs` setting in the `.vscode/settings.json` file:

```json
{
    "java.compilerArgs": ["--enable-preview"]
}
```

Note: Make sure to restart Visual Studio Code after making this change for it to take effect.

## Details of the Traveling Salesman problem

The Traveling Salesman Problem is a classic optimization problem in computer science. The problem is defined as follows: given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the starting city?

The brute force solution to this problem involves calculating the distance of every possible route and selecting the shortest one. However, this approach quickly becomes infeasible as the number of cities increases, since the number of possible routes grows exponentially.

One common algorithm for solving the Traveling Salesman Problem is the Nearest Neighbor algorithm. This algorithm starts at a random city and repeatedly visits the nearest unvisited city until all cities have been visited. This approach is simple and fast, but it does not always produce the optimal solution.

Another algorithm for solving the Traveling Salesman Problem is the 2-Opt algorithm. This algorithm starts with an initial route and repeatedly swaps pairs of edges to try to find a shorter route. This approach can be effective, but it can also get stuck in local minima and fail to find the optimal solution.

A more advanced algorithm for solving the Traveling Salesman Problem is the Lin-Kernighan algorithm. This algorithm uses a combination of 2-Opt moves and other techniques to iteratively improve the current solution. The Lin-Kernighan algorithm can find high-quality solutions for large instances of the problem, but it can also be computationally expensive.

There are many other algorithms for solving the Traveling Salesman Problem, each with its own strengths and weaknesses. The choice of algorithm depends on the specific problem instance and the desired trade-off between solution quality and computational efficiency.
