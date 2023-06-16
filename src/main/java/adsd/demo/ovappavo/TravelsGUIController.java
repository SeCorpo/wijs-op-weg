package adsd.demo.ovappavo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.Duration;
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
    @FXML protected Label labelTravelsInfoStap1;
    @FXML protected Label labelTravelsInfoStap2;

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
        setupTravelsInfo();
    }

    // INITIALIZE FUNCTIONS
    private void initHistoryRoutes() {
        labelTravelsFavoriteHistory.setText("Reisgeschiedenis");
        buttonTravelsFavoriteHistory.setText("Favoriete Routes");
        labelTravelsInfoStap1.setText("Stap 1 Maak hier een keuze uit uw bereisde trajecten");

        loadTableView(Travels.getTravelHistory());
        OVappController.favoriteTrueHistoryFalse = true;
    }
    private void initFavoriteRoutes() {
        labelTravelsFavoriteHistory.setText("Favoriete Routes");
        buttonTravelsFavoriteHistory.setText("Reisgeschiedenis");
        labelTravelsInfoStap1.setText("Stap 1 Maak hier een keuze uit uw favoriete trajecten");

        loadTableView(Travels.getFavoriteRoutes());
        OVappController.favoriteTrueHistoryFalse = false;
    }

    private void setupTravelsInfo() {
        textFieldTravelsBeginStation.setText(tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().get(0).getLocationName());
        textFieldTravelsEndStation.setText(tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().get(tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().size() - 1).getLocationName());
        textFieldTravelsStopsCount.setText(String.valueOf(tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().size()-2));

        StringBuilder middleStops = new StringBuilder();
        for(int i = 1; i < tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().size()-1; i++) {
            middleStops.append(tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().get(i).getLocationName() + "; ");
        }
        textFieldTravelsMiddleStops.setText(middleStops.toString());

        textFieldTravelsTravelTime.setText(travelTime());
    }

    // HELPER FUNCTIONS
    private void loadTableView(ArrayList<Route> arrayList) {
        tableViewTravels.setVisible(true);
        tableViewTravels.getItems().clear();
        arrayList.forEach((route) -> tableViewTravels.getItems().add(route));

        tableViewTravelsFrom.setCellValueFactory(new PropertyValueFactory<>("firstStopOver"));
        tableViewTravelsTo.setCellValueFactory(new PropertyValueFactory<>("lastStopOver"));
        tableViewTravelsBeginTime.setCellValueFactory(new PropertyValueFactory<>("timeOfDepartureFrom"));
    }

    private String travelTime() {
        Duration travelDuration = Duration.between(
                tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().get(0).getTimeOfDeparture(),
                tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().get(tableViewTravels.getSelectionModel().getSelectedItem().getStopOvers().size() - 1).getTimeOfArrival()
        );

        long hours = travelDuration.toHours();
        long minutes = travelDuration.toMinutesPart();

        return hours + " hours " + minutes + " minutes";
    }

    public void onDeleteTravels() {
        try {
            if (OVappController.favoriteTrueHistoryFalse) {
                Travels.getFavoriteRoutes().remove(tableViewTravels.getSelectionModel().getSelectedItem());
//                initialize();
            } else if (!OVappController.favoriteTrueHistoryFalse) {
                Travels.getTravelHistory().remove(tableViewTravels.getSelectionModel().getSelectedItem());
//                initialize();
            }
        } catch(Exception e) {
            System.out.println("Please select a route to delete");
            alertNothingSelectedToDelete();
        }
    }

    // ALERTS
    private void alertNothingSelectedToDelete() {
        Alert registerIncorrect = new Alert(Alert.AlertType.WARNING);
        registerIncorrect.setTitle("No route selected");
        registerIncorrect.setContentText("Please select a route to delete");
        registerIncorrect.show();
    }
}
