import java.util.*;
import java.util.concurrent.*;

public class TravelingSalesman {

    public static void main(String[] args) throws InterruptedException {
        // Define the distances between cities as a Map
        Map<String, Map<String, Integer>> cities = Map.of(
                "San Francisco", Map.of("Los Angeles", 347, "Denver", 950, "New York", 2572, "Johannesburg", 16983, "Paris", 5583),
                "Los Angeles", Map.of("San Francisco", 347, "Denver", 830, "New York", 2445, "Johannesburg", 16713, "Paris", 5669),
                "Denver", Map.of("San Francisco", 950, "Los Angeles", 830, "New York", 1631, "Johannesburg", 15388, "Paris", 4880),
                "New York", Map.of("San Francisco", 2572, "Los Angeles", 2445, "Denver", 1631, "Johannesburg", 7969, "Paris", 3635),
                "Johannesburg", Map.of("San Francisco", 16983, "Los Angeles", 16713, "Denver", 15388, "New York", 7969, "Paris", 5410),
                "Paris", Map.of("San Francisco", 5583, "Los Angeles", 5669, "Denver", 4880, "New York", 3635, "Johannesburg", 5410)
        );

        // Create a list of all cities
        List<String> allCities = new ArrayList<>(cities.keySet());

        // Create maps to store the total distances and routes for each starting city
        Map<String, Integer> totalDistances = new ConcurrentHashMap<>();
        Map<String, List<String>> routes = new ConcurrentHashMap<>();

        // Create a random number generator
        Random random = new Random();

        // Create an executor to run the distance calculations in parallel
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (String startCity : allCities) {
            executor.submit(() -> {
                // Create a list of all cities except the starting city
                List<String> otherCities = new ArrayList<>(allCities);
                otherCities.remove(startCity);

                // Calculate the distance and route for the starting city
                List<String> route = calculateDistance(startCity, otherCities, cities, random);
                int totalDistance = calculateTotalDistance(route, cities);

                // Store the distance and route for the starting city
                totalDistances.put(startCity, totalDistance);
                routes.put(startCity, route);
            });
        }

        // Shut down the executor and wait for all tasks to complete
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        // Print the total distance and route for each starting city
        for (String city : allCities) {
            System.out.printf("Starting at %s, total distance: %s, route: %s\n",
                    city, formatDistance(totalDistances.get(city)), routes.get(city));
        }

        // Find the best city to start based on the shortest total distance
        Map.Entry<String, Integer> bestCity = totalDistances.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow();

        // Print the best city to start
        System.out.printf("\nBest city to start is %s with a total distance of %s miles\n",
                bestCity.getKey(), formatDistance(bestCity.getValue()));
    }

    // Calculate the distance and route for a starting city
    private static List<String> calculateDistance(String startCity, List<String> otherCities,
            Map<String, Map<String, Integer>> cities, Random random) {
        List<String> route = new ArrayList<>();
        String currentCity = startCity;

        // Visit each city in a random order
        while (!otherCities.isEmpty()) {
            String nextCity = findRandomCity(otherCities, random);
            route.add(currentCity);
            currentCity = nextCity;
            otherCities.remove(currentCity);
        }

        // Return to the start city to complete the route
        route.add(currentCity);
        route.add(startCity);
        return route;
    }

    // Calculate the total distance for a route
    private static int calculateTotalDistance(List<String> route, Map<String, Map<String, Integer>> cities) {
        int totalDistance = 0;

        // Add up the distances between each pair of cities in the route
        for (int i = 0; i < route.size() - 1; i++) {
            String currentCity = route.get(i);
            String nextCity = route.get(i + 1);
            totalDistance += cities.get(currentCity).get(nextCity);
        }

        return totalDistance;
    }

    // Select a random city from a list of cities
    private static String findRandomCity(List<String> cities, Random random) {
        int randomIndex = random.nextInt(cities.size());
        return cities.get(randomIndex);
    }

    // Format a distance as a string
    private static String formatDistance(Integer distance) {
        return distance != null ? distance.toString() : "null";
    }

}