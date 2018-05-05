

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class loginStartController {

	

	@FXML
    private Button loginButton;

    @FXML
    private TextField passwordLogin, idLogin;

    public TextField getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(TextField passwordLogin) {
		this.passwordLogin = passwordLogin;
	}
	
	

	public TextField getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(TextField idLogin) {
		this.idLogin = idLogin;
	}


  
String username ="910831";
String password = "123"; 
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
          stage.setTitle("Hospital System");
          stage.setScene(scene);
          stage.show();
  
          /*
         Alert incorrect = new Alert(AlertType.INFORMATION);
		incorrect.setTitle("Failed login");
		incorrect.setHeaderText(null);
		incorrect.setContentText("Incorrect login-credentials!");
		incorrect.showAndWait();
    	
         
          */
          
          
          
    }


}
