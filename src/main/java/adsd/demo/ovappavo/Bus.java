package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Bus extends Vehicle {

    private final Map<String, Location> locationMap = new TreeMap<>();
    private final Map<String, Route> routeMap = new TreeMap<>();

    Bus(String vehicleName) {
        super(vehicleName);
    }
    //Getters standaard return (polymorfisme)
    @Override
    public Map<String, Location> getLocationMap() {
        return locationMap;
    }
    @Override
    public Map<String, Route> getRouteMap() {
        return routeMap;
    }
    {
        Location location = new Location("Abcoude");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Amersfoort");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Amsterdam");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Emmen");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Groningen");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Haarlem");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Maastricht");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Nijmegen");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Rotterdam");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Utrecht");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Vlissingen");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Xanten");
        locationMap.put(location.getLocationName(), location);
    }
    {
        //Amersfoort - Utrecht - Amsterdam
        for (int hour = 8; hour <= 21; hour += 2) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Amersfoort"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 54), LocalTime.of(hour, 58));
            route.addEndPoint(locationMap.get("Amsterdam"), LocalTime.of(hour + 3, 26));
            routeMap.put(route.getKey(), route);
        }
        //Amsterdam - Utrecht - Amersfoort
        for (int hour = 8; hour <= 21; hour += 2) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Amsterdam"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour + 2, 28), LocalTime.of(hour, 32));
            route.addEndPoint(locationMap.get("Amersfoort"), LocalTime.of(hour + 3, 30));
            routeMap.put(route.getKey(), route);
        }
    }
}