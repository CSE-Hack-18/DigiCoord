import java.util.ArrayList;

public class Room {
	
    private int nr;
	public int capacity;
	public ArrayList<Patient> patientArray;
	private String status;
	
	public Room(int capacity, ArrayList<Patient> patientArray,int nr,String status) {
		
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

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( String status) {
		this.status = status;
	}
	
	
	
	

}
