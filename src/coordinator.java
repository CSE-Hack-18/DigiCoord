import java.util.ArrayList;

public class coordinator {
	
	public ArrayList<Patient> queque;
	

	public coordinator() {
		
	}
	
	public void enquequePatient(String name,String sSn,int prio, String cause) {
		
		 Patient p = new Patient(name,sSn,prio,cause);
		 queque.add(p);
		
	}

}
