import java.util.ArrayList;

public class Room {
	
    private int nr;
	public int capacity;
	public ArrayList<Patient> patientArray;
	boolean status;
	public Room(int capacity, ArrayList<Patient> patientArray) {
		
		this.capacity = capacity;
		this.patientArray = patientArray;
	}
	
	public Room(int number, int capacity2, boolean status) {
		this.nr = number;
		this.capacity = capacity2;
		this.status = status;;
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

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}
	
	
	
	

}
