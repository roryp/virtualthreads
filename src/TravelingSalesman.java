import java.util.*;

public class TravelingSalesman {

    public static void main(String[] args) throws InterruptedException {
        Map<String, Map<String, Integer>> cities = Map.of(
            "San Francisco", Map.of("Los Angeles", 383, "Denver", 1258, "New York", 2904),
            "Los Angeles", Map.of("San Francisco", 383, "Denver", 1015, "New York", 2797),
            "Denver", Map.of("San Francisco", 1258, "Los Angeles", 1015, "New York", 1771),
            "New York", Map.of("San Francisco", 2904, "Los Angeles", 2797, "Denver", 1771)
        );

        List<String> allCities = new ArrayList<>(cities.keySet());

        for (String startCity : allCities) {
            Thread t = Thread.startVirtualThread(() -> {
                List<String> otherCities = new ArrayList<>(allCities);
                otherCities.remove(startCity);
                int totalDistance = calculateDistance(startCity, otherCities, cities);
                System.out.printf("Starting at %s: %d miles\n", startCity, totalDistance);
            });
            t.join();
        }
    }

    private static int calculateDistance(String startCity, List<String> otherCities, Map<String, Map<String, Integer>> cities) {
        int totalDistance = 0;
        String currentCity = startCity;

        while (!otherCities.isEmpty()) {
            String nearestCity = findNearestCity(currentCity, otherCities, cities);
            totalDistance += cities.get(currentCity).get(nearestCity);
            currentCity = nearestCity;
            otherCities.remove(currentCity);
        }

        // return to start city
        totalDistance += cities.get(currentCity).get(startCity);
        return totalDistance;
    }

    private static String findNearestCity(String currentCity, List<String> cities, Map<String, Map<String, Integer>> allCities) {
        return cities.stream()
            .min(Comparator.comparingInt(city -> allCities.get(currentCity).get(city)))
            .orElseThrow();
    }
}