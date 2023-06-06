package adsd.demo.ovappavo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

public class OVapp extends Application
{
   //Singleton is een ingewikkelde methode (ontwerp pattern) die verzekerd dat er niet meerdere instances van OVapp (ook stage, scene, root) zijn.
   //De functie voegt een extra veiligheidlaag toe aan de applicatie (iets met memorie-leaks)
   private static volatile OVapp instance;

   public static OVapp getInstance() {
      OVapp singleton = instance;
      if(singleton == null) {
         synchronized(OVapp.class) {
            singleton = instance;
            if(singleton == null) {
               instance = singleton = new OVapp();
            }
         }
      } return singleton;
   }

   public Stage stage;
   public Scene scene;
   public Parent root;

   public void start(Stage stage) throws IOException
   {
      OVapp.getInstance().stage = stage;
      FXMLLoader fxmlLoader = new FXMLLoader(OVapp.class.getResource("OVappGUI.fxml"));
      scene = new Scene(fxmlLoader.load(), 1200, 700);
      stage.setTitle("Mobiliteitsfabriek");
      stage.setScene(scene);
      stage.show();
   }

   public static void main( String[] args )
   {
      launch();
   }

   //LOAD FXML PAGE
   public void loadFXML(String fxmlFile) {
      try {
         OVapp.getInstance().root = FXMLLoader.load(getClass().getResource(fxmlFile));
         OVapp.getInstance().scene = new Scene(OVapp.getInstance().root);
         OVapp.getInstance().stage.setScene(OVapp.getInstance().scene);
         OVapp.getInstance().stage.show();
      } catch(Exception e) {
         alertCannotFindFXMLFile();
      }

   }

   //ALERTS
   public void alertCannotFindFXMLFile() {
      Alert cannotFindFXMLFile = new Alert(Alert.AlertType.WARNING);
      cannotFindFXMLFile.setTitle("Cannot Find Page");
      cannotFindFXMLFile.setContentText("Failed to load the FXML file");
      cannotFindFXMLFile.show();
   }
}
