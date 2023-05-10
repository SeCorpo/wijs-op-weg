package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

//Vehicle heeft reisbare routes en selecteerbare locaties, Data (abc) is een 'mono-morfisme' versie van Vehicle.
//Vehicle kan worden gezien als de dienstregeling, en heeft functies die elk type vervoermiddel gebruikt (getAllLocations, writeRoute)
public class Vehicle {

    private final String vehicleName;
    private final Map<String, Location> locationMap = new TreeMap<>();
    private final Map<String, Route> routeMap = new TreeMap<>();

    public Vehicle(String vehicleName) {
        this.vehicleName = vehicleName;
    }
    //FN_///////////////////////////////////////////////////////////
    public Map<String, Location> getLocationMap() {
        return locationMap;
    }
    public Map<String, Route> getRouteMap() {
        return routeMap;
    }
    public String getVehicleName() {
        return vehicleName;
    }
    protected void addLocation(String locationNameToAdd) {
        Location locationToAdd = new Location(locationNameToAdd);
        getLocationMap().put(locationToAdd.getLocationName(), locationToAdd);
    }

    //DATA_/////////////////////////////////////////////////////////
    {
        addLocation("Abcoude");
        addLocation("Amersfoort");
        addLocation("Amsterdam");
        addLocation("Arnhem");
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
            route.write();
        }
    }
}

