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
    //FN_///////////////////////////////////////////////////////////
    @Override
    public Map<String, Location> getLocationMap() {
        return locationMap;
    }
    @Override
    public Map<String, Route> getRouteMap() {
        return routeMap;
    }
    private void addLocation(String locationNameToAdd) {
        Location locationToAdd = new Location(locationNameToAdd);
        locationMap.put(locationToAdd.getLocationName(), locationToAdd);
    }
    //DATA_/////////////////////////////////////////////////////////
    {
        addLocation("Amersfoort");
        addLocation("Amsterdam");
        addLocation("Groningen");
        addLocation("Haarlem");
        addLocation("Maastricht");
        addLocation("Nijmegen");
        addLocation("Rotterdam");
        addLocation("Utrecht");
        addLocation("Vlissingen");
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
        // Groningen - Amersfoort - Utrecht
        for (int hour = 8; hour <= 21; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Groningen"), timeOfDeparture);
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(hour + 1, 37), LocalTime.of(hour, 40));
            route.addEndPoint(locationMap.get("Utrecht"), LocalTime.of(hour + 1, 53));
            routeMap.put(route.getKey(), route);
        }
        // Utrecht - Amersfoort - Groningen
        for (int hour = 8; hour <= 21; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 0);
            Route route = new Route(locationMap.get("Utrecht"), timeOfDeparture);
            route.addStopOver(locationMap.get("Amersfoort"), LocalTime.of(hour, 13), LocalTime.of(hour, 15));
            route.addEndPoint(locationMap.get("Groningen"), LocalTime.of(hour + 1, 53));
            routeMap.put(route.getKey(), route);
        }
        // Nijmegen - Utrecht - Amsterdam - Haarlem
        for (int hour = 8; hour <= 21; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 13);
            Route route = new Route(locationMap.get("Nijmegen"), timeOfDeparture);
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour + 1, 7), LocalTime.of(hour + 1, 9));
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour + 1, 35), LocalTime.of(hour + 1, 45));
            route.addEndPoint(locationMap.get("Haarlem"), LocalTime.of(hour + 2, 5));
            routeMap.put(route.getKey(), route);
        }
        // Haarlem - Amsterdam - Utrecht - Nijmegen
        for (int hour = 8; hour <= 21; hour += 1) {
            LocalTime timeOfDeparture = LocalTime.of(hour, 2);
            Route route = new Route(locationMap.get("Haarlem"), timeOfDeparture);
            route.addStopOver(locationMap.get("Amsterdam"), LocalTime.of(hour, 20), LocalTime.of(hour, 24));
            route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 51), LocalTime.of(hour, 53));
            route.addEndPoint(locationMap.get("Nijmegen"), LocalTime.of(hour + 1, 47));
            routeMap.put(route.getKey(), route);
        }
    }
}