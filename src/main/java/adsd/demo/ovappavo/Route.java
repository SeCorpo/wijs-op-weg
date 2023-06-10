package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;

public class Route {

    private ArrayList<StopOver> stopOvers = new ArrayList<>();

    Route(Location beginLocation, LocalTime timeOfDeparture) {
        StopOver stopOver = new StopOver(beginLocation.getLocationName(), timeOfDeparture, timeOfDeparture);
        stopOvers.add(stopOver);
    }
    Route(ArrayList<StopOver> stopOvers) {
        this.stopOvers = stopOvers;
    }
    //FN_///////////////////////////////////////////////////////////
    public ArrayList<StopOver> getStopOvers() {
        return stopOvers;
    }
    public void addStopOver( Location location, LocalTime timeOfArrival, LocalTime timeOfDeparture ) {
        StopOver stopover = new StopOver(location.getLocationName(), timeOfArrival, timeOfDeparture );
        stopOvers.add( stopover );
    }
    public void addEndPoint(Location location, LocalTime timeOfArrival) {
        StopOver stopover = new StopOver(location.getLocationName(), timeOfArrival, timeOfArrival);
        stopOvers.add(stopover);
    }
    public String getKey() {
        String key = stopOvers.get(0).getLocationName();

        for (int i = 1; i < stopOvers.size(); i++) {
            key += "-";
            key += stopOvers.get(i).getLocationName();
        }

        key += "|";
        key += stopOvers.get(0).getTimeOfDeparture();

        return key;
    }
    public String getFirstStopOver() {
        if(stopOvers != null && !stopOvers.isEmpty()) {
            return stopOvers.get(0).getLocationName();
        }
        return null;
    }
    public String getLastStopOver() {
        if(stopOvers != null && !stopOvers.isEmpty()) {
            return stopOvers.get(stopOvers.size()-1).getLocationName();
        }
        return null;
    }
    public LocalTime getTimeOfDepartureFrom() {
        if(stopOvers != null && !stopOvers.isEmpty()) {
            return stopOvers.get(0).getTimeOfDeparture();
        }
        return null;
    }

    public void write() {
        StopOver first = stopOvers.get(0);
        StopOver last = stopOvers.get(stopOvers.size() - 1);

        if (first.getLocationName() == null || last.getLocationName() == null) {
            System.out.println("The route cannot be found.");
        } else {
            System.out.format("%-6s [%-13s] %-4s %-10s %-2s %-2s %-5s %-4s %-8s %-2s %-2s %-5s\n", "Route:", getKey(), " ", "Departure: ", first.getLocationName(), "at ",
                    first.getTimeOfDeparture(), " ", "Arrival: ", last.getLocationName(), "at ", last.getTimeOfArrival());
        }
    }
    public String fromToATString(Route route) {
        String from = route.getStopOvers().get(0).getLocationName();
        String to = route.getStopOvers().get(route.getStopOvers().size()-1).getLocationName();
        String at = route.getStopOvers().get(0).getTimeOfDeparture().toString();
        return String.format("%-8s %-15s %-8s %-15s @ %-6s\n", "From", from, "To", to, at);
    }
}
