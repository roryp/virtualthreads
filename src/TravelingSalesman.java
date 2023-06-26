import java.util.*;
import java.util.concurrent.*;

public class TravelingSalesman {

    public static void main(String[] args) throws InterruptedException {
        Map<String, Map<String, Integer>> cities = Map.of(
                "San Francisco", Map.of("Los Angeles", 347, "Denver", 950, "New York", 2572, "Johannesburg", 16983, "Paris", 5583),
                "Los Angeles", Map.of("San Francisco", 347, "Denver", 830, "New York", 2445, "Johannesburg", 16713, "Paris", 5669),
                "Denver", Map.of("San Francisco", 950, "Los Angeles", 830, "New York", 1631, "Johannesburg", 15388, "Paris", 4880),
                "New York", Map.of("San Francisco", 2572, "Los Angeles", 2445, "Denver", 1631, "Johannesburg", 7969, "Paris", 3635),
                "Johannesburg", Map.of("San Francisco", 16983, "Los Angeles", 16713, "Denver", 15388, "New York", 7969, "Paris", 5410),
                "Paris", Map.of("San Francisco", 5583, "Los Angeles", 5669, "Denver", 4880, "New York", 3635, "Johannesburg", 5410)
        );

        List<String> allCities = new ArrayList<>(cities.keySet());
        Map<String, Integer> totalDistances = new ConcurrentHashMap<>();
        Map<String, List<String>> routes = new ConcurrentHashMap<>();
        Random random = new Random();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (String startCity : allCities) {
            executor.submit(() -> {
                List<String> otherCities = new ArrayList<>(allCities);
                otherCities.remove(startCity);
                List<String> route = calculateDistance(startCity, otherCities, cities, random);
                int totalDistance = calculateTotalDistance(route, cities);
                totalDistances.put(startCity, totalDistance);
                routes.put(startCity, route);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);

        // Print all routes
        for (String city : allCities) {
            System.out.printf("Starting at %s, total distance: %s, route: %s\n",
                    city, formatDistance(totalDistances.get(city)), routes.get(city));
        }

        // Find the best city to start
        Map.Entry<String, Integer> bestCity = totalDistances.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow();

        System.out.printf("\nBest city to start is %s with a total distance of %s miles\n",
                bestCity.getKey(), formatDistance(bestCity.getValue()));
    }

    private static List<String> calculateDistance(String startCity, List<String> otherCities,
            Map<String, Map<String, Integer>> cities, Random random) {
        List<String> route = new ArrayList<>();
        String currentCity = startCity;

        while (!otherCities.isEmpty()) {
            String nextCity = findRandomCity(otherCities, random);
            route.add(currentCity);
            currentCity = nextCity;
            otherCities.remove(currentCity);
        }

        // Return to the start city
        route.add(currentCity);
        route.add(startCity); // Add the start city to complete the route
        return route;
    }

    private static int calculateTotalDistance(List<String> route, Map<String, Map<String, Integer>> cities) {
        int totalDistance = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            String currentCity = route.get(i);
            String nextCity = route.get(i + 1);
            totalDistance += cities.get(currentCity).get(nextCity);
        }

        return totalDistance;
    }

    private static String findRandomCity(List<String> cities, Random random) {
        int randomIndex = random.nextInt(cities.size());
        return cities.get(randomIndex);
    }

    private static String formatDistance(Integer distance) {
        return distance != null ? distance.toString() : "null";
    }

}
   
