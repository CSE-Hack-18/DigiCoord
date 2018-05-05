

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class frontController implements Initializable {

    @FXML
    private TabPane deptTab;
	@FXML
	private ToggleGroup floor;
	@FXML
    private Button changeRoomManually, addRoomManually, testUpdate;
	
    @FXML
    private Rectangle room101, room102, room103;
    
    @FXML
    private TextArea roomInfo;
    
    @FXML
    private Tab ER;
    
   


 String roomStatus = "vacant";
    
   @FXML void testUpdate(ActionEvent event) {
	   
	   if (roomStatus == "vacant") {
	   room101.setFill(Color.LIMEGREEN);
	   room103.setFill(Color.LIMEGREEN);
	   roomStatus = "occupied";
	   
	   }
	   else if (roomStatus == "occupied") {
		   room101.setFill(Color.RED);
		   room103.setFill(Color.YELLOW);
		   roomStatus = "vacant";
	   }
   }
	
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
	
	public void  timer ()   {
	
		System.out.println("hej");
		
	}
	public void initialize(URL location, ResourceBundle resources) {
	

		    Timer timer = new Timer();
		    
		    timer.schedule(new TimerTask() {
		    	  @Override
		    	  public void run() {
		    	    System.out.print("hej");
		    	  }
		    	}, 15*1000);
        
		    timer.scheduleAtFixedRate(new TimerTask() {
		    	  @Override
		    	  public void run() {
		    		  System.out.println("hej");
		    	    // Your database code here
		    	  }
		    	}, 15*1000, 15*1000); 
	}
}
