package adsd.demo.ovappavo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;


/* TESTFASE:
-comboTransport, comboA, comboB preset to vehicle, begin, end, instead of promptText
-getCurrentVehicle init to 'Train'
 */

public class OVappController
{
    // VEHICLE MAP
    private final static Map<String, Vehicle> vehicleMap = new TreeMap<>();
    public static Map<String, Vehicle> getVehicleMap() {return vehicleMap;}
    //Vehicle vehicle = new Vehicle("Vehicle");
    Train train = new Train("Train");
    Bus bus = new Bus("Bus");
    {
        //vehicleMap.put(vehicle.getVehicleName(), vehicle);
        vehicleMap.put(train.getVehicleName(), train);
        vehicleMap.put(bus.getVehicleName(), bus);
    }

    // CURRENT VEHICLE
    private Vehicle currentVehicle;
    public Vehicle getCurrentVehicle() {
        if(currentVehicle == null) {
            setCurrentVehicle(getVehicleMap().get("Train"));
            try {
                vehicleMap.put(getCurrentVehicle().getVehicleName(), getCurrentVehicle());
            } catch (Exception e) {
                System.err.println("Cant put new currentVehicle 'Train' in vehicleMap" + e.getMessage());
            }
        } return currentVehicle;
    }
    public void setCurrentVehicle(Vehicle currentVehicle) {
        try {
            this.currentVehicle = currentVehicle;
        } catch(Exception e) {
            System.out.println("Unable to set currentVehicle, reset to getCurrentVehicle (VehicleNull)");
            getCurrentVehicle();
        }
    }

    // FXML OBJECTS
    @FXML private ComboBox<String> comboTransport;
    @FXML private ComboBox<String> comboA;
    @FXML private ComboBox<String> comboB;
    @FXML private Spinner spinnerTime;
    @FXML private Button buttonPlanMyTrip;
    @FXML private TextArea textAreaSmall;
    @FXML private TextArea textAreaLarge;
    @FXML private Label labelClock;
    @FXML private Button buttonFavoriteTravels;
    @FXML private Button buttonTravelHistory;
    @FXML private Button buttonFavorite;

    public static boolean favoriteTrueHistoryFalse = true;

    public void initialize() {
        initLabelClock();
        initComboTransport();
        initComboA();
        initComboB();
        initSpinnerTime();

        System.out.println("OVappController - initialize done");
    }

    // ACTION EVENTS
    @FXML protected void onComboA() {
        System.out.println("OVappController.onComboA :: to: " + comboA.getValue());
    }
    @FXML protected void onComboB() {
        System.out.println("OVappController.onComboB :: to: " + comboB.getValue());
    }
    @FXML protected void onTransport() {
        setCurrentVehicle(vehicleMap.get(comboTransport.getValue()));
        initComboA();
        initComboB();
        System.out.println("OVappController.onTransportChange :: to: " + comboTransport.getValue() + "\n");
    }
    @FXML protected void onButtonPlanMyTrip() {
        System.out.println("OVappController.onPlanMyTrip >>>>>>>>>>");
        String beginStop = comboA.getValue();
        String endStop = comboB.getValue();
        LocalTime beginTime = LocalTime.parse(spinnerTime.getValue().toString());

        textAreaSmall.setText(getCurrentVehicle().buildTripText(getCurrentVehicle().findTrip(beginStop, endStop, beginTime)));

        latestTraveledRoute();

        System.out.println("OVappController.onPlanMyTrip <<<<<<<<<<");
    }
    @FXML protected void onButtonFavoriteTravels() {
        favoriteTrueHistoryFalse = true;
        OVapp.getInstance().loadFXML("TravelsGUI.fxml");
    }
    @FXML protected void onButtonTravelHistory() {
        favoriteTrueHistoryFalse = false;
        OVapp.getInstance().loadFXML("TravelsGUI.fxml");
    }

    @FXML protected void onButtonFavorite() {
        String beginStop = comboA.getValue();
        String endStop = comboB.getValue();
        LocalTime beginTime = LocalTime.parse(spinnerTime.getValue().toString());


        Travels.addRouteToFavorite(getCurrentVehicle().findTrip(beginStop,endStop,beginTime));
    }

    // INITIALIZE FUNCTIONS
    private void initComboTransport() {
        ObservableList<String> vehicleList = FXCollections.observableArrayList();
        vehicleList.addAll(vehicleMap.keySet());
        comboTransport.setItems(vehicleList);
        comboTransport.getSelectionModel().select(1);
    }
    private void initComboA() {
        ObservableList<String> currentVehicleLocationList = FXCollections.observableArrayList();
        currentVehicleLocationList.addAll(getCurrentVehicle().getLocationMap().keySet());
        comboA.setItems(currentVehicleLocationList);
        comboA.getSelectionModel().select(0);
    }
    private void initComboB() {
        ObservableList<String> currentVehicleLocationList = FXCollections.observableArrayList();
        currentVehicleLocationList.addAll(getCurrentVehicle().getLocationMap().keySet());
        comboB.setItems(currentVehicleLocationList);
        comboB.getSelectionModel().select(1);
    }
    private void initSpinnerTime() {
        spinnerTime.setValueFactory(spinnerTimeController);
    }
        private SpinnerValueFactory<LocalTime> spinnerTimeController = new SpinnerValueFactory<>() {
            {
                setValue(timeNow());
            }
            private LocalTime timeNow() {
                return LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
            }
            public void decrement(int minutes) {
                LocalTime time = getValue();
                setValue(time == null ? timeNow() : time.minusMinutes(minutes * 5));
            }
            public void increment(int minutes) {
                LocalTime time = getValue();
                setValue(time == null ? timeNow() : time.plusMinutes(minutes * 5));
            }
        };

    private void initLabelClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                labelClock.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    // HELPER FUNCTIONS
    private void latestTraveledRoute() {
        try {
            String latestTraveledRouteString =
                    "Latest traveled route\n"
                    + "From: " + Travels.getTravelHistory().get(Travels.getTravelHistory().size()-1).getStopOvers().get(0).getLocationName()
                    + " @ " + Travels.getTravelHistory().get(Travels.getTravelHistory().size()-1).getStopOvers().get(0).getTimeOfDeparture()
                    + " to: " + Travels.getTravelHistory().get(Travels.getTravelHistory().size()-1).getStopOvers().get(
                    Travels.getTravelHistory().get(Travels.getTravelHistory().size()-1).getStopOvers().size()-1).getLocationName();

            System.out.println(latestTraveledRouteString);
        } catch(Exception e) {
            System.out.println("There are no recent travels\n");
        }
    }
}