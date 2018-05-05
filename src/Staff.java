
public class Staff {
	
	private String name;
	private String SSN;
	private String type;
	private String experience;
	
	

	public Staff(String name,String type, String sSN, String experience) {
		
		this.name = name;
		this.type = type;
		SSN = sSN;
		this.experience = experience;
	}


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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}



	public Staff() {
		
	}


	public String getExperience() {
		return experience;
	}


	public void setExperience(String experience) {
		this.experience = experience;
	}

}
