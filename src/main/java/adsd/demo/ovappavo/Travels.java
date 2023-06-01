package adsd.demo.ovappavo;

import java.util.ArrayList;

public class Travels {

    private static ArrayList<Route> travelHistory = new ArrayList<>();
    private ArrayList<Route> favoriteRoutes = new ArrayList<>();

    //getters
    public ArrayList<Route> getFavoriteRoutes() {
        return favoriteRoutes;
    }
    public static ArrayList<Route> getTravelHistory() {
        return travelHistory;
    }
}
