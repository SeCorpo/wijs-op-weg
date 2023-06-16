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

    public static void addRouteToFavorite(Route route) {
        favoriteRoutes.add(route);
        System.out.println("Route added to favoriteRoutes");
    }

    //FAVORITE ROUTES
    static
    {
        //Amersfoort - Utrecht - Amsterdam
        Route route = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amersfoort"), LocalTime.of(12, 0));
        route.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(12, 13), LocalTime.of(12, 21));
        route.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(12, 47));
        favoriteRoutes.add(route);

        // Haarlem - Amsterdam - Utrecht - Nijmegen
        Route route1 = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Haarlem"), LocalTime.of(15,2));
        route1.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(15,20), LocalTime.of(15,24));
        route1.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(15,51), LocalTime.of(15,53));
        route1.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Nijmegen"), LocalTime.of(16, 47));
        favoriteRoutes.add(route1);

    }

    //TRAVEL HISTORY
    static
    {
        //Amersfoort - Utrecht - Amsterdam
        Route route = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amersfoort"), LocalTime.of(12, 0));
        route.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(12, 13), LocalTime.of(12, 21));
        route.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(12, 47));
        travelHistory.add(route);

        //Nijmegen - Maastricht
        Route route1 = new Route(OVappController.getVehicleMap().get("Bus").getLocationMap().get("Nijmegen"), LocalTime.of(16,0));
        route1.addEndPoint(OVappController.getVehicleMap().get("Bus").getLocationMap().get("Maastricht"), LocalTime.of(18, 47));
        travelHistory.add(route1);

        // Amsterdam - Utrecht - Nijmegen
        Route route2 = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(8,24));
        route2.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(8, 51), LocalTime.of(8, 53));
        route2.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Nijmegen"), LocalTime.of(9, 47));
        travelHistory.add(route2);

        // Haarlem - Amsterdam - Utrecht - Nijmegen
        Route route3 = new Route(OVappController.getVehicleMap().get("Train").getLocationMap().get("Haarlem"), LocalTime.of(15,2));
        route3.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Amsterdam"), LocalTime.of(15,20), LocalTime.of(15,24));
        route3.addStopOver(OVappController.getVehicleMap().get("Train").getLocationMap().get("Utrecht"), LocalTime.of(15,51), LocalTime.of(15,53));
        route3.addEndPoint(OVappController.getVehicleMap().get("Train").getLocationMap().get("Nijmegen"), LocalTime.of(16, 47));
        travelHistory.add(route3);
    }
}
