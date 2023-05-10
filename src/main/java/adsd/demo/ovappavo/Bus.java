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
    //FN_///////////////////////////////////////////////////////////
    @Override
    public Map<String, Location> getLocationMap() {
        return locationMap;
    }
    @Override
    public Map<String, Route> getRouteMap() {
        return routeMap;
    }

    //DATA_/////////////////////////////////////////////////////////
    {
        addLocation("Abcoude");
        addLocation("Amersfoort");
        addLocation("Amsterdam");
        addLocation("Emmen");
        addLocation("Groningen");
        addLocation("Haarlem");
        addLocation("Maastricht");
        addLocation("Nijmegen");
        addLocation("Rotterdam");
        addLocation("Utrecht");
        addLocation("Vlissingen");
        addLocation("Xanten");
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