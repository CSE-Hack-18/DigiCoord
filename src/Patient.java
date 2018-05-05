import java.util.*;

public class Patient implements Comparable<Patient>  {
	
	private String name;
	private String SSN;
	private int prio;
	private long startTime;
	private String destination;
	
	public Patient(String name, String sSN, int prio, String destination, long startTime) {
		
		this.name = name;
		this.SSN = sSN;
		this.prio = prio;
		this.destination = destination;
		this.startTime = startTime;
	}
	public String cause;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		this.SSN = sSN;
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}
	
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	

	@Override
	public int compareTo(Patient o) {
	
		if (this.prio > o.prio) {
			return -1;
		} else if (this.prio < o.prio) {
			return 1;
		} else if (this.prio == o.prio) {
			if (this.startTime > o.startTime) {
				return -1;
			} else if (this.startTime < o.startTime) {
				return 1;
			}

			else if (this.startTime == o.startTime) {
				return 0;
			}

		}
		return 0;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
