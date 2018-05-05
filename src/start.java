

import java.io.IOException;
import java.awt.Button;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class start extends Application  {

	public void start(Stage primaryStage) throws IOException {
		
			Parent root = FXMLLoader.load(getClass().getResource("loginStart.fxml"));
			Scene scene = new Scene(root,556,320);
			
		
			primaryStage.setTitle("Hospital system");
			primaryStage.setScene(scene);
			primaryStage.show();
	
	}
	
	public static void main(String[] args) {
		
		launch(args);

}

	
}

