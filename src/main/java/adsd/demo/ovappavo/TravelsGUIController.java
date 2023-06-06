package adsd.demo.ovappavo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class TravelsGUIController {

    // FXML OBJECTS
    @FXML protected Label labelTravelsFavoriteHistory;
    @FXML protected Button buttonTravelsPlanYourJourney;
    @FXML protected Button buttonTravelsFavoriteHistory;
    @FXML protected ListView<String> listViewTravels;
    @FXML protected TextField textFieldTravelsBeginStation;
    @FXML protected TextField textFieldTravelsEndStation;
    @FXML protected TextField textFieldTravelsStopsCount;
    @FXML protected TextField textFieldTravelsMiddleStops;
    @FXML protected TextField textFieldTravelsTravelTime;

    public void initialize() {
        if(OVappController.favoriteTrueHistoryFalse) {
            initFavoriteRoutes();
            System.out.println("TravelsGUIController - initialize favorites done");
        } else if(!OVappController.favoriteTrueHistoryFalse) {
            initHistoryRoutes();
            System.out.println("TravelsGUIController - initialize history done");
        }
    }

    // ACTION EVENTS

    public void onButtonTravelsPlanYourJourney() {
        OVapp.getInstance().loadFXML("OVappGUI.fxml");
    }
    public void onButtonFavoriteTravelHistory() {
        initialize();
    }

    // INITIALIZE FUNCTIONS
    private void initHistoryRoutes() {
        labelTravelsFavoriteHistory.setText("Reisgeschiedenis");
        buttonTravelsFavoriteHistory.setText("Favoriete Routes");
        loadListView(Travels.getTravelHistory(), "HistoryRoutes");
        OVappController.favoriteTrueHistoryFalse = true;
    }
    private void initFavoriteRoutes() {
        labelTravelsFavoriteHistory.setText("Favoriete Routes");
        buttonTravelsFavoriteHistory.setText("Reisgeschiedenis");
        loadListView(Travels.getFavoriteRoutes(), "Favorite Routes");
        OVappController.favoriteTrueHistoryFalse = false;
    }

    // HELPER FUNCTIONS
    private void loadListView(ArrayList<Route> arrayList, String add) {
        ObservableList<String> travelsStringData = FXCollections.observableArrayList();

        travelsStringData.add(add);
        for(Route route : arrayList) {
            travelsStringData.add(route.fromToATString(route));
        }
        listViewTravels.setItems(travelsStringData);
    }
}
