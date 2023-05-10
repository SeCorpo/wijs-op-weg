package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

public class Train extends Vehicle {

    private final Map<String, Location> locationMap = new TreeMap<>();
    private final Map<String, Route> routeMap = new TreeMap<>();

    public Train(String vehicleName) {
        super(vehicleName);
    }

    {
        Location location = new Location("Amersfoort");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Amsterdam");
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
    }

    {
        //Amersfoort - Utrecht - Amsterdam
        for (int hour = 6; hour <= 22; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Amersfoort"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 13), LocalTime.of(hour, 21));
            route.addEndPoint(locationMap.get("Amsterdam"), LocalTime.of(hour, 47));
            routeMap.put(route.getKey(), route);
        }
        //Amsterdam - Utrecht - Amersfoort
        for (int hour = 6; hour <= 22; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Amsterdam"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 26), LocalTime.of(hour, 34));
            route.addEndPoint(locationMap.get("Amersfoort"), LocalTime.of(hour, 47));
            routeMap.put(route.getKey(), route);
        }
        //Groningen - Nijmegen - Maastricht
        for (int hour = 6; hour <= 20; hour += 2) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 30);
            Route route = new Route(locationMap.get("Groningen"), timeOfDeparture);
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour + 2, 36), LocalTime.of(hour + 2, 46)); //2uur,6min + 10
            route.addEndPoint(locationMap.get("Maastricht"), LocalTime.of(hour + 3, 59)); //1uur,13min
            routeMap.put(route.getKey(), route);
        }
        //Maastricht - Nijmegen - Groningen
        for (int hour = 6; hour <= 20; hour += 2) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 30);
            Route route = new Route(locationMap.get("Maastricht"), timeOfDeparture);
            route.addStopOver(locationMap.get("Nijmegen"), LocalTime.of(hour + 1, 43), LocalTime.of(hour + 1, 53));
            route.addEndPoint(locationMap.get("Groningen"), LocalTime.of(hour + 3, 59));
            routeMap.put(route.getKey(), route);
        }
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
}