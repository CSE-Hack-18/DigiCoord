

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginStartController {

	
public class PleaseProvideControllerClassName {

    @FXML
    private TextField passwordLogin, idLogin;

    @FXML
    private Button loginButton;



    }

    @FXML
    void loginButton(ActionEvent event) throws IOException {
     
    	FXMLLoader fxmlLoader = new FXMLLoader();
          
    	fxmlLoader.setLocation(getClass().getResource("front.fxml"));
          /* 
           * if "fx:controller" is not set in fxml
           * fxmlLoader.setController(NewWindowController);
           */
          Scene scene = new Scene(fxmlLoader.load(), 1600, 800);
          Stage stage = new Stage();
          stage.setTitle("New Window");
          stage.setScene(scene);
          stage.show();
         
          
          
          
          
    }


}
