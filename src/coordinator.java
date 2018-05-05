
import java.sql.SQLException;
import java.util.*;

public class coordinator {
	Unit[] units = new Unit[6];

	public ArrayList<Patient> queque;


	public coordinator() {
		for(int i = 1; i <= 3; i++) {
			try (Database db = new Database()) {
				Unit tempUnit = db.getUnit(i);
				ArrayList<Staff> tempStaff = db.getStaffByUnit(i);
				ArrayList<Room> tempRoom = db.getRoomsByUnit(i);
				ArrayList<Patient> tempPatient = db.getPatientsByUnit(i);
				
				tempUnit.setStaffs(tempStaff);
				tempUnit.setRooms(tempRoom);
				tempUnit.addAllPatients(tempPatient);
				units[i] = tempUnit;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void enquequePatient(String name,int sSn,int prio, String destination,long startTime) {

		Patient p = new Patient(name,sSn,prio,destination,startTime);
		queque.add(p);
		Collections.sort(queque);
	}


	public void placePatient() {
		for(int i = 0; i < queque.size(); i++) {
			String destination = queque.get(i).getDestination();
			for (int j = 1; j < units.length; j++) {
				if (units[j].getType() == destination) {
					if(units[j].hasAnyAvailableRooms()) {
						units[j].addPatient(queque.get(i));
					}
				}
			}
		}
	}
}
