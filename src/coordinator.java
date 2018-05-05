
import java.util.*;

public class coordinator {
	Unit unit;
	
	public ArrayList<Patient> queque;
	
	

	public coordinator() {
		
	}
	
	public void enquequePatient(String name,String sSn,int prio, String destination,long startTime) {
		
		 Patient p = new Patient(name,sSn,prio,destination,startTime);
		 queque.add(p);
		 Collections.sort(queque);
	}
	
	public void placePatient() {
		for(int i = 0; i < queque.size(); i++) {
			 switch (queque.get(i).getDestination()) {
			 case "ER":
				 unit.addPatient(queque.get(i));
				 
				 break;
			 case "ICU":
				 unit.addPatient(queque.get(i));
				 break;
			
			 case "Oncology":
				 unit.addPatient(queque.get(i));
				 break;
			 case "XRAY":
				 unit.addPatient(queque.get(i));
				 break;
			 case "OR":
				 unit.addPatient(queque.get(i));
			 break;
			 }
			
			
					
		}
		
		
		
			}
			
			
			
			


}
