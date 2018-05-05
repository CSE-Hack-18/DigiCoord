
import java.util.*;

public class coordinator {
	Unit[] units = new Unit[6];
	
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
			String destination = queque.get(i).getDestination();
			for (int j = 1; j < units.length; j++) {
				if (units[j].getType() == destination) {
					if(units[j].roomAvailable()) {
						
					
					units[j].addPatient(queque.get(i));
					}
				}
			}
			
				
			
			
		}


}
}
