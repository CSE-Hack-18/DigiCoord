import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


import application.Customer;


public class Database implements AutoCloseable {
	static final String dbUrl = "jdbc:sqlite:./sqlite/db/digiCoord.db";
	final String EOL = System.lineSeparator();
	static Connection conn;
	Statement stmt;
	public Database() throws SQLException { 
		createDigiCoordDatabase();
		stmt = conn.createStatement();
		createTables();

	}
	public void createDigiCoordDatabase() throws SQLException {
		File dir = new File("./sqlite/db");

		dir.mkdirs();
		conn = DriverManager.getConnection(dbUrl);

	}
	public void createTables() throws SQLException {
		createUnitTable();
		createEmployeeTable();
		createPatientTable();
		createRoomTable();
		createRoomOccupancyTable();
		createAdminTable();
	}
	public void createUnitTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS unit  (" + EOL + 
				"unit_id INTEGER," + EOL + 
				"type VARCHAR," + EOL + 
				"floor INTEGER," + EOL + 
				"PRIMARY KEY (unit_id)," + EOL + 
				"CHECK (floor >= 0 OR floor <= 5));";
		PreparedExecute(sql);
	}
	public void createEmployeeTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS employee ( " + EOL + 
				"name VARCHAR," + EOL + 
				"ssn VARCHAR," + EOL + 
				"type VARCHAR," + EOL + 
				"unit INTEGER," + EOL + 
				"experience INTEGER," + EOL + 
				"PRIMARY KEY (ssn)," + EOL + 
				"FOREIGN KEY (unit) REFERENCES unit(unit_id)," + EOL + 
				"CHECK (type = 'nurse' OR type = 'assist.nurse' OR type = 'doctor' ));";
		PreparedExecute(sql);
	}
	public void createPatientTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS patient ( " + EOL + 
				"name VARCHAR," + EOL + 
				"ssn VARCAHR," + EOL + 
				"reg_time DATE," + EOL + 
				"prio INTEGER," + EOL +
				"destination INTEGER," + EOL +
				"FOREIGN KEY (destination) REFERENCES unit(unit_id)," + EOL +
				"PRIMARY KEY (ssn) );";
		PreparedExecute(sql);
	}
	public void createRoomTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS room ( " + EOL + 
				"number INTEGER, " + EOL + 
				"unit INTEGER," + EOL + 
				"beds INTEGER," + EOL + 
				"cleaning BOOLEAN," + EOL + 
				"PRIMARY KEY (number, unit)," + EOL + 
				"FOREIGN KEY (unit) REFERENCES unit(unit_id));";
		PreparedExecute(sql);
	}
	public void createRoomOccupancyTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS room_occupancy (" + EOL + 
				"p_ssn INTEGER," + EOL + 
				"roomNr INTEGER," + EOL + 
				"unit INTEGER," + EOL + 
				"PRIMARY KEY( p_ssn, roomNr, unit)," + EOL + 
				"FOREIGN KEY (p_ssn) REFERENCES patient(ssn)," + EOL + 
				"FOREIGN KEY (roomNr, unit) REFERENCES room(number, unit));";
		PreparedExecute(sql);
	}
	public void createAdminTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS admin (" + EOL + 
				"ssn VARCHAR," + EOL + 
				"password VARCHAR," + EOL + 
				"FOREIGN KEY (ssn) REFERENCES employee(ssn));";
		PreparedExecute(sql);
	}
	public Unit getUnit(int unit_id) throws SQLException {
		String getUnit = "SELECT * FROM unit WHERE unit_id = ?";
		ResultSet unitSet = PreparedQuery(getUnit, unit_id);
		Unit result = rsToUnit(unitSet);
		unitSet.close();
		return result;
	}
	public Unit rsToUnit(ResultSet unitSet) throws SQLException {
		String type;
		int floor, unit_id;
		
		type = unitSet.getString("type");
		floor = unitSet.getInt("floor");
		unit_id = unitSet.getInt("unit_id");
		
		Unit result = new Unit(type, floor, unit_id);
		
		return result;
	}
	public ArrayList<Staff> getStaffByUnit(int unit_id) throws SQLException {
		String getStaff = "SELECT * FROM employee WHERE unit = ?";
		ResultSet staffSet = PreparedQuery(getStaff, unit_id);
		ArrayList<Staff> result = rsToStaffArray(staffSet);
		return result;
		
	}
	public ArrayList<Staff> rsToStaffArray(ResultSet staffSet) throws SQLException {
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		String name, type;
		int ssn, experience;
		
		while(staffSet.next()) {
			
			name = staffSet.getString("name");
			type = staffSet.getString("type");
			ssn = staffSet.getInt("ssn");
			experience = staffSet.getInt("experience");
			Staff temp = new Staff(name, type, ssn, experience);
			staffList.add(temp);
		}
		staffSet.close();
		return staffList;
		
	}
	public ArrayList<Patient> getPatientsByUnit(int unit_id) throws SQLException {
		String getPatients = "SELECT name,ssn,id,prio,room_number FROM patient as p INNER JOIN room_occupancy as r "
				+ "ON p.id = r.patient_id WHERE r.department_id = ?";
		ResultSet patientSet = PreparedQuery(getPatients, unit_id);
		ArrayList<Patient> result = rsToPatientWithRoomArray(patientSet);
		return result;
	}
	public ArrayList<Patient> rsToPatientWithRoomArray(ResultSet patientSet) throws SQLException {
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		String name;
		int ssn, prio, roomNr;
		
		while(patientSet.next()) {
			
			name = patientSet.getString("name");
			ssn = patientSet.getInt("ssn");
			prio = patientSet.getInt("prio");
			roomNr = patientSet.getInt("room_number");
			Patient temp = new Patient(name, ssn, prio, roomNr);
			patientList.add(temp);
		}
		patientSet.close();
		return patientList;
		
	}
	public ArrayList<Room> getRoomsByUnit(int unit_id) throws SQLException {
		String getRooms = "SELECT number, bed, status FROM room WHERE unit = ?";
		ResultSet roomSet = PreparedQuery(getRooms, unit_id);
		ArrayList<Room> result = rsToRoomArray(roomSet);
		return result;
	}
	public ArrayList<Room> rsToRoomArray(ResultSet roomSet) throws SQLException {
		ArrayList<Room> roomList = new ArrayList<Room>();
		boolean status;
		int number, capacity;
		
		while(roomSet.next()) {
			
			status = roomSet.getBoolean("status");
			number = roomSet.getInt("ssn");
			capacity = roomSet.getInt("prio");			
			Room temp = new Room(number, capacity, status);
			roomList.add(temp);
		}
		roomSet.close();
		return roomList;
	}
	public ArrayList<Patient> getWaitingPatients() throws SQLException {
		String getPatients = "SELECT * FROM patient EXCEPT "
				+ "SELECT p.name, p.ssn, p.reg_time, p.prio, p.destination "
				+ "FROM patient as p \n" + 
				"INNER JOIN room_occupancy as r WHERE p.ssn = r.p_ssn;";
		ResultSet patientSet = PreparedQuery(getPatients);
		ArrayList<Patient> result = rsToPatientWoRoomArray(patientSet);
		
		return result;
	}
	public ArrayList<Patient> rsToPatientWoRoomArray(ResultSet patientSet) throws SQLException {
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		String name, destination;
		int ssn, prio, reg_time;
		
		while(patientSet.next()) {
			
			name = patientSet.getString("name");
			ssn = patientSet.getInt("ssn");
			prio = patientSet.getInt("prio");
			reg_time = patientSet.getInt("reg_time");
			destination = patientSet.getString("destination");
			Patient temp = new Patient(name, ssn, prio, destination, reg_time);
			patientList.add(temp);
		}
		patientSet.close();
		return patientList;
		
	}
	public ResultSet PreparedQuery(String query, Object...objects) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(query);
		for(int i = 0; i < objects.length; i++) {
			Object obj = objects[i];
			String check = obj.getClass().getSimpleName();
			switch(check) {
			case "Integer":
				pstmt.setInt(i+1, (int)obj);
				break;
			case "Double":
				pstmt.setDouble(i+1, (double)obj);
				break;
			case "String":
				pstmt.setString(i+1, (String)obj);
				break;
			case "Long":
				pstmt.setLong(i+1, (Long)obj);
				break;
			default:
				System.out.println("Magic type, no idea!");
				break;
			}
		}
		ResultSet result = pstmt.executeQuery();
		return result;	
	}
	public void PreparedUpdate(String update, Object...objects) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(update);
		for(int i = 0; i < objects.length; i++) {
			Object obj = objects[i];
			String check = obj.getClass().getSimpleName();
			switch(check) {
			case "Integer":
				pstmt.setInt(i+1, (int)obj);
				break;
			case "Double":
				pstmt.setDouble(i+1, (double)obj);
				break;
			case "String":
				pstmt.setString(i+1, (String)obj);
				break;
			case "Long":
				pstmt.setLong(i+1, (Long)obj);
				break;
			default:
				System.out.println("Magic type, no idea!");
				break;
			}
		}
		pstmt.executeUpdate();
		pstmt.close();	
	}
	public void PreparedExecute(String sql) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.execute();
		pstmt.close();
	}
	@Override
	
	public void close() throws Exception {
		conn.close();
	}

	public ArrayList<Patient> getERWaitingList() throws SQLException {

		String sql = "SELECT * FROM patient";
		ResultSet customerSet = PreparedQuery(sql);
		ArrayList<Patient> customerArray = rsToPatientWoRoomArray(customerSet);
		return customerArray;
		
		
}
	public Patient[] rsToPatientArrayPlus(ResultSet customerSet) throws SQLException {
		ArrayList<Patient> patientList= new ArrayList<Patient>();
		String name, sSn, destination;
		int  prio;
		long startTime;
		while(customerSet.next()) {
			name = customerSet.getString("name");
			prio = customerSet.getInt("prio");
			destination = customerSet.getString("destination");
			startTime = customerSet.getLong("reg_time");
			
			Patient temp = new Patient( name, sSn, prio, destination, startTime);
			patientList.add(temp);	
		}
		customerSet.close();
		Patient[] patientArray = patientList.toArray(new Patient[patientList.size()]);
		return patientArray;
	}
	
	public ArrayList<Room> getOccupiedRooms( ) throws SQLException {
		ArrayList<Room> roomList = new ArrayList<Room>();
		String getStaff = "SELECT * FROM room_occupancy WHERE roomNr = 'NULL';";
		ResultSet roomOccupied = PreparedQuery(getStaff);
		ArrayList<Room> customerArray = rsToOccupiedRooms(roomOccupied);
		return customerArray;
		
	
	}
	
	public ArrayList<Room> rsToOccupiedRooms(ResultSet roomOccupied) throws SQLException {
		ArrayList<Room> roomList= new ArrayList<Room>();
		int roomNr;
		while(roomOccupied.next()) {
			roomNr = roomOccupied.getInt("roomNr");
		
			Room temp = new Room (roomNr);
			roomList.add(temp);
		}
		roomOccupied.close();
			return roomList;
		
	}
	
	

	public void addPatientToRoom(int i, int id, int nr) {
		// TODO Auto-generated method stub
		
	}
}


