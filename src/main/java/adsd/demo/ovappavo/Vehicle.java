package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

//Vehicle heeft reisbare routes en selecteerbare locaties
public class Vehicle {

    private final String vehicleName;
    private final Map<String, Location> locationMap = new TreeMap<>();
    private final Map<String, Route> routeMap = new TreeMap<>();

    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    {
        Location location = new Location("Abcoude");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Amersfoort");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Amsterdam");
        locationMap.put(location.getLocationName(), location);

        location = new Location("Arnhem");
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
        // Amersfoort - Utrecht - Amsterdam
        for (int hour = 6; hour <= 22; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Amersfoort"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 13), LocalTime.of(hour, 21));
            route.addEndPoint(locationMap.get("Amsterdam"), LocalTime.of(hour, 47));
            routeMap.put(route.getKey(), route);
            route.write();
        }
        // Amsterdam - Utrecht - Amersfoort
        for (int hour = 6; hour <= 22; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Amsterdam"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 26), LocalTime.of(hour, 34));
            route.addEndPoint(locationMap.get("Amersfoort"), LocalTime.of(hour, 47));
            routeMap.put(route.getKey(), route);
            route.write();
        }
    }

    //Getters standaard return (polymorfisme)
    public Map<String, Location> getLocationMap() {
        return locationMap;
    }
    public Map<String, Route> getRouteMap() {
        return routeMap;
    }
    public String getVehicleName() {
        return vehicleName;
    }

    //FN
    public String[] getAllLocations() {
        String[] locations = new String[getLocationMap().size()];
        int i = 0;
        for(Location location : getLocationMap().values()) {
            locations[i] = location.getLocationName();
            i++;
        }
        return locations;
    }

}
