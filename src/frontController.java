

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
	private coordinator DigiCoord;
    @FXML
    private TabPane deptTab;
	@FXML
	private ToggleGroup floor;
	@FXML
    private Button changeRoomManually, addRoomManually, testUpdate;
	private Rectangle b101,b102,b103,b104,b105,b106,b107,b108,b109,b110;
	
    @FXML
    private Rectangle[] unit1Boxes = new Rectangle[] {null,b101,b102,b103,b104,b105,b106,b107,b108,b109,b110};
   // private Rectangle[] unit2Boxes = new Rectangle[] {null,b201,b202,b203,b204,b205,b206,b207,b208,b209,b120};
   // private Rectangle[] unit3Boxes = new Rectangle[] {null,b301,b302,b303,b304,b305,b306,b307,b308,b309,b130};
    
    @FXML
    private TextArea roomInfo;
    
    @FXML
    private Tab ER;
   


/*    String roomStatus = "vacant";
    
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
   }*/
	public void updateUnits() {
		updateUnit1Boxes();
		
	}
	public void updateUnit1Boxes() {
		int[] check = DigiCoord.getAvailableRooms(1);
		
		for(int i = 0; i < unit1Boxes.length; i++) {
			if(check[i] == 0) {
				unit1Boxes[i].setFill(Color.RED);
			}
			else {
				unit1Boxes[i].setFill(Color.LIMEGREEN);
			}
		}
	}	
	/*public void updateUnit2Boxes() {
		int[] check = DigiCoord.getAvailableRooms(2);
		
		for(int i = 0; i < unit2Boxes.length; i++) {
			if(check[i] == 0) {
				unit2Boxes[i].setFill(Color.RED);
			}
			else {
				unit2Boxes[i].setFill(Color.LIMEGREEN);
			}
		}
	}
	public void updateUnit3Boxes() {
		int[] check = DigiCoord.getAvailableRooms(3);
		
		for(int i = 0; i < unit3Boxes.length; i++) {
			if(check[i] == 0) {
				unit3Boxes[i].setFill(Color.RED);
			}
			else {
				unit3Boxes[i].setFill(Color.LIMEGREEN);
			}
		}
	}*/
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
			
			DigiCoord = new coordinator();
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
		    		  updateUnits();
		    	    // Your database code here
		    	  }
		    	}, 15*1000, 15*1000); 
	}
}
