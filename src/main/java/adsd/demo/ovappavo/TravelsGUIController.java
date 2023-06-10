package adsd.demo.ovappavo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalTime;
import java.util.ArrayList;

public class TravelsGUIController {

    // FXML OBJECTS
    @FXML protected Label labelTravelsFavoriteHistory;
    @FXML protected Button buttonTravelsPlanYourJourney;
    @FXML protected Button buttonTravelsFavoriteHistory;
    @FXML protected TableView<Route> tableViewTravels;
    @FXML protected TableColumn<Route, String> tableViewTravelsFrom;
    @FXML protected TableColumn<Route, String> tableViewTravelsTo;
    @FXML protected TableColumn<Route, LocalTime> tableViewTravelsBeginTime;
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
    public void onTableViewTravels() {

    }

    // INITIALIZE FUNCTIONS
    private void initHistoryRoutes() {
        labelTravelsFavoriteHistory.setText("Reisgeschiedenis");
        buttonTravelsFavoriteHistory.setText("Favoriete Routes");
        loadTableView(Travels.getTravelHistory());
        OVappController.favoriteTrueHistoryFalse = true;
    }
    private void initFavoriteRoutes() {
        labelTravelsFavoriteHistory.setText("Favoriete Routes");
        buttonTravelsFavoriteHistory.setText("Reisgeschiedenis");

        loadTableView(Travels.getFavoriteRoutes());
        OVappController.favoriteTrueHistoryFalse = false;
    }

    private void setupTravelsInfo() {

    }

    // HELPER FUNCTIONS
    private void loadTableView(ArrayList<Route> arrayList) {
        tableViewTravels.setVisible(true);
        tableViewTravels.getItems().clear();
        arrayList.forEach((route) -> tableViewTravels.getItems().add(route));

        tableViewTravelsFrom.setCellValueFactory(new PropertyValueFactory<Route, String>("firstStopOver"));
        tableViewTravelsTo.setCellValueFactory(new PropertyValueFactory<Route, String>("lastStopOver"));
        tableViewTravelsBeginTime.setCellValueFactory(new PropertyValueFactory<Route, LocalTime>("timeOfDepartureFrom"));
    }
    private void loadListView(ArrayList<Route> arrayList, String add) {
        ObservableList<String> travelsStringData = FXCollections.observableArrayList();

        travelsStringData.add(add);
        for(Route route : arrayList) {
            travelsStringData.add(route.fromToATString(route));
        }
        //listViewTravels.setItems(travelsStringData);
    }
}
