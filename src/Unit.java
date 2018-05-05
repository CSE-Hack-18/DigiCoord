import java.sql.SQLException;
import java.util.ArrayList;

public class Unit {

	public String type;
	private int id;
	private int floor;

	public ArrayList<Room> rooms;

	public ArrayList<Staff> staffs;


	public Unit(String type, int id, int floor, ArrayList<Room> rooms, ArrayList<Staff> staffs) {

<<<<<<< HEAD
   public Unit(String type2, int floor2, int unit_id) {
		// TODO Auto-generated constructor stub
	}

   public double workLoad() {
	   int count=0;
	   for(int i = 0; i < rooms.size(); i++) {
		   count += rooms.get(i).patientArray.size();
	   }
	   return  (double)(staffs.size() / count);
   }
   
   
public void addPatient(Patient p) {
	   for(int i = 0; i < rooms.size();) {
	   
		   if( rooms.get(i).capacity  > rooms.get(i).patientArray.size() ) {
		   
		   rooms.get(i).patientArray.add(p);
		   try(Database db = new Database()) {
			   db.addPatientToRoom(p.getSSN(), this.id, rooms.get(i).getNr());
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	   }
		   else {
			   i++;
		   }
	   }
   }
	
	public boolean hasAnyAvailableRooms() {
		
		for(int i = 0; i < rooms.size(); i++) {
			if( rooms.get(i).available()) {
				return true;
			}
		}
		return false;
	}
	public int countAvailableBeds() {
		int beds= 0;
		for(int i = 0; i < rooms.size(); i++) {
			beds += rooms.get(i).freeBeds();
		}
		return beds;
	}
	public int singleRooms() {
		
		int beds = 0;
		for(int i = 0; i < rooms.size(); i++) {
			if(rooms.get(i).isSingleRoom()) {
				beds += rooms.get(i).freeBeds();
			}
=======
		this.type = type;
		this.id = id;
		this.floor = floor;
	}

	public Unit(String type2, int floor2, int unit_id) {
		this.type = type2;
		this.floor = floor2;
		this.id = unit_id;
	}

	public void addPatient(Patient p) {
		for(int i = 0; i < rooms.size();) {

			if( rooms.get(i).capacity  > rooms.get(i).patientArray.size() ) {

				rooms.get(i).patientArray.add(p);
				try(Database db = new Database()) {
					db.addPatientToRoom(p.getSSN(), this.id, rooms.get(i).getNr());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else {
				i++;
			}
		}
	}








>>>>>>> 5aa4e8cd1a28bcbf861a7398857768947f4d4580

		}
		return beds;
		
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFloor() {
		return floor;
	}


	public void setFloor(int floor) {
		this.floor = floor;
	}










}
