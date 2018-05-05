

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class frontController implements Initializable {
Database db;
	
	   @FXML
	    private TableView<Patient>  ERWaitingList, ORWaitingList; //roomInfo,
	   @FXML
	    private TableColumn<Patient, String> ERwaitingListPatient, ERwaitingListNote; //ORwaitingListPatient, ORwaitingListNote ;
		@FXML private TableColumn<Patient, Integer> ERwaitingListPrio, vacantRooms, vacantSharedRooms, totalPatients;// ORwaitingListPrio;
		@FXML private TableColumn<Patient, Long> ERwaitingListTime; //ORwaitingListTime;
		
    @FXML
    private TabPane deptTab;
	@FXML
	private ToggleGroup floor;
	@FXML
    private Button changeRoomManually, addRoomManually, testUpdate;
	
    @FXML
    private Rectangle room101, room102, room103;
    

    
    
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
	
	
	  public ObservableList<Patient> getERWaitingList() throws Exception {
			ObservableList<Patient> patientList = FXCollections.observableArrayList();
			try(Database db = new Database()) {
				ArrayList<Patient> allPatient = db.getWaitingPatients();
				patientList.addAll(allPatient);
			}
			return patientList;
		}/*
	  public ObservableList<Patient> getORWaitingList() throws Exception {
			ObservableList<Patient> patientList = FXCollections.observableArrayList();
			try(Database db = new Database()) {
				ArrayList<Patient> allPatient = db.getWaitingPatients();
				patientList.addAll(allPatient);
			}
			return patientList;
		}
	*/
	
	
	
	

	public void initialize(URL location, ResourceBundle resources) {
		
		
			
			
		
		
		
		ERwaitingListPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
		ERwaitingListPrio.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("prio"));
		
		ERwaitingListTime.setCellValueFactory(new PropertyValueFactory<Patient, Long>("startTime"));
		/*
		ORwaitingListPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
		ORwaitingListPrio.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("prio"));
		
		ORwaitingListTime.setCellValueFactory(new PropertyValueFactory<Patient, Long>("startTime"));
		
		*/

		    Timer timer = new Timer();
		    
		    try {
				ERWaitingList.setItems(getERWaitingList());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    		  
		    		  
		    	    System.out.print("hej");
		   
        
		    timer.scheduleAtFixedRate(new TimerTask() {
		    	  @Override
		    	  public void run() {
		    		  try {
						ERWaitingList.setItems(getERWaitingList());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		  System.out.println("hej");
		    	    // Your database code here
		    	  }
		    	}, 15*1000, 15*1000); 
	}
}
