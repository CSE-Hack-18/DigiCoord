import java.util.ArrayList;

public class Room {
	
	
	public int capacity;
	public ArrayList<Patient> patientArray;
	
	public Room(int capacity, ArrayList<Patient> patientArray) {
		
		this.capacity = capacity;
		this.patientArray = patientArray;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	public void accomodatePatient(Patient newPatient) {
		
		patientArray.add(newPatient);
		
		
	}
	
	public void removePatient(Patient patient) {
		patientArray.remove(patient);
			
	}
	
	
	
	

}
