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
  private void addLocation(String locationNameToAdd) {
    Location locationToAdd = new Location(locationNameToAdd);
    locationMap.put(locationToAdd.getLocationName(), locationToAdd);
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
    // Amersfoort - Utrecht - Amsterdam
    for (int hour = 8; hour <= 21; hour += 2) {
      LocalTime timeOfDeparture = LocalTime.of(hour, 0);
      Route route = new Route(locationMap.get("Amersfoort"), timeOfDeparture);
      route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour, 54), LocalTime.of(hour, 58));
      route.addEndPoint(locationMap.get("Amsterdam"), LocalTime.of(hour + 2, 26));
      routeMap.put(route.getKey(), route);
      route.write();
    }
    // Amsterdam - Utrecht - Amersfoort
    for (int hour = 8; hour <= 21; hour += 2) {
      LocalTime timeOfDeparture = LocalTime.of(hour, 0);
      Route route = new Route(locationMap.get("Amsterdam"), timeOfDeparture);
      route.addStopOver(locationMap.get("Utrecht"), LocalTime.of(hour + 1, 28), LocalTime.of(hour + 1, 32));
      route.addEndPoint(locationMap.get("Amersfoort"), LocalTime.of(hour + 2, 30));
      routeMap.put(route.getKey(), route);
      route.write();
    }
    // Maastricht - Nijmegen
    for (int hour = 8; hour <= 21; hour += 2) {
      LocalTime timeOfDeparture = LocalTime.of(hour, 0);
      Route route = new Route(locationMap.get("Maastricht"), timeOfDeparture);
      route.addEndPoint(locationMap.get("Nijmegen"), LocalTime.of(hour + 2, 47));
      routeMap.put(route.getKey(), route);
      route.write();
    }
    // Nijmegen - Maastricht
    for (int hour = 8; hour <= 21; hour += 2) {
      LocalTime timeOfDeparture = LocalTime.of(hour, 0);
      Route route = new Route(locationMap.get("Nijmegen"), timeOfDeparture);
      route.addEndPoint(locationMap.get("Maastricht"), LocalTime.of(hour + 2, 47));
      routeMap.put(route.getKey(), route);
      route.write();
    }
    // Nijmegen - Xanten
    for (int hour = 9; hour <= 20; hour += 2) {
      LocalTime timeOfDeparture = LocalTime.of(hour, 16);
      Route route = new Route(locationMap.get("Nijmegen"), timeOfDeparture);
      route.addEndPoint(locationMap.get("Xanten"), LocalTime.of(hour + 3, 4));
      routeMap.put(route.getKey(), route);
      route.write();
    }
    // Xanten - Nijmegen
    for (int hour = 9; hour <= 20; hour += 2) {
      LocalTime timeOfDeparture = LocalTime.of(hour, 16);
      Route route = new Route(locationMap.get("Xanten"), timeOfDeparture);
      route.addEndPoint(locationMap.get("Nijmegen"), LocalTime.of(hour + 3, 4));
      routeMap.put(route.getKey(), route);
      route.write();
    }
  }
}