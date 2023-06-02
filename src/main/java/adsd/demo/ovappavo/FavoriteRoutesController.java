package adsd.demo.ovappavo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FavoriteRoutesController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToOVapp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OVappGUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button buttonSwitchOVApp;
}
