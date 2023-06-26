import java.util.*;
import java.util.concurrent.Executors;

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
        Map<String, Integer> totalDistances = new HashMap<>();
        Random random = new Random();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (String startCity : allCities) {
                executor.submit(() -> {
                    List<String> otherCities = new ArrayList<>(allCities);
                    otherCities.remove(startCity);
                    int totalDistance = calculateDistance(startCity, otherCities, cities, random);
                    System.out.printf("Starting at %s: %d miles\n", startCity, totalDistance);
                    totalDistances.put(startCity, totalDistance);
                });
            }
        }

        // Let's wait for all tasks to finish before finding the best city
        Thread.sleep(1000);

        Map.Entry<String, Integer> bestCity = totalDistances.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow();

        System.out.printf("Best city to start is %s with a total distance of %d miles\n", bestCity.getKey(),
                bestCity.getValue());
    }

    private static int calculateDistance(String startCity, List<String> otherCities,
            Map<String, Map<String, Integer>> cities, Random random) {
        int totalDistance = 0;
        String currentCity = startCity;

        while (!otherCities.isEmpty()) {
            String nextCity = findRandomCity(otherCities, random);
            totalDistance += cities.get(currentCity).get(nextCity);
            currentCity = nextCity;
            otherCities.remove(currentCity);
        }

        // return to start city
        totalDistance += cities.get(currentCity).get(startCity);
        return totalDistance;
    }

    private static String findRandomCity(List<String> cities, Random random) {
        int randomIndex = random.nextInt(cities.size());
        return cities.get(randomIndex);
    }
}