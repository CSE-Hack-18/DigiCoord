import java.util.*;

public class Patient implements Comparable<Patient>  {

	private String name;
	private int SSN;
	private int prio;
	private long startTime;
	private String destination;
	private int roomNr;

	public Patient(String name, int sSN, int prio, String destination, long startTime) {

		this.name = name;
		this.SSN = sSN;
		this.prio = prio;
		this.destination = destination;
		this.startTime = startTime;
	}
	public Patient(String name2, int ssn2, int prio2, int roomNr) {
		this.name = name2;
		this.SSN = ssn2;
		this.prio = prio2;
		this.roomNr = roomNr;
	}

	public Patient(int reg_time, String name2, int ssn2, int prio2) {
		this.name = name2;
		this.SSN = ssn2;
		this.prio = prio2;
		this.startTime = reg_time;
	}
	public String cause;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int sSN) {
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
	public int getRoomNr() {
		return roomNr;
	}
	public void setRoomnr(int roomnr) {
		this.roomNr = roomnr;
	}
}
