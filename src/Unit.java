import java.util.ArrayList;

public class Unit {
	
public String type;
	
	public ArrayList<Room> rooms;
	
	public ArrayList<Staff> staffs;
	
	
	public Unit(String type, ArrayList<Room> rooms, ArrayList<Staff> staffs) {
		
		this.type = type;
		this.rooms = rooms;
		this.staffs = staffs;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public ArrayList<Room> getRooms() {
		return rooms;
	}


	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}


	public ArrayList<Staff> getStaffs() {
		return staffs;
	}


	public void setStaffs(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}
	
	
	public int countRooms() {
		
		return rooms.size();
	}
	
	public int countStaff() {
		
		return staffs.size();
	}
	
	public void addStaff(Staff s) {
		
		staffs.add(s);
		
	}
	
	public void removeStaff(Staff s) {
		
		staffs.add(s);
		
		
		
	}

	
	
	
	
	
	
	
	

}
