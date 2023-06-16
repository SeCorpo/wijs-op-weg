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


    //Find track within one hour of your selected time
    public Route findTrip(String beginStop, String endStop, LocalTime beginTime) {
        System.out.println("@Vehicle.findTrip :: find all tracks from: " + beginStop + " to: " + endStop + " @ " + beginTime.toString() + " using: " + getVehicleName());

        int listBegin = 0;
        int listEnd = 0;
        StopOver beginStopOver = null;

        for (Route route : getRouteMap().values()) {
            boolean beginLocationFound = false;
            boolean endLocationFound = false;
            boolean beginTimeFound = false;

            for (StopOver stopOver : route.getStopOvers()) {
                if (stopOver.getLocationName().equals(beginStop)) {
                    beginStopOver = stopOver;
                    listBegin = route.getStopOvers().indexOf(stopOver);
                    beginLocationFound = true;
                }

                if (beginLocationFound && stopOver.getLocationName().equals(endStop)) {
                    listEnd = route.getStopOvers().indexOf(stopOver);
                    endLocationFound = true;
                }

                if (endLocationFound) {
                    LocalTime stopOverDepartureTime = beginStopOver.getTimeOfDeparture();
                    if (beginTime.equals(stopOverDepartureTime)) {
                        beginTimeFound = true;
                        System.out.println("Time is timeOfDeparture");
                    } else if (stopOverDepartureTime.isAfter(beginTime) && stopOverDepartureTime.isBefore(beginTime.plusMinutes(30))) {
                        beginTimeFound = true;
                        System.out.println("Time is after timeOfDeparture & before +30 min");
                    } else if (stopOverDepartureTime.isAfter(beginTime) && stopOverDepartureTime.isBefore(beginTime.plusMinutes(60))) {
                        beginTimeFound = true;
                        System.out.println("Time is after timeOfDeparture & before +60 min");
                    }
                }

                if (beginLocationFound && endLocationFound && beginTimeFound) {
                    ArrayList<StopOver> trip = new ArrayList<>();

                    for (int i = listBegin; i <= listEnd; i++) {
                        trip.add(route.getStopOvers().get(i));
                    }

                    // Automatically add the found route to travels
                    Route route1 = new Route(trip);
                    Travels.getTravelHistory().add(route1);
                    return route1;
                }
            }
        }

        System.out.println("Cannot find a route between: " + beginStop + " and " + endStop + " @ " + beginTime);
        System.out.println("No route is added to your travel history" + "\n");
        return null;
    }


    public void writeTrip(List<StopOver> trip) {
        System.out.println("@Track.writeRoute :: Route from: " + trip.get(0).getLocationName() + " to: " +
                trip.get(trip.size() - 1).getLocationName() + " @ " + trip.get(0).getTimeOfDeparture().toString());
        for (StopOver stopOver : trip) {
            System.out.println("Location: " + stopOver.getLocationName() + " @ " + stopOver.getTimeOfArrival());
        }
    }
    public String buildTripText(Route route) {
        if (route != null) {
            StringBuilder tripToString = new StringBuilder();
            tripToString.append(String.format("Route van %s naar %s%n", route.getStopOvers().get(0).getLocationName(), route.getStopOvers().get(route.getStopOvers().size() - 1).getLocationName()))
                    .append(System.lineSeparator())
                    .append(String.format("Vertrektijd:       %s%n", route.getStopOvers().get(0).getTimeOfDeparture().toString()))
                    .append(String.format("Beginstation:    %s%n", route.getStopOvers().get(0).getLocationName()));

            for (int i = 1; i < route.getStopOvers().size() - 1; i++) {
                StopOver stopOver = route.getStopOvers().get(i);
                tripToString.append(String.format("Tussenstop:      %s om %s%n", stopOver.getLocationName(), stopOver.getTimeOfArrival()));
            }

            StopOver lastStopOver = route.getStopOvers().get(route.getStopOvers().size() - 1);
            tripToString.append(String.format("Eindstation:      %s%n", lastStopOver.getLocationName()))
                    .append(String.format("Aankomsttijd:   %s%n", lastStopOver.getTimeOfArrival()))
                    .append(System.lineSeparator())
                    .append("Wij wensen u een fijne reis!");

            String outputTrip = tripToString.toString();
            System.out.println(outputTrip);
            return outputTrip;
        } else {
            return "Cannot build your trip";
        }
    }
}