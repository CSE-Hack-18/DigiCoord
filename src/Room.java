import java.util.ArrayList;

public class Room {

	private int nr;
	public int capacity;
	public ArrayList<Patient> patientArray;
	boolean status;
	
	public Room(int capacity, ArrayList<Patient> patientArray,int nr,boolean status) {
		
		this.nr = nr;
		this.status = status;
		this.capacity = capacity;
		this.patientArray = patientArray;
	}



	public Room(int nr) {
		this.nr = nr;
	}
	
	public boolean hasPatient(int ssn) {
		
		if(patientArray.get(0).getSSN() == ssn) {
			return true;
		}
		return false;
	}
	
	public void removePatient() {
		patientArray.remove(0);
	}

	public Room(int number, int capacity2, boolean status) {
		this.nr = number;
		this.capacity = capacity2;
		this.status = status;
		this.patientArray = new ArrayList<Patient>();
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus( boolean status) {
		this.status = status;
	}
	public boolean available()  {
		return capacity > patientArray.size();
	}
	public int freeBeds() {
		return capacity - patientArray.size();
	}
	public boolean isSingleRoom() {
		return capacity == 1;
	}


}
