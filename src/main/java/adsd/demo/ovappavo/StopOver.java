package adsd.demo.ovappavo;

import java.time.LocalTime;

public class StopOver extends Location {

    private final LocalTime timeOfArrival;
    private final LocalTime timeOfDeparture;

    StopOver(String locationName, LocalTime timeOfArrival, LocalTime timeOfDeparture) {
        super(locationName);
        this.timeOfArrival = timeOfArrival;
        this.timeOfDeparture = timeOfDeparture;
    }
    public LocalTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public LocalTime getTimeOfDeparture() {
        return timeOfDeparture;
    }

}
