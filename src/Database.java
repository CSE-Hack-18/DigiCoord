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
		String sql = "CREATE TABLE unit (" + EOL + 
				"unit_id INTEGER," + EOL + 
				"name VARCHAR," + EOL + 
				"floor INTEGER," + EOL + 
				"PRIMARY KEY (id)," + EOL + 
				"CHECK (floor >= 0 OR floor <= 5));";
		PreparedExecute(sql);
	}
	public void createEmployeeTable() throws SQLException {
		String sql = "CREATE TABLE employee ( " + EOL + 
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
		String sql = "CREATE TABLE patient ( " + EOL + 
				"name VARCHAR," + EOL + 
				"ssn VARCAHR," + EOL + 
				"id INTEGER," + EOL + 
				"reg_time DATE," + EOL + 
				"destination INTEGER," + EOL +
				"FOREIGN KEY (destination) REFERENCES unit(unit_id)," + EOL +
				"PRIMARY KEY (ssn));";
		PreparedExecute(sql);
	}
	public void createRoomTable() throws SQLException {
		String sql = "CREATE TABLE room ( " + EOL + 
				"number INTEGER, " + EOL + 
				"unit INTEGER," + EOL + 
				"beds INTEGER," + EOL + 
				"op_room BOOLEAN," + EOL + 
				"PRIMARY KEY (number, unit)" + EOL + 
				"FOREIGN KEY (unit) REFERENCES unit(unit_id));";
		PreparedExecute(sql);
	}
	public void createRoomOccupancyTable() throws SQLException {
		String sql = "CREATE TABLE room_occupancy (" + EOL + 
				"patient_id INTEGER," + EOL + 
				"roomNr INTEGER," + EOL + 
				"unit INTEGER," + EOL + 
				"PK ( patient_id, roomNr, unit)," + EOL + 
				"FK (patient_id) REFERENCES patient(id)," + EOL + 
				"FK (roomNr, unit) REFERENCES room(number, unit));";
		PreparedExecute(sql);
	}
	public void createAdminTable() throws SQLException {
		String sql = "CREATE TABLE admin (" + EOL + 
				"ssn VARCHAR," + EOL + 
				"password VARCHAR," + EOL + 
				"FOREIGN KEY (ssn) REFERENCES employee(ssn));";
		PreparedExecute(sql);
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
	public Patient[] getERWaitingList(String dept) throws SQLException {

		String sql = "SELECT * FROM patient";
		ResultSet customerSet = PreparedQuery(sql);
		Patient[] customerArray = rsToPatientArrayPlus(customerSet);
		return customerArray;
}
	public Patient[] rsToPatientArrayPlus(ResultSet customerSet) throws SQLException {
		ArrayList<Patient> patientList= new ArrayList<Patient>();
		String name, sSn, destination;
		int  prio;
		long startTime;
		while(customerSet.next()) {
			name = customerSet.getString("name");
			sSn = customerSet.getString("sSN");
			prio = customerSet.getInt("prio");
			destination = customerSet.getString("destination");
			startTime = customerSet.getLong("startTime");
			
			Patient temp = new Patient( name, sSn, prio, destination, startTime);
			patientList.add(temp);	
		}
		customerSet.close();
		Patient[] patientArray = patientList.toArray(new Patient[patientList.size()]);
		return patientArray;
	}
}
