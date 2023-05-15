package adsd.demo.ovappavo;

import java.util.ArrayList;

public class User {

    private final String username;
    private final String password;
    private ArrayList<Route> travelHistory;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.travelHistory = new ArrayList<>();
        //todo: put automatic in userMap

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Route> getTravelHistory() {
        return travelHistory;
    }

}
