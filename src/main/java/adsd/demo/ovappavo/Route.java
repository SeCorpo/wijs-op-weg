package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
}
