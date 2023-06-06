package adsd.demo.ovappavo;

import java.time.LocalTime;
import java.util.ArrayList;

public class Travels {

    private static ArrayList<Route> travelHistory = new ArrayList<>();
    private static ArrayList<Route> favoriteRoutes = new ArrayList<>();

    //getters
    public static ArrayList<Route> getFavoriteRoutes() {
        return favoriteRoutes;
    }
    public static ArrayList<Route> getTravelHistory() {
        return travelHistory;
    }

    //FAVORITE ROUTES
    static
    {
        //Amersfoort - Utrecht - Amsterdam
            Route route = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amersfoort"), LocalTime.of(12, 0));
            route.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(12, 13), LocalTime.of(12, 21));
            route.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(12, 47));
            favoriteRoutes.add(route);
    }

    //TRAVEL HISTORY
    static
    {
        //Amersfoort - Utrecht - Amsterdam
        Route route = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amersfoort"), LocalTime.of(12, 0));
        route.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(12, 13), LocalTime.of(12, 21));
        route.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(12, 47));
        travelHistory.add(route);
    }
}
