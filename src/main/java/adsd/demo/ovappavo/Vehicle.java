package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
    private void addLocation(String locationNameToAdd) {
        Location locationToAdd = new Location(locationNameToAdd);
        locationMap.put(locationToAdd.getLocationName(), locationToAdd);
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
    //find track between 1 hour before and 1 hour after beginTime
    public List<StopOver> findTrip(String beginStop, String endStop, LocalTime beginTime) {
        System.out.println("@Vehicle.findTrip :: find all tracks from: " + beginStop + " to: " + endStop + " @ " + beginTime.toString() + " using: " + getVehicleName());

        boolean beginLocationFound;
        boolean endLocationFound;
        boolean beginTimeFound;
        boolean trackFound = false;

        StopOver beginLocation;
        StopOver endLocation;

        int listBegin = 0;
        int listEnd = 0;

        for(Route route : getRouteMap().values()) {
            beginLocationFound = false;
            endLocationFound = false;
            beginTimeFound = false;
            beginLocation = null;
            endLocation = null;

            for(StopOver stopOver : route.getStopOvers()) {
                if(stopOver.getLocationName().equals(beginStop)) {
                    beginLocation = stopOver;
                    listBegin = route.getStopOvers().indexOf(stopOver);
                    beginLocationFound = true;
                } else if(stopOver.getLocationName().equals(endStop)) {
                    endLocation = stopOver;
                    listEnd = route.getStopOvers().indexOf(stopOver);
                    endLocationFound = true;
                } else if(stopOver.getTimeOfDeparture().isAfter(beginTime.minusHours(1)) &&
                        stopOver.getTimeOfDeparture().isBefore(beginTime.plusHours(1))) {
                    beginTimeFound = true;
                }
            }

            if(beginLocationFound && endLocationFound && beginTimeFound && beginLocation.getTimeOfDeparture().isBefore(endLocation.getTimeOfArrival())) {
                List<StopOver> trip = new ArrayList<>();

                for (int i = listBegin; i <= listEnd; i++) {
                    trip.add(route.getStopOvers().get(i));
                }
                Route route1 = new Route((ArrayList<StopOver>) trip);
                //todo: add route1 to currentUser > find location for currentUser (if currentUser != "currentUser" aka null)
                return trip;
            }
        }
        if(!trackFound) {
            System.out.println("Cannot find a route between: " + beginStop + " and " + endStop + " @ " + beginTime);
        } return null;
    }
    public void writeTrip(List<StopOver> trip) {
        System.out.println("@Track.writeRoute :: Route from: " + trip.get(0).getLocationName() + " to: " +
                trip.get(trip.size() - 1).getLocationName() + " @ " + trip.get(0).getTimeOfDeparture().toString());
        for (StopOver stopOver : trip) {
            System.out.println("Location: " + stopOver.getLocationName() + " @ " + stopOver.getTimeOfArrival());
        }
    }
    public String buildTripText(List<StopOver> trip) {
        if(trip != null) {
            StringBuilder tripToString = new StringBuilder();
            tripToString.append("Route from: " + trip.get(0).getLocationName())
                    .append(" to: " + trip.get(trip.size() - 1).getLocationName())
                    .append(" at: " + trip.get(0).getTimeOfDeparture().toString() + "\n");

            for (StopOver stopOver : trip) {
                tripToString.append(" Location: " + stopOver.getLocationName())
                        .append(" arriving at: " + stopOver.getTimeOfArrival() + "\n");
            }
            String outputTrip = tripToString.toString();
            System.out.println(outputTrip);
            return outputTrip;
        }
        else { return "Cannot find your trip";}
    }
}