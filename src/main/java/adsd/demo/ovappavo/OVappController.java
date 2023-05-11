package adsd.demo.ovappavo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.Map;
import java.util.TreeMap;

public class OVappController
{
   private Map<String, Vehicle> vehicleMap = new TreeMap<>();
   Vehicle vehicle = new Vehicle("Vehicle");
   Train train = new Train("Train");
   Bus bus = new Bus("Bus");
   {
      vehicleMap.put(vehicle.getVehicleName(), vehicle);
      vehicleMap.put(train.getVehicleName(), train);
      vehicleMap.put(bus.getVehicleName(), bus);
   }

   private Vehicle currentVehicle;

   public Vehicle getCurrentVehicle() {
         if(currentVehicle == null) {
            currentVehicle = new Vehicle("VehicleNull");

         } return currentVehicle;
   }
   public void setCurrentVehicle(Vehicle currentVehicle) {
      this.currentVehicle = currentVehicle;
   }

   @FXML private ComboBox<String> comboTransport;
   @FXML private ComboBox<String> comboA;
   @FXML private ComboBox<String> comboB;
   @FXML private Button buttonPlanMyTrip;
   @FXML private TextArea textArea;

   public void initialize() {

      setComboTransport();
      setComboA();
      setComboB();

      System.out.println("init TransportSelectorController done");
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
      setComboA();
      setComboB();
      System.out.println("OVappController.onTransportChange :: to: " + comboTransport.getValue());
   }
   @FXML protected void onButtonPlanMyTrip() {
      System.out.println("OVappController.onPlanMyTrip");
      System.out.format("OVType: %s\n", comboTransport.getValue());
      System.out.format("From:   %s\n", comboA.getValue());
      System.out.format("To:     %s\n", comboB.getValue());

      String text = String.format("%-8s %-15s\n", "OV-middel:", comboTransport.getValue());
      text += String.format("%-8s %-15s\n", "Vanaf:", comboA.getValue());
      text += String.format("%-8s %-15s\n", "Naar:", comboB.getValue());

      textArea.setText( text );
   }

   // SETTERS
   private void setComboTransport() {
      ObservableList<String> vehicleList = FXCollections.observableArrayList();
      vehicleList.addAll(vehicleMap.keySet());
      comboTransport.setItems(vehicleList);
      comboTransport.getSelectionModel().select(2);
   }
   private void setComboA() {
      ObservableList<String> currentVehicleLocationList = FXCollections.observableArrayList();
      currentVehicleLocationList.addAll(getCurrentVehicle().getLocationMap().keySet());
      comboA.setItems(currentVehicleLocationList);
      comboA.getSelectionModel().select(0);
   }
   private void setComboB() {
      ObservableList<String> currentVehicleLocationList = FXCollections.observableArrayList();
      currentVehicleLocationList.addAll(getCurrentVehicle().getLocationMap().keySet());
      comboB.setItems(currentVehicleLocationList);
      comboB.getSelectionModel().select(comboB.getItems().size() - 1);
   }
}
