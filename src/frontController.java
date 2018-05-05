

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ToggleGroup;

public class frontController {
	@FXML
	private ToggleGroup floor;
	@FXML
    private Button changeRoomManually, addRoomManually;
	
	
	
	
	
	@FXML
	void changeRoomManually(ActionEvent event) {
		
	}
	
	@FXML
	void addRoomManually(ActionEvent event) {
		ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1);
		dialog.setTitle("Choose the number of weeks");
		dialog.setHeaderText(null);
		dialog.setContentText("Choose the number of weeks to borrow the books: ");
		
		Optional<Integer> result = dialog.showAndWait();
	}
}
