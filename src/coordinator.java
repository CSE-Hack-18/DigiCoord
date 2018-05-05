
import java.util.*;

public class coordinator {
	
	public ArrayList<Patient> queque;
	

	public coordinator() {
		
	}
	
	public void enquequePatient(String name,String sSn,int prio, String destination,long startTime) {
		
		 Patient p = new Patient(name,sSn,prio,destination,startTime);
		 queque.add(p);
		 Collections.sort(queque);
	}
	
	public void placePatient() {
		
		
		
		
			}
			
			
			
			


}
