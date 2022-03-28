package adsd.demo.ovintro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class OVIntroController
{
   @FXML private ComboBox<String> comboTransport;
   @FXML private ComboBox<String> comboA;
   @FXML private ComboBox<String> comboB;
   @FXML private TextArea         textArea;

   @FXML
   public void onComboA()
   {
      System.out.println( "OVIntroController.onComboA" );
   }

   @FXML
   public void onComboB()
   {
      System.out.println( "OVIntroController.onComboB" );
   }

   @FXML
   protected void onTransport()
   {
      System.out.print( "OVIntroController.onTransportChange" );
   }

   @FXML
   protected void onPlanMyTrip()
   {
      System.out.println( "OVIntroController.onPlanMyTrip" );
      System.out.format( "OVType: %s\n", comboTransport.getValue() );
      System.out.format( "From:   %s\n", comboA.getValue() );
      System.out.format( "To:     %s\n", comboB.getValue() );

      String text = String.format( "%-8s %-15s\n", "OVType:", comboTransport.getValue() );
      text += String.format( "%-8s %-15s\n", "From:", comboA.getValue() );
      text += String.format( "%-8s %-15s\n", "To:", comboB.getValue() );

      textArea.setText( text );
   }

   // Important method to initialize this Controller object!!!
   public void initialize()
   {
      System.out.println( "init TransportSelectorController ..." );

      // Initialise the combo box comboTransport with transportation types ...
      {
         String[] ovtypes = { "bus", "tram", "train", "plane" };

         ObservableList<String> list = FXCollections.observableArrayList( ovtypes );
         comboTransport.setItems( list );
         comboTransport.getSelectionModel().select( 2 ); // i.e. "train"
      }

      // Initialise the combo box comboA with stopover locations.
      {
         String[] locations = { "Amsterdam", "Amersfoort", "Arnhem", "Nijmegen", "Utrecht", "Rotterdam", "Vlissingen", "Maastricht", "Groningen" };

         ObservableList<String> list = FXCollections.observableArrayList( locations );
         comboA.setItems( list );
         comboA.getSelectionModel().select( 0 ); // i.e. "Amsterdam"

         comboB.setItems( list );
         comboB.getSelectionModel().select( comboB.getItems().size() - 1 );
      }

      System.out.println( "init TransportSelectorController done" );
   }

}
