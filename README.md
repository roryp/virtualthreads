![Traveling Salesmzn](https://github.com/roryp/virtualthreads/blob/main/salesman.jpg?raw=true)

# Traveling Salesman problem with Java Virtual Threads

The Traveling Salesman Problem is a classic optimization problem in computer science. The problem is defined as follows: given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the starting city?

Virtual threads are a new feature that allow for lightweight concurrency without the overhead of context switching, making them ideal for tasks that require a large number of threads. This implementation uses the brute-force algorithm to calculate the shortest route for each starting city and prints the total distance to the console.

## Running the Example with the JDK 20

1. Install JDK 20
2. Open a terminal or command prompt and navigate to the directory containing the `TravelingSalesman.java` file.
3. Compile the source code by running the following command:

   ```java
   javac --enable-preview -source 20 TravelingSalesman.java
   ```

   Note: The `--enable-preview` flag is only needed for Virtual Threads in JDK 20.
   
4. Run the program by running the following command:

   ```java
   java --enable-preview TravelingSalesman
   ```

The program will calculate the shortest route for each starting city and print the total distance to the console.

## Running the Example in Visual Studio Code
To run the `TravelingSalesman` class in Visual Studio Code, follow these steps:

1. Install JDK 20
2. Install the Java Extension Pack for Visual Studio Code.
3. Open the `TravelingSalesman.java` file in Visual Studio Code.
4. Press `F5` to open the Run panel.
5. Select `Java` as the environment and `TravelingSalesman` as the configuration.
6. Press `Enter` to run the configuration.

   This will run the `TravelingSalesman` class and output the total distance for each starting city in the Debug Console.

Note: Make sure to restart Visual Studio Code after making this change for it to take effect.

## Details of the Traveling Salesman problem

The brute force solution to this problem involves calculating the distance of every possible route and selecting the shortest one. However, this approach quickly becomes infeasible as the number of cities increases, since the number of possible routes grows exponentially.

There are many other algorithms for solving the Traveling Salesman Problem, each with its own strengths and weaknesses. The choice of algorithm depends on the specific problem instance and the desired trade-off between solution quality and computational efficiency.
