

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




/*	@FXML
	private TableView<Patient>  ERWaitingList, ORWaitingList; //roomInfo,
	@FXML
	private TableColumn<Patient, String> ERwaitingListPatient, ERwaitingListNote; //ORwaitingListPatient, ORwaitingListNote ;
	@FXML private TableColumn<Patient, Integer> ERwaitingListPrio, vacantRooms, vacantSharedRooms, totalPatients;// ORwaitingListPrio;
	@FXML private TableColumn<Patient, Long> ERwaitingListTime; //ORwaitingListTime;
*/


	@FXML
	private TableView<Patient>  ERWaitingList, ORWaitingList, oncWaitingList, allWaitingList; //roomInfo,
	@FXML
	private TableColumn<Patient, String> ERwaitingListPatient, ERwaitingListNote, ORwaitingListPatient, ORwaitingListNote, oncPatient,	    oncNote;
	@FXML private TableColumn<Patient, Integer> ERwaitingListPrio, vacantRooms, vacantSharedRooms, totalPatients, ORwaitingListPrio, oncPrio;
	@FXML private TableColumn<Patient, Long> ERwaitingListTime, ORwaitingListTime, oncWaited;

	private coordinator DigiCoord;

	@FXML
	private TabPane deptTab;
	@FXML
	private ToggleGroup floor;
	@FXML
	private Button changeRoomManually, addRoomManually, testUpdate;
	@FXML
	private Rectangle b101,b102,b103,b104,b105,b106,b107,b108,b109,b110,
	b201, b202, b203, b204, b205, b206, b207, b208, b209, b210,
	b301, b302, b303, b304, b305, b306, b307, b308, b309, b310;



	private ArrayList<Rectangle> unit1= new ArrayList<Rectangle>();
	private ArrayList<Rectangle> unit2= new ArrayList<Rectangle>();
	private ArrayList<Rectangle> unit3= new ArrayList<Rectangle>();



	@FXML
	private Tab ER;



	String roomStatus = "vacant";



	public void addUnit1() {
		unit1.add(b101);
		unit1.add(b102);
		unit1.add(b103);
		unit1.add(b104);
		unit1.add(b105);
		unit1.add(b106);
		unit1.add(b107);
		unit1.add(b108);
		unit1.add(b109);
		unit1.add(b110);


	}
	public void addUnit2() {
		unit2.add(b201);
		unit2.add(b202);
		unit2.add(b203);
		unit2.add(b204);
		unit2.add(b205);
		unit2.add(b206);
		unit2.add(b207);
		unit2.add(b208);
		unit2.add(b209);
		unit2.add(b210);



	}
	public void addUnit3() {

		unit3.add(b301);
		unit3.add(b302);
		unit3.add(b303);
		unit3.add(b304);
		unit3.add(b305);
		unit3.add(b306);
		unit3.add(b307);
		unit3.add(b308);
		unit3.add(b309);
		unit3.add(b310);

	}




	@FXML void testUpdate(ActionEvent event) {

		if (roomStatus == "vacant") {
			b101.setFill(Color.LIMEGREEN);
			b103.setFill(Color.LIMEGREEN);
			roomStatus = "occupied";

		} 
		else if (roomStatus == "occupied") {
			b102.setFill(Color.RED);
			b105.setFill(Color.YELLOW);
			roomStatus = "vacant";



		}

	} 
	public void updateUnits() {
		updateUnit1Boxes();
		updateUnit2Boxes();
		updateUnit3Boxes();
	}
	public void updateUnit1Boxes() {
		int[] check = DigiCoord.getAvailableRooms(1);
		for(int i = 1; i < unit1.size(); i++) {
			if(check[i] == 0) {
				unit1.get(i).setFill(Color.RED);
			}
			else {
				unit1.get(i).setFill(Color.LIMEGREEN);
			}
		}
	}	

	public void updateUnit2Boxes() {
		int[] check = DigiCoord.getAvailableRooms(2);

		for(int i = 1; i < unit2.size(); i++) {
			if(check[i] == 0) {
				unit2.get(i).setFill(Color.RED);
			}
			else {
				unit2.get(i).setFill(Color.LIMEGREEN);
			}
		}
	}
	public void updateUnit3Boxes() {
		int[] check = DigiCoord.getAvailableRooms(3);	
		for(int i = 1; i < unit3.size(); i++) {
			if(check[i] == 0) {
				unit3.get(i).setFill(Color.RED);
			}
			else {
				unit3.get(i).setFill(Color.LIMEGREEN);
			}
		}
	}
	@FXML
	void changeRoomManually(ActionEvent event) {

	}

	@FXML
	void addRoomManually(ActionEvent event) {
		ChoiceDialog<Integer> dialog = new ChoiceDialog<>(1);
		dialog.setTitle("");
		dialog.setHeaderText(null);
		dialog.setContentText("");

		Optional<Integer> result = dialog.showAndWait();
	}

	public void setWaitingLists() throws Exception {
		ObservableList<Patient> patientList = FXCollections.observableArrayList();
		ArrayList<Patient> allPatient = new ArrayList<Patient>();
		try(Database db = new Database()) {
			allPatient = db.getWaitingPatients();
			patientList.addAll(allPatient);
		}
		ERWaitingList.setItems(getERWaitingList(allPatient));
		ORWaitingList.setItems(getORWaitingList(allPatient));
		oncWaitingList.setItems(getERWaitingList(allPatient));
		allWaitingList.setItems(patientList);
	}
	public ObservableList<Patient> getERWaitingList(ArrayList<Patient> allPatients) throws Exception {
		ObservableList<Patient> patientListER = FXCollections.observableArrayList();
		for(int i = 0; i < allPatients.size(); i++) {
			if(allPatients.get(i).getDestination().equals("ER")) {
				patientListER.add(allPatients.get(i));
			}
		}
		return patientListER;
	}
	public ObservableList<Patient> getOncologyWaitingList(ArrayList<Patient> allPatients) throws Exception {
		ObservableList<Patient> patientListOnc = FXCollections.observableArrayList();
		for(int i = 0; i < allPatients.size(); i++) {
			if(allPatients.get(i).getDestination().equals("onc")) {
				patientListOnc.add(allPatients.get(i));
			}
		}
		return patientListOnc;
	}
	public ObservableList<Patient> getORWaitingList(ArrayList<Patient> allPatients) throws Exception {
		ObservableList<Patient> patientListOR = FXCollections.observableArrayList();
		for(int i = 0; i < allPatients.size(); i++) {
			if(allPatients.get(i).getDestination().equals("OR")) {
				patientListOR.add(allPatients.get(i));
			}
		}
		return patientListOR;
	}

	public void initialize(URL location, ResourceBundle resources) {

		addUnit1();
		addUnit2();
		addUnit3();
		
		ERwaitingListPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
		ERwaitingListPrio.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("prio"));
		ERwaitingListTime.setCellValueFactory(new PropertyValueFactory<Patient, Long>("startTime"));

		ORwaitingListPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
		ORwaitingListPrio.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("prio"));
		ORwaitingListTime.setCellValueFactory(new PropertyValueFactory<Patient, Long>("startTime"));

		oncPatient.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
		oncPrio.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("prio"));
		oncWaited.setCellValueFactory(new PropertyValueFactory<Patient, Long>("startTime"));

		System.out.println("hej");
		DigiCoord = new coordinator();

		Timer timer = new Timer();

		try {
			setWaitingLists();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.print("hej");


		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					setWaitingLists();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("hej");
				updateUnits();

				Timer timer = new Timer();
			}
		}, 15*1000, 15*1000); 
	}
}
