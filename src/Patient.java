
public class Patient {
	
	private String name;
	private String SSN;
	private int prio;
	
	public Patient(String name, String sSN, int prio, String cause) {
		
		this.name = name;
		SSN = sSN;
		this.prio = prio;
		this.cause = cause;
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
		SSN = sSN;
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	

}
