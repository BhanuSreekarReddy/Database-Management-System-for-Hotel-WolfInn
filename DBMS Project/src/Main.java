/*
 * Project Title: Hotel Management System for Wolf Inns
 * Team: E
 * Group Members - Akshit Meghawat, Harshal Gurjar, Bhanu Sreekar Reddy Karumuri, Rasika Pande
 */

import java.sql.*;
import java.util.Scanner;
public class Main {
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/rhpande";
	static Connection connection = null;
	
	// API to maintain billing account. 
	//Input - record to be updated referenced by account number, list of fields to be updated
	private static Boolean updateBillingAccount(int oldAccountNumber, int newAccountNumber, int SSN, String billingAddress, String payMethod, String cardNumber, int hotelCredit) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Billing_Account SET account_number = ?, SSN = ?, billing_address = ?, pay_method = ?, card_number = ?, hotel_credit = ? WHERE account_number = ?;");
			preparedStatement.setInt(1, newAccountNumber);
			preparedStatement.setInt(2, SSN);
			preparedStatement.setString(3, billingAddress);
			preparedStatement.setString(4, payMethod);
			if(cardNumber.equals("NULL"))
				preparedStatement.setNull(5, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(5, new Integer(cardNumber));
			preparedStatement.setInt(6, hotelCredit);
			preparedStatement.setInt(7, oldAccountNumber);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true if record was updated successfully. Else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	// API to update services used during a check in. 
	//Input - record to be updated referenced by the check in id and service name, list of fields to be updated
	private static Boolean updateServiceUsed(int oldCheckInId, String oldServiceName, int newCheckInId, String newServiceName, int quantity) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Services_Used SET check_in_id = ?, service_name = ?, quantity = ? WHERE check_in_id = ? AND service_name = ?;");
			preparedStatement.setInt(1, newCheckInId);
			preparedStatement.setString(2, newServiceName);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setInt(4, oldCheckInId);
			preparedStatement.setString(5, oldServiceName);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Ouput - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to update a service
	//Input - record to be updated referenced by the service name, list of fields to be udpated
	private static Boolean updateService(String oldServiceName, String newServiceName, double rate) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Services SET service_name = ?, rate = ? WHERE service_name = ?;");
			preparedStatement.setString(1, newServiceName);
			preparedStatement.setDouble(2, rate);
			preparedStatement.setString(3, oldServiceName);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to update customer information
	//Input - record to be updated reference by the customer id, list of fields to be updated
	private static Boolean updateCustomer(int oldCustId, int newCustId, String name, 
			long phone, String email, String dob) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET cust_id = ?, cust_name = ?, cust_phone = ?, email = ?, dob = ? WHERE cust_id = ?;");
			preparedStatement.setInt(1, newCustId);
			preparedStatement.setString(2, name);
			preparedStatement.setLong(3, phone);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, dob);
			preparedStatement.setInt(6, oldCustId);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to update staff information
	//Input - record to be updated referenced by the staff id, list of fields to be updated
	private static Boolean updateStaff(int oldStaffId, int newStaffId, String address, String name, 
			long phone, int age, String department, String title, int hotelId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Staff SET staff_id = ?, staff_address = ?, staff_name = ?, staff_phone = ?, age = ?, department = ?, title = ?, hotel_id = ? WHERE staff_id = ?;");
			preparedStatement.setInt(1, newStaffId);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, name);
			preparedStatement.setLong(4, phone);
			preparedStatement.setInt(5, age);
			preparedStatement.setString(6, department);
			preparedStatement.setString(7, title);
			preparedStatement.setInt(8, hotelId);
			preparedStatement.setInt(9, oldStaffId);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to update room information 
	//Input - record to be updated referenced by the room and hotel id, list of fields to  be udpated
	private static Boolean updateRoom(int oldRoomNumber, int oldHotelId, int newRoomNumber, int newHotelId, 
			String roomCategory, int availability) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rooms SET hotel_id = ?, room_number = ?, room_category = ?, availability = ? WHERE hotel_id = ? AND room_number = ?;");
			preparedStatement.setInt(1, newHotelId);
			preparedStatement.setInt(2, newRoomNumber);
			preparedStatement.setString(3, roomCategory);
			preparedStatement.setInt(4, availability);
			preparedStatement.setInt(5, oldHotelId);
			preparedStatement.setInt(6, oldRoomNumber);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true if successful, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to update room category information
	//Input - record to be update referenced by the room category name and hotel id, list of fields to be updated
	private static Boolean updateRoomCategory(String oldRoomCategory, int oldHotelId, String newRoomCategory, int newHotelId, 
			double rate, int occupancy) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Room_Category SET hotel_id = ?, room_category = ?, nightly_rate = ?, max_occupancy = ? WHERE hotel_id = ? AND room_category = ?;");
			preparedStatement.setInt(1, newHotelId);
			preparedStatement.setString(2, newRoomCategory);
			preparedStatement.setDouble(3, rate);
			preparedStatement.setInt(4, occupancy);
			preparedStatement.setInt(5, oldHotelId);
			preparedStatement.setString(6, oldRoomCategory);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to update hotel information
	//Input - record to be updated referenced by the hotel id, list of fields to be updated
	private static Boolean updateHotel(int oldHotelId, int newHotelId, String address, String name, 
			long phone, String city) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Hotel SET hotel_id = ?, hotel_address = ?, hotel_name = ?, hotel_phone = ?, city = ? WHERE hotel_id = ?;");
			preparedStatement.setInt(1, newHotelId);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, name);
			preparedStatement.setLong(4, phone);
			preparedStatement.setString(5, city);
			preparedStatement.setInt(6, oldHotelId);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to process check outs - updates checkout time and total amount owed for a check in id
	//Input - check in id for which check out is to be done, total amount owed by customer, time of check out
	private static Boolean processCheckOut(int checkInId, Double totalAmount, String checkOutTime) {
		PreparedStatement preparedStatement;
		try {
			//Set parameters and execute query
			preparedStatement = connection.prepareStatement("UPDATE Check_In_Information "
					+ "SET total_amount = ?, check_out_time = ? WHERE check_in_id = ?;");
			preparedStatement.setDouble(1, totalAmount);
			preparedStatement.setString(2, checkOutTime);
			preparedStatement.setInt(3, checkInId);
			
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful checkout, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to process check in - inserts a new check in record
	//Input - new check in id to be added along with values for columns start and end dates, start and end times, 
	//number of guests, total amount, account number, room number, hotel id, customer id
	private static Integer processCheckIn(Integer checkInId, String startDate, String endDate, 
			String checkInTime, String checkOutTime, Integer numberOfGuests, String totalAmount, 
			Integer accountNumber, Integer roomNumber, Integer hotelId, Integer custId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Check_In_Information VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, checkInId);
			preparedStatement.setString(2, startDate);
			preparedStatement.setString(3, endDate);
			preparedStatement.setString(4, checkInTime);
			if(checkOutTime.equals("NULL")) 
				preparedStatement.setNull(5, java.sql.Types.CHAR);
			else
				preparedStatement.setString(5, checkOutTime);
			preparedStatement.setInt(6, numberOfGuests);
			if(totalAmount.equals("NULL"))
				preparedStatement.setNull(7, java.sql.Types.DOUBLE);
			else
				preparedStatement.setDouble(7, new Double(totalAmount));
			preparedStatement.setInt(8, accountNumber);
			preparedStatement.setInt(9, roomNumber);
			preparedStatement.setInt(10, hotelId);
			preparedStatement.setInt(11, custId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return id of new record if successful, else null
			return checkInId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to release a room (sets availability of the specified room as 1) 
	//Input - room number and hotel id of room to be released
	private static Boolean releaseRoom(Integer roomNumber, Integer hotelId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rooms SET availability = 1 "
					+ "WHERE room_number = ? AND hotel_id = ? AND availability = 0;");
			preparedStatement.setInt(1, roomNumber);
			preparedStatement.setInt(2, hotelId);
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else 
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to assign a room (sets availability of the specified room as 0) 
	//Input - room number and hotel id of room to be assigned
	private static Boolean assignRoom(int roomNumber, int hotelId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rooms SET availability = 0 "
					+ "WHERE room_number = ? AND hotel_id = ? AND availability = 1;");
			preparedStatement.setInt(1, roomNumber);
			preparedStatement.setInt(2, hotelId);
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			
			//Output - return confirmation true for successful update, else false
			if(result == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to create a new billing account - inserts a new billing account record
	//Input - new account number to be added along with values for columns SSN, billing address, payment method, 
	//card number, and if paying by hotel credit or not
	private static Integer createBillingAccount(int accountNumber, int SSN, String billingAddress, String payMethod, String cardNumber, int hotelCredit) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Billing_Account VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, SSN);
			preparedStatement.setString(3, billingAddress);
			preparedStatement.setString(4, payMethod);
			if(cardNumber.equals("NULL"))
				preparedStatement.setNull(5, java.sql.Types.INTEGER);
			else
				preparedStatement.setInt(5, new Integer(cardNumber));
			preparedStatement.setInt(6, hotelCredit);
			preparedStatement.executeUpdate();
	
			preparedStatement.close();
			
			//Output - returns id of new billing account if successful, else null
			return accountNumber;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	//API to create a new service bill - inserts a new record in Services_Used table
	//Input - new check in id and service name to be added along with values for quantity used of the service
	private static String enterServiceBill(Integer checkInId, String serviceName, Integer quantity) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Services_Used VALUES (?, ?, ?)");
			preparedStatement.setInt(1, checkInId);
			preparedStatement.setString(2, serviceName);
			preparedStatement.setInt(3, quantity);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - return name of the service if successul, else false
			return serviceName;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a new service - inserts a new service record in table Services
	//Input - new service name to be added along with values for rate of service
	private static String enterService(String serviceName, Double rate) {
		try {
			//set autocommit to false so that the transaction will not be committed automatically
			connection.setAutoCommit(false);
			
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Services VALUES (?, ?)");
			preparedStatement.setString(1, serviceName);
			preparedStatement.setDouble(2, rate);
			preparedStatement.executeUpdate();
			
			//Insert a check in order to ensure correctness of rate. 
			if(rate > 0) {
				
				//If rate >= 0, commit the changes to the database
				connection.commit();
				System.out.println("Transaction successful - commit");
				preparedStatement.close();
				
				//reset autocommit to true so that future transactions can be committed automatically
				connection.setAutoCommit(true);
				
				//Output - return service name if successful, else null
				return serviceName;
			}
			else {
				//service rate < 0, hence rollback the changes 
				connection.rollback();
				System.out.println("Transaction failure - rollback");
			}
			preparedStatement.close();
			
			//reset autocommit to true so that future transactions can be committed automatically
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				//Rollback changes in case of any errors and reset autocommit to true
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	//API to delete a customer - removes customer record referenced by its id
	//Input - id of record to be removed
	private static Boolean deleteCustomer(int custId) {
		Statement statement = null;
		try {
			//Execute query
			statement = connection.createStatement();
			statement.executeQuery("DELETE FROM Customer WHERE cust_id = " + custId);
			statement.close();
			
			//Output - return confirmation true if successful, else false
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to delete a staff member - removes staff record referenced by its id
	//Input - id of record to be removed
	private static Boolean deleteStaff(int staffId) {
		Statement statement = null;
		try {
			//Execute query
			statement = connection.createStatement();
			statement.executeQuery("DELETE FROM Staff WHERE staff_id = " + staffId);
			statement.close();
			
			//Output - return true if successful, else false
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to delete a room category - removes the room category record record referenced by its name and hotel id
	//Input - name of record to be removed and id of its id
	private static Boolean deleteRoomCategory(String roomCategory, int hotelId) {
		Statement statement = null;
		try {
			//Execute query
			statement = connection.createStatement();
			statement.executeQuery("DELETE FROM Room_Category WHERE room_category = '" + roomCategory + "' AND hotel_id = " + hotelId);
			statement.close();
			
			//Output - returns confirmation true if successful, else false
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to delete a room - removes room record referenced by its number and hotel id
	//Input - number of record to be removed and its hotel id
	private static Boolean deleteRoom(int roomNumber, int hotelId) {
		Statement statement = null;
		try {
			//Execute query
			statement = connection.createStatement();
			statement.executeQuery("DELETE FROM Rooms WHERE room_number = " + roomNumber + " AND hotel_id = " + hotelId);
			statement.close();
			
			//Output - return confirmation true if successful, else false
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to delete a hotel - removes hotel record referenced by its id
	//Input - id of record to be removed
	private static Boolean deleteHotel(int hotelId) {
		Statement statement = null;
		try {
			//Execute query
			statement = connection.createStatement();
			statement.executeQuery("DELETE FROM Hotel WHERE hotel_id = " + hotelId);
			statement.close();
			
			//Output - return confirmation true if successful, else false
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//API to create a new customer - inserts a new customer record
	//Input - new customer id to be added along with values for columns name, phone, email, date of birth
	private static Integer enterNewCustomer(int custId, String name, long phone, String email, String dob) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, custId);
			preparedStatement.setString(2, name);
			preparedStatement.setLong(3, phone);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, dob);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - id of new record added if successful, else null
			return custId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a new catering staff - inserts a new record in Catering_Staff table
	//Input - new staff id to be added
	private static Integer enterNewCateringStaff(int staffId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Catering_Staff VALUES (?)");
			preparedStatement.setInt(1, staffId);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - return id of record successful, else false
			return staffId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a Room Service Staff - inserts a new record in Room_Service_Staff table
	//Input - new staff_id to be added
	private static Integer enterNewRoomServiceStaff(int staffId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Room_Service_Staff VALUES (?)");
			preparedStatement.setInt(1, staffId);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - return id of inserted record if successful, else false
			return staffId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a new staff member - inserts a new staff record
	//Input - new staff id to be added along with values for columns - name, address, phone, age, department, title and id of hotel
	private static Integer enterNewStaff(int staffId, String name, String address, long phone, int age, String department, String title, int hotelId) {
		try {
			//set autocommit to false (transaction is not committed automatically)
			connection.setAutoCommit(false);
			
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, staffId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, address);
			preparedStatement.setLong(4, phone);
			preparedStatement.setInt(5, age);
			preparedStatement.setString(6, department);
			preparedStatement.setString(7, title);
			preparedStatement.setInt(8, hotelId);
			preparedStatement.executeUpdate();
			
			//Insert a check to ensure correctness of records inserted
			if(age >= 18) {
				
				//Commit record only if age of staff member >= 18
				connection.commit();
				System.out.println("Transaction successful - commit");
				
				preparedStatement.close();
				
				//Reset autocommit to true so that future transactions are committed automatically
				connection.setAutoCommit(true);
				
				//Output - return id of new staff if successful, else false
				return staffId;
			}
			else {
				//age < 18, hence rollback changes
				connection.rollback();
				System.out.println("Transaction failure - rollback");
			}
			
			preparedStatement.close();
			
			//reset autocommit to true so that future transactions will be committed automatically
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				//Rollback the transaction in case of any errors and reset autocommit to true
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	//API to create a new Presidential Suite - inserts a new record in Presidential_Suite table
	//Input - new room number and id of hotel it is present in, along with values for room service and catering staff ids
	private static Integer enterNewPresidentialSuite(int roomNumber, int hotelId) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Presidential_Suite VALUES (?, ?, ?, ?);");
			preparedStatement.setInt(1, roomNumber);
			preparedStatement.setInt(2, hotelId);
			preparedStatement.setNull(3, java.sql.Types.INTEGER);
			preparedStatement.setNull(4, java.sql.Types.INTEGER);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - return id of room if successful, else null
			return roomNumber;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a new room category - inserts a new record in Room_Categoriestable
	//Input - new hotel id and category name to be added along with values for columns nightly rate and max occupancy
	private static String enterNewRoomCategory(int hotelId, String roomCategory, Double rate, int occupancy) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Room_Category VALUES (?, ?, ?, ?);");
			preparedStatement.setInt(1, hotelId);
			preparedStatement.setString(2, roomCategory);
			preparedStatement.setDouble(3, rate);
			preparedStatement.setInt(4, occupancy);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - return name of new category if successful, else null
			return roomCategory;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a new room - inserts a new Room record
	//Input - new room number and hotel id to be added along with values for columns room category and availability
	private static Integer enterNewRoom(int roomNumber, int hotelId, String roomCategory, int availability) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rooms VALUES (?, ?, ?, ?);");
			preparedStatement.setInt(1, roomNumber);
			preparedStatement.setInt(2, hotelId);
			preparedStatement.setString(3, roomCategory);
			preparedStatement.setInt(4, availability);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - return number of new room if successful, else null
			return roomNumber;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to create a new hotel - inserts a new hotel record
	//Input - new hotel id to be added along with values for hotel address, name, phone and city
	private static Integer enterNewHotel(int hotelId, String address, String name, long phone, String city) {
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Hotel VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, hotelId);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, name);
			preparedStatement.setLong(4, phone);
			preparedStatement.setString(5, city);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			//Output - returns id of new record if successful, else null
			return hotelId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//API to report revenue of a specified hotel within a given date range
	//Input - id of the required hotel, start and end date ranges 
	private static ResultSet reportHotelRevenueDateRange(int hotelId, String rangeStart, String rangeEnd) {
		ResultSet resultSet = null;
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT hotel_id, SUM(total_amount) as revenue "
					+ "FROM Check_In_Information WHERE check_in_id NOT IN  "
					+ "(SELECT check_in_id FROM Check_In_Information "
					+ "WHERE (start_date < ? AND end_date < ?) OR (start_date > ? AND end_date > ?)) "
					+ "AND total_amount IS NOT NULL AND hotel_id = ? GROUP BY hotel_id;");
			preparedStatement.setString(1, rangeStart);
			preparedStatement.setString(2, rangeEnd);
			preparedStatement.setString(3, rangeStart);
			preparedStatement.setString(4, rangeEnd);
			preparedStatement.setInt(5, hotelId);
			resultSet = preparedStatement.executeQuery();
			
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation from the query, null if empty
		return resultSet;
	}
	
	//API to report revenue of all hotels within a given date range
	//Input - start and end date ranges 
	private static ResultSet reportAllRevenueDateRange(String rangeStart, String rangeEnd) {
		ResultSet resultSet = null;
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT hotel_id, SUM(total_amount) as revenue "
					+ "FROM Check_In_Information WHERE check_in_id NOT IN "
					+ "(SELECT check_in_id FROM Check_In_Information "
					+ "WHERE (start_date < ? AND end_date < ?) "
					+ "OR (start_date > ? AND end_date > ?)) "
					+ "AND total_amount IS NOT NULL GROUP BY hotel_id;");
			preparedStatement.setString(1, rangeStart);
			preparedStatement.setString(2, rangeEnd);
			preparedStatement.setString(3, rangeStart);
			preparedStatement.setString(4, rangeEnd);
			resultSet = preparedStatement.executeQuery();
			
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation from the query (grouped by hotel ids), null if empty
		return resultSet;
	}
	
	//API to report which staff members are serving which check in records
	//No input as all check in records are to be retrieved 
	private static ResultSet reportStaffServingCheckIn() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT Customer.cust_name, Check_In_Information.check_in_id, "
					+ "Staff.* FROM Staff_Serving JOIN Check_In_Information "
					+ "ON Staff_Serving.check_in_id = Check_In_Information.check_in_id "
					+ "JOIN Staff ON Check_In_Information.hotel_id = Staff.hotel_id "
					+ "AND Staff_Serving.staff_id = Staff.staff_id "
					+ "JOIN Customer ON Check_In_Information.cust_id = Customer.cust_id;");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Output - resultant relation from the query, null if empty 
		return resultSet;
	}
	
	//API to report the staff information according to their role
	//No input since all staff records are to be retrieved and ordered by their title
	private static ResultSet reportStaffInformationByRole() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT title, staff_name, staff_address, staff_phone, age, department, hotel_id "
					+ "FROM Staff ORDER BY title;");
		
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resulant relation from the query, null if empty
		return resultSet;
	}
	
	//API to report count of staff according to their title
	//No input since all records are to be retrieved and grouped according to their title
	private static ResultSet reportStaffCountByRole() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT title, COUNT(*) FROM Staff GROUP BY title;");
		
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation of query (grouped by title), null if empty
		return resultSet;
	}
	
	//API to report occupancy grouped by date
	//No input since all records are to be retrieved and grouped by start and end dates
	private static ResultSet reportOccupancyByDateRange() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT start_date, end_date, COUNT(*) AS occupancy, "
					+ "100 * COUNT(*) / (select COUNT(*) FROM Rooms) AS 'occupancy %'"
					+ "FROM Check_In_Information GROUP BY start_date, end_date;");
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation of query (grouped by start, end dates), null if empty
		return resultSet;
	}
	
	//API to report occupancy grouped by city
	//No input since all records are to be retrieved and grouped by city
	private static ResultSet reportOccupancyByCity() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT city, (total_count - availability) AS occupancy, "
					+ "100 * (total_count - availability) / total_count AS 'occupancy %' "
					+ "FROM (SELECT city, COUNT(Rooms.hotel_id) AS total_count, SUM(availability) AS availability "
					+ "FROM Rooms JOIN Hotel ON Rooms.hotel_id = Hotel.hotel_id GROUP BY city) a;");
		
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation of query (grouped by city), null if empty
		return resultSet;
	}
	
	//API to report occupancy grouped by room category
	//No input since all records are to be retrieved and grouped by room category
	private static ResultSet reportOccupancyByRoomCategory() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT room_category, COUNT(*) - SUM(availability) AS 'occupancy value', "
					+ "100 * (COUNT(*) - SUM(availability)) / COUNT(*) AS 'occupancy %' "
					+ "FROM Rooms GROUP BY room_category;");
		
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation of query (grouped by room category), null if empty
		return resultSet;
	}
	
	//API to report occupancy grouped by hotel
	//No input since all records are to be retrieved and grouped by hotel id
	private static ResultSet reportOccupancyByHotel() {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT a.hotel_id, hotel_name, "
					+ "COUNT(*) - SUM(availability) AS 'occupancy value', "
					+ "100 * (COUNT(*) - SUM(availability))/COUNT(*) AS 'occupancy %' "
					+ "FROM Rooms a JOIN Hotel b ON a.hotel_id = b.hotel_id "
					+ "GROUP BY a.hotel_id;");
		
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation of query (grouped by hotel id), null if empty
		return resultSet;
	}
	
	//API to generate the total amount owed by the customer for a particular check in record
	//Input check in record for which bill amount is to be generated
	private static ResultSet generateTotalBill(int checkInId) {
		ResultSet resultSet = null;
		try {
			//Execute query
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT check_in_id, "
					+ "room_total + services_total - (room_total + services_total) * 5/100 * hotel_credit AS 'Total Charge' "
					+ "FROM (select check_in_id, hotel_credit, room_total, sum(services_total) AS services_total "
					+ "FROM (SELECT c.check_in_id, hotel_credit, nightly_rate, (end_date - start_date) * nightly_rate AS room_total, g.service_name, quantity, rate, services_total "
					+ "FROM Check_In_Information c JOIN (SELECT b.room_number, b.hotel_id, nightly_rate "
					+ "FROM Room_Category a JOIN Rooms b ON a.room_category = b.room_category AND a.hotel_id = b.hotel_id) d "
					+ "ON c.room_number = d.room_number AND c.hotel_id = d.hotel_id "
					+ "JOIN (SELECT check_in_id, e.service_name, quantity, rate, quantity * rate AS services_total FROM Services_Used e "
					+ "JOIN Services f ON e.service_name = f.service_name) g ON c.check_in_id = g.check_in_id "
					+ "JOIN Billing_Account h ON c.account_number = h.account_number WHERE c.check_in_id = " + checkInId + ") i "
					+ "GROUP BY hotel_credit, room_total, check_in_id)j;");
		
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - relation containing total amount, empty if null
		return resultSet;
	}
	
	
	//API to generate receipt containing each item and its cost (room charge, services used etc)
	//Input - check in id for which receipt is to be generated
	private static ResultSet generateItemizedReceipt(int checkInId) {
		ResultSet resultSet = null;
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT 'room cost' AS Name, "
					+ "nightly_rate AS Rate_Per_Unit, (end_date - start_date) AS Quantity, (end_date - start_date) * nightly_rate AS Total_Amount "
					+ "FROM Check_In_Information c "
					+ "JOIN (SELECT a.room_number, a.hotel_id, b.nightly_rate FROM Rooms a "
					+ "JOIN Room_Category b ON a.hotel_id = b.hotel_id AND a.room_category = b.room_category) d "
					+ "ON c.room_number = d.room_number AND c.hotel_id = d.hotel_id "
					+ "WHERE check_in_id = ? "  
					+ " UNION ALL SELECT a.service_name AS 'Name', rate AS Rate_Per_Unit, quantity AS Quantity, (rate * quantity) AS Total_Amount "
					+ "FROM Services a JOIN Services_Used b ON a.service_name = b.service_name "
					+ "WHERE check_in_id = ?;");
			preparedStatement.setInt(1, checkInId);
			preparedStatement.setInt(2, checkInId);
			resultSet = preparedStatement.executeQuery();
			
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation containing columns for check in id, name of charge, rate per unit, quantity used, total amount
		//Returns null if empty
		return resultSet;
	}
	
	
	//API to check whether rooms of a particular category are available at a particular hotel
	//Input - category and hotel id of rooms 
	private static ResultSet checkRoomTypeAvailability(String roomCategory, int hotelId) {
		ResultSet resultSet = null;
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT room_number, availability"
					+ " FROM Rooms WHERE room_category = ? AND hotel_id = ?;");
			preparedStatement.setString(1, roomCategory);
			preparedStatement.setInt(2, hotelId);
			resultSet = preparedStatement.executeQuery();
			
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Output - resultant relation containing room numbers and their availability status (1 for available and 0 for not)
		return resultSet;
	}
	
	//API to check whether a particular room is available at a particular hotel
	//Input - category and hotel id of rooms 
	private static Boolean checkRoomAvailability(int roomNumber, int hotelId) {
		ResultSet resultSet = null;
		try {
			//Set parameters and execute query
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT availability FROM Rooms "
					+ "WHERE room_number = ? AND hotel_id = ?;");
			preparedStatement.setInt(1, roomNumber);
			preparedStatement.setInt(2, hotelId);
			resultSet = preparedStatement.executeQuery();
			
			preparedStatement.close();
			
			//Output - return true if room is available, false otherwise
			if(resultSet.first()) {
				if(resultSet.getInt("availability") == 1)
					return true;
				else 
					return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Function to print contents of ResultSet object
	private static void printResultSet(ResultSet resultSet) throws SQLException {
		ResultSetMetaData rsmd = resultSet.getMetaData();
		rsmd = resultSet.getMetaData();
		for(int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.print(rsmd.getColumnName(i) + " | ");
		}
		System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
        while(resultSet.next()) {
        	for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(resultSet.getString(i) + "   |   ");
			}
        	System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
        }
		return;
	}
	
	//close a connection object
	private static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//close a ResultSet object
	private static void close (ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//establish connection to the specified database
	private static Connection connect() {
		String user = "rhpande";
	    String passwd = "200207263";
	    Connection c = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		    c = DriverManager.getConnection(jdbcURL, user, passwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return c;
	}

	public static void main(String[] args) throws SQLException {  
		
		//Establish connection to the database
		connection = connect();
		
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);
        Integer roomNumber, hotelId, checkInId, staffId, custId, resultId, quantity, newHotelId;
        Long phone;
        String roomCategory, rangeStart, rangeEnd, name, address, serviceName, city;
        Double rate;
        Boolean result;

        //Call relevant APIs for tasks and operations through a menu-based system
        while(true) {
        	System.out.println();
        	System.out.println("Press 1 for Information Processing operations");
        	System.out.println("Press 2 for Maintaining Service Record operations");
        	System.out.println("Press 3 for Maintaining Billing Record operations");
        	System.out.println("Press 4 for Reports");
        	System.out.println("Press any other number to exit");
        	
        	int task = scanner.nextInt();
        	int operation;
        	
        	switch (task) {
			case 1: //Choice of operations for the task 'Information Processing'
				System.out.println("Press 1 to enter information about hotels");
	        	System.out.println("Press 2 to update information about hotels");
	        	System.out.println("Press 3 to delete information about hotels");
	        	System.out.println("Press 4 to enter information about rooms");
	        	System.out.println("Press 5 to enter information about room categories");
	        	System.out.println("Press 6 to update information about rooms");
	        	System.out.println("Press 7 to update information about room categories");
	        	System.out.println("Press 8 to delete information about rooms");
	        	System.out.println("Press 9 to delete information about room categories");
	        	System.out.println("Press 10 to enter information about staff");
	        	System.out.println("Press 11 to update information about staff");
	        	System.out.println("Press 12 to delete information about staff");
	        	System.out.println("Press 13 to enter information about customer");
	        	System.out.println("Press 14 to update information about customer");
	        	System.out.println("Press 15 to delete information about customer");
	        	System.out.println("Press 16 to check room availability");
	        	System.out.println("Press 17 to check room type availability");
	        	System.out.println("Press 18 to assign a room");
	        	System.out.println("Press 19 to release a room");
	        	
	        	operation = scanner.nextInt();
	        	switch (operation) {
				case 1:	//Enter information about hotels
					
					//Scan inputs for all columns
					System.out.println("Enter hotel_id");
					hotelId = scanner.nextInt();
					System.out.println("Enter address");
					scanner.nextLine();
					address = scanner.nextLine();
					System.out.println("Enter name");
					name = scanner.nextLine();
					System.out.println("Enter phone");
					phone = scanner.nextLong();
					System.out.println("Enter city");
					scanner.nextLine();
					city = scanner.nextLine();
					
					//Enter new hotel record with columns hotel_id, address, name, phone, city. 
					//Output: hotel_id of newly inserted record if successful. Else null
					resultId = enterNewHotel(hotelId, address, name, phone, city);
					if(resultId != null)
						System.out.println("1 Row Added with hotel_id " + resultId);
					else
						System.out.println("Insert unsuccessful");
					break;
				
				case 2: //Update information about hotels
					
					//Scan input for hotel_id to be updated 
					System.out.println("Enter hotel_id to be updated");
					hotelId = scanner.nextInt();
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter hotel_id");
					newHotelId = scanner.nextInt();
					System.out.println("Enter address");
					scanner.nextLine();
					address = scanner.nextLine();
					System.out.println("Enter name");
					name = scanner.nextLine();
					System.out.println("Enter phone");
					phone = scanner.nextLong();
					System.out.println("Enter city");
					scanner.nextLine();
					city = scanner.nextLine();
					
					//Update hotel record with specified hotel_id 
					//Output: Confirmation - true if successful. Else false
					result = updateHotel(hotelId, newHotelId, address, name, phone, city);
					if(result)
						System.out.println("1 Row changed with hotel_id " + hotelId);
					else
						System.out.println("Update unsuccessful");
					break;
				case 3: //Delete a hotel with a specified id
					
					//Scan input for hotel id to be deleted
					System.out.println("Enter hotel id to be deleted");
					hotelId = scanner.nextInt();
					
					//Delete hotel. Output - true if successful, else false
					result = deleteHotel(hotelId);
					if(result) 
						System.out.println("Hotel with id " + hotelId + " deleted");
					else
						System.out.println("Delete unusccessful");
					break;
				case 4: //Enter a new room
					
					//Scan input
					System.out.println("Enter room_number, hotel_id, room_category, availability (1/0)");
					roomNumber = scanner.nextInt();
					hotelId = scanner.nextInt();
					roomCategory = scanner.next();
					scanner.nextLine();
					int availability = scanner.nextInt();
					
					//Enter new room record with columns room_number, hotel_id, room_category, availability. 
					//Output: room_number of newly inserted record if successful. Else null
					resultId = enterNewRoom(roomNumber, hotelId, roomCategory, availability);
					if(resultId != null)
						System.out.println("1 Row Added with room_number " + resultId);
					else
						System.out.println("Insert unsuccessful");
					
					//If room category is Presidential, add a new record the table Presidential_Suite
					if(roomCategory.equals("Presidential")) {
						resultId = enterNewPresidentialSuite(roomNumber, hotelId);
						if(resultId != null)
							System.out.println("1 Row added to Presidential Suite with room_number " + resultId);
						else {
							System.out.println("Insert into Presidential Suite unsuccessful");
						}
					}
					
					break;
				case 5: //Enter a new room category
					
					//Scan input
					System.out.println("Enter hotel_id, room_category, rate, occupancy");
					hotelId = scanner.nextInt();
					roomCategory = scanner.next();
					rate = scanner.nextDouble();
					int occupancy = scanner.nextInt();
					
					//Enter new room category record with columns room_number, hotel_id, room_category, availability. 
					//Output: room_number of newly inserted record if successful. Else null
					roomCategory = enterNewRoomCategory(hotelId, roomCategory, rate, occupancy);
					if(roomCategory != null)
						System.out.println("1 Row Added with category " + roomCategory);
					else
						System.out.println("Insert unsuccessful");
					
					break;
				case 6: //Update an existing room 
					System.out.println("Enter room_id, hotel_id to be updated");
					
					//Scan input
					roomNumber = scanner.nextInt();
					hotelId = scanner.nextInt();
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter values of room_number, hotel_id, room_category, availability");
					int newRoomNumber = scanner.nextInt();
					newHotelId = scanner.nextInt();
					roomCategory = scanner.next();
					scanner.nextLine();
					availability = scanner.nextInt();
					
					//Update room record with specified hotel_id and room_number 
					//Output: Confirmation - true if successful. Else false
					result = updateRoom(roomNumber, hotelId, newRoomNumber, newHotelId, roomCategory, availability);
					if(result)
						System.out.println("1 Row changed with hotel_id " + hotelId + " and room_number " + roomNumber);
					else
						System.out.println("Update unsuccessful");
					break;
				case 7: //Update an existing room category
					
					//Scan input
					System.out.println("Enter hotel_id, room_category to be updated");
					hotelId = scanner.nextInt();
					roomCategory = scanner.next();
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter values of hotel_id, room_category, rate, occupancy");
					newHotelId = scanner.nextInt();
					String newRoomCategory = scanner.next();
					rate = scanner.nextDouble();
					occupancy = scanner.nextInt();
					
					//Update room record with specified hotel_id and room_number 
					//Output: Confirmation - true if successful. Else false
					result = updateRoomCategory(roomCategory, hotelId, newRoomCategory, newHotelId, rate, occupancy);
					if(result)
						System.out.println("1 Row changed with hotel_id " + hotelId + " and room_category " + roomCategory);
					else
						System.out.println("Update unsuccessful");
					break;
				case 8: //Delete a room
					System.out.println("Enter room_number, hotel_id to be deleted");
					
					//Scan input
					roomNumber = scanner.nextInt();
					hotelId = scanner.nextInt();
					
					//Input - room number to be deleted at given hotel
					//Output - true if successful, else false
					result = deleteRoom(roomNumber, hotelId);
					if(result) 
						System.out.println("Room with number " + roomNumber + " deleted");
					else
						System.out.println("Delete unusccessful");
					break;
				case 9: //Delete room category
					
					//Scan input
					System.out.println("Enter room_category, hotel_id to be deleted");
					roomCategory = scanner.next();
					hotelId = scanner.nextInt();
					
					//Delete room category with the specified category name and hotel id
					//Output - true if successful, else false
					result = deleteRoomCategory(roomCategory, hotelId);
					if(result) 
						System.out.println("Room with category " + roomCategory + " deleted");
					else
						System.out.println("Delete unusccessful");
					break;
				case 10: //Enter new staff record
					
					//Scan input
					System.out.println("Enter staff_id");
					staffId = scanner.nextInt();
					System.out.println("Enter name");
					scanner.nextLine();
					name = scanner.nextLine();
					System.out.println("Enter address");
					address = scanner.nextLine();
					System.out.println("Enter phone");
					phone = scanner.nextLong();
					System.out.println("Enter age");
					int age = scanner.nextInt();
					System.out.println("Enter department");
					scanner.nextLine();
					String department = scanner.nextLine();
					System.out.println("Enter title");
					String title = scanner.nextLine();
					System.out.println("Enter hotel_id");;
					hotelId = scanner.nextInt();
					
					//Enter new staff record with columns staff_id, name, address, phone, age, department, title, hotel_id. 
					//Output: staff_id of newly inserted record if successful. Else null
					resultId = enterNewStaff(staffId, name, address, phone, age, department, title, hotelId);
					if(resultId != null)
						System.out.println("1 Row Added with staff_id " + resultId);
					else
						System.out.println("Insert unsuccessful");
					
					//If title is Room Service Staff, add a record to the corresponding table
					if(title.equals("Room Service Staff") && resultId != null) {
						resultId = enterNewRoomServiceStaff(staffId);
						if(resultId != null)
							System.out.println("1 Row added to Room_Service_Staff");
						else
							System.out.println("Insert into Room_Service_Staff unsuccessful");
					}
					
					//If title is Catering Staff, add record to the corresponding table
					else if(title.equals("Catering Staff") && resultId != null) {
						resultId = enterNewCateringStaff(staffId);
						if(resultId != null)
							System.out.println("1 Row added to Catering_Staff");
						else
							System.out.println("Insert into Catering_Staff unsuccessful");
					}
					
					break;
				case 11: //Update staff 
					
					//Scan input
					System.out.println("Enter staff_id to be updated");
					staffId = scanner.nextInt();
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter values of staff_id, address, name, phone, age, department, title, hotel_id");
					int newStaffId = scanner.nextInt();
					System.out.println("Enter address");
					scanner.nextLine();
					address = scanner.nextLine();
					System.out.println("Enter name");
					name = scanner.nextLine();
					System.out.println("Enter phone");
					phone = scanner.nextLong();
					System.out.println("Enter age");
					age = scanner.nextInt();
					System.out.println("Enter department");
					scanner.nextLine();
					department = scanner.nextLine();
					System.out.println("Enter title");;
					title = scanner.nextLine();
					System.out.println("Enter hotel_id");
					hotelId = scanner.nextInt();
					
					//Update staff record with specified staff_id 
					//Output: Confirmation - true if successful. Else false
					result = updateStaff(staffId, newStaffId, address, name, phone, age, department, title, hotelId);
					if(result)
						System.out.println("1 Row changed with hotel_id " + staffId);
					else
						System.out.println("Update unsuccessful");
					break;
				case 12: //Delete staff
					
					//Scan input
					System.out.println("Enter staff id to be deleted");
					staffId = scanner.nextInt();
					
					//Delete staff with specified staff id
					//Output - true if successful, else false
					result = deleteStaff(staffId);
					if(result) 
						System.out.println("Staff with id " + staffId + " deleted");
					else
						System.out.println("Delete unusccessful");
					break;
				case 13: //Enter new customer
					
					//Scan input
					System.out.println("Enter cust_id");
					custId = scanner.nextInt();
					System.out.println("Enter name");
					scanner.nextLine();
					name = scanner.nextLine();
					System.out.println("Enter phone");
					phone = scanner.nextLong();
					System.out.println("Enter email");
					String email = scanner.next();
					System.out.println("Enter dob in format yyyy-mm-dd");
					String dob = scanner.next();
					
					//Enter new customer record with columns cust_id, name, phone, email, dob. 
					//Output: cust_id of newly inserted record if successful. Else null
					resultId = enterNewCustomer(custId, name, phone, email, dob);
					if(resultId != null)
						System.out.println("1 Row Added with cust_id " + resultId);
					else
						System.out.println("Insert unsuccessful");
					break;
				case 14: //Update customer
					
					//Scan input
					System.out.println("Enter cust_id to be updated");
					custId = scanner.nextInt();
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter cust_id");
					int newCustId = scanner.nextInt();
					System.out.println("Enter name");
					scanner.nextLine();
					name = scanner.nextLine();
					System.out.println("Enter phone");
					phone = scanner.nextLong();
					System.out.println("Enter email");
					email = scanner.next();
					System.out.println("Enter dob in format yyyy-mm-dd");
					dob = scanner.next();
					
					//Update customer record with specified cust_id 
					//Output: Confirmation - true if successful. Else false
					result = updateCustomer(custId, newCustId, name, phone, email, dob);
					if(result)
						System.out.println("1 Row changed with cust_id " + custId);
					else
						System.out.println("Update unsuccessful");
					break;
				case 15: //Delete customer
					
					//Scan input
					System.out.println("Enter customer id to be deleted");
					custId = scanner.nextInt();
					
					//Delete customer with specified id
					//Output - true if successful, else false
					result = deleteCustomer(custId);
					if(result) 
						System.out.println("Customer with id " + custId + " deleted");
					else
						System.out.println("Delete unusccessful");
					break;
				case 16: //Check availability of a room
					
					//Scan input
			        System.out.println("Enter room number, hotel id");
			        roomNumber = scanner.nextInt();
			        hotelId = scanner.nextInt();
			        
			        //Check if requested room is available. Input: room_number, hotel_id. 
			        //Output: true if available, else false
			        Boolean available = checkRoomAvailability(roomNumber, hotelId);
			        if(available != null && available) 
			        	System.out.println("Room number " + roomNumber + " available"); 
			        else if(available != null && !available)
			        	System.out.println("Room number " + roomNumber + " not available");
					break;
				case 17: //Check availability of room category
			        System.out.println("Enter room category, hotel id");
			        roomCategory = scanner.next();
			        scanner.nextLine();
			        hotelId = scanner.nextInt();
			        
			        //Check if requested room category is available. Input: room_category, hotel_id. 
			        //Set of all rooms and their availability 
			        resultSet = checkRoomTypeAvailability(roomCategory, hotelId);
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 18: //Assign a room
					
					//Scan input
					System.out.println("Enter room number, hotel id");
			        roomNumber = scanner.nextInt();
			        hotelId = scanner.nextInt();
			        
			        //Assign room with a particular room number if available
			        //Output - true if successful, else false
					result = assignRoom(roomNumber, hotelId);
					if(result) 
						System.out.println("Room " + roomNumber + " assigned");
					else 
						System.out.println("Room " + roomNumber + " cannot be assigned");
					break;
				case 19: //Release a room
					
					//Scan input
					System.out.println("Enter room number, hotel id");
			        roomNumber = scanner.nextInt();
			        hotelId = scanner.nextInt();
			        
			        //Release a room with particular room number if already assigned
			        //Output - true if successful, else false
					result = releaseRoom(roomNumber, hotelId);
					if(result) 
						System.out.println("Room " + roomNumber + " released");
					else 
						System.out.println("Room " + roomNumber + " cannot be released");
					break;
				default:
					break;
				}
	        	
				break;
			case 2: //Choice of operations for the task 'Maintaining Service Records'
	        	System.out.println("Press 1 to enter new service");
	        	System.out.println("Press 2 to update service");
	        	System.out.println("Press 3 to enter new service used record");
	        	System.out.println("Press 4 to update service used record");
	        	
	        	operation = scanner.nextInt();
	        	switch (operation) {
				case 1: //Enter new service information
					
					//Scan input
					System.out.println("Enter service_name");
					scanner.nextLine();
					serviceName = scanner.nextLine();
					System.out.println("Enter rate");
					rate = scanner.nextDouble();
					
					//Enter new service record with columns - rate. 
					//Output: name of newly inserted record if successful. Else null
					serviceName = enterService(serviceName, rate);
					if(serviceName != null)
						System.out.println("1 Row Added with name " + serviceName);
					else
						System.out.println("Insert unsuccessful");
					break;
				case 2: //Update service information
					
					//Scan input
					System.out.println("Enter service_name to update");
					scanner.nextLine();
					serviceName = scanner.nextLine();
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter service_name");
					String newServiceName = scanner.nextLine();
					System.out.println("Enter rate");
					rate = scanner.nextDouble();
					
					//Update record with given service name
					//Output - true if successful, else false
					result = updateService(serviceName, newServiceName, rate);
					if(result)
						System.out.println("1 Row changed with service_name " + serviceName);
					else
						System.out.println("Update unsuccessful");
					break;
				case 3: //Add a new bill for service used
					
					//Scan input
					System.out.println("Enter check_in_id");
					checkInId = scanner.nextInt();
					System.out.println("Enter service name");
					scanner.nextLine();
					serviceName = scanner.nextLine();
					System.out.println("Enter quantity");
					quantity = scanner.nextInt();
					
					//Enter new bill record with columns check_in_id, service_name, quantity. 
					//Output: service_name of newly inserted record if successful. Else null
					serviceName = enterServiceBill(checkInId, serviceName, quantity);
					if(serviceName != null)
						System.out.println("1 Row Added with name " + serviceName + " for check in " + checkInId);
					else
						System.out.println("Insert unsuccessful");
					break;
				case 4: //Update record for service used
					
					//Scan input
					System.out.println("Enter check_in_id to be changed");
					checkInId = scanner.nextInt();
					System.out.println("Enter service name to be changed");
					scanner.nextLine();
					serviceName = scanner.nextLine();
					
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter check_in_id");
					int newCheckInId = scanner.nextInt();
					System.out.println("Enter new service name");
					scanner.nextLine();
					newServiceName = scanner.nextLine();
					System.out.println("Enter quantity");
					quantity = scanner.nextInt();
					
					//Update service used record with specified check_in_id and service_name 
					//Output: Confirmation - true if successful. Else false
					result = updateServiceUsed(checkInId, serviceName, newCheckInId, newServiceName, quantity);
					if(result)
						System.out.println("1 Row changed with check_in_id " + checkInId + " and service_name " + serviceName);
					else
						System.out.println("Update unsuccessful");
					
					break;
				default:
					break;
				}
				break;
			case 3: //Choice of operations for the task 'Maintaining Billing Accounts'
				System.out.println("Press 1 to create billing account");
				System.out.println("Press 2 to maintain/update billing account");
				System.out.println("Press 3 to process check in");
				System.out.println("Press 4 to process check out (and update total_amount)");
				System.out.println("Press 5 to generate itemized receipt");
				System.out.println("Press 6 to calculate total bill");
				
				operation = scanner.nextInt();
				switch (operation) {
				case 1: //Enter new billing account
					
					//Scan input
					System.out.println("Enter account_number");
					int accountNumber = scanner.nextInt();
					System.out.println("Enter SSN");
					int SSN = scanner.nextInt();
					System.out.println("Enter billing address");
					scanner.nextLine();
					String billingAddress = scanner.nextLine();
					System.out.println("Enter pay_method");
					String payMethod = scanner.next();
					System.out.println("Enter card number");
					String cardNumber = scanner.next();
					System.out.println("Enter hotel credit (0/1)");
					int hotelCredit = scanner.nextInt();
					
					//Enter new billing account record with columns account_number, SSN, billing_address, pay_method, card_number, hotel_credit. 
					//Output: account number of newly inserted record if successful. Else null
					resultId = createBillingAccount(accountNumber, SSN, billingAddress, payMethod, cardNumber, hotelCredit);
					if(resultId != null)
						System.out.println("1 Row Added with account_number " + resultId);
					else
						System.out.println("Insert unsuccessful");
					break;
				case 2: //Update/maintain billing account
					
					//Scan input
					System.out.println("Enter account_number to be updated");
					accountNumber = scanner.nextInt();
					
					System.out.println("Enter new values. Reenter old values where fields are not to be updated");
					System.out.println("Enter account_number");
					int newAccountNumber = scanner.nextInt();
					System.out.println("Enter SSN");
					SSN = scanner.nextInt();
					System.out.println("Enter billing address");
					scanner.nextLine();
					billingAddress = scanner.nextLine();
					System.out.println("Enter pay_method");
					payMethod = scanner.next();
					System.out.println("Enter card_number");
					cardNumber = scanner.next();
					System.out.println("Enter hotel credit (0/1)");
					hotelCredit = scanner.nextInt();
					
					//Update billing record with specified account number 
					//Output: Confirmation - true if successful. Else false
					result = updateBillingAccount(accountNumber, newAccountNumber, SSN, billingAddress, payMethod, cardNumber, hotelCredit);
					if(result)
						System.out.println("1 Row changed with account_number " + accountNumber);
					else
						System.out.println("Update unsuccessful");
					break;
				case 3: //Process check in for a particular check in id 
					
					//Scan input
					System.out.println("Enter check_in_id");
					checkInId = scanner.nextInt();
					System.out.println("Enter start_date");
					String startDate = scanner.next();
					System.out.println("Enter end_date");
					String endDate = scanner.next();
					System.out.println("Enter check_in_time");
					String checkInTime = scanner.next();
					System.out.println("Enter check_out_time");
					String checkOutTime = scanner.next();
					System.out.println("Enter number_of_guests");
					int numberOfGuests = scanner.nextInt();
					System.out.println("Enter total_amount");
					String totalAmount = scanner.next();
					System.out.println("Enter account_number");
					accountNumber = scanner.nextInt();
					System.out.println("Enter room_number");
					roomNumber = scanner.nextInt();
					System.out.println("Enter hotel_id");
					hotelId = scanner.nextInt();
					System.out.println("Enter cust_id");
					custId = scanner.nextInt();
					
					//Enter new check in record 
					//Output: check in id of newly inserted record if successful. Else null
					resultId = processCheckIn(checkInId, startDate, endDate, checkInTime, checkOutTime, numberOfGuests, totalAmount, accountNumber, roomNumber, hotelId, custId);
					if(resultId != null)
						System.out.println("1 Row Added with check_in_id " + resultId);
					else
						System.out.println("Insert unsuccessful");
					break;
				case 4: //Process check out for a particular id
					
					//Scan input
					System.out.println("Enter check_in_id");
					checkInId = scanner.nextInt();
					System.out.println("Enter total_amount");
					totalAmount = scanner.next();
					System.out.println("Enter check_out_time");
					checkOutTime = scanner.next();
					
					//Update values of check out time and total amount for a particular check in
					//Output - true if successful, else falce
					result = processCheckOut(checkInId, new Double(totalAmount), checkOutTime);
					if(result)
						System.out.println("Checkout for id " + checkInId + " complete");
					else
						System.out.println("Checkout failed");
					break;
				case 5: //Generate itemized receipt for a check in
					
					//Scan input
					System.out.println("Enter check_in_id for receipt generation");
					checkInId = scanner.nextInt();
					
					//Generate itemized receipt. Input: check_in_id. 
					//Output: ResultSet containing receipt
			        resultSet = generateItemizedReceipt(checkInId);
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 6: //Generate total bill for a particular check in 
					
					//Scan input
					System.out.println("Enter check_in_id for calculating total amount");
					checkInId = scanner.nextInt();
					
					//Calculate total amount. Input: check_in_id. 
					//Output: ResultSet containing total amount
			        resultSet = generateTotalBill(checkInId);
			        if(resultSet != null) 
			        	printResultSet(resultSet);
					break;
				default:
					break;
				}
				break;
			case 4: //Choice of operations for the task 'Reports'
				System.out.println("Press 1 for occupancy report by hotel");
				System.out.println("Press 2 for occupancy report by room type");
				System.out.println("Press 3 for occupancy report by city");
				System.out.println("Press 4 for occupancy report by date range");
				System.out.println("Press 5 for reporting staff count by role");
				System.out.println("Press 6 for reporting staff information");
				System.out.println("Press 7 for reporting staff serving customer stay");
				System.out.println("Press 8 for reporting revenue within date range");
				System.out.println("Press 9 for reporting hotel revenue within date range");
				
				operation = scanner.nextInt();
				switch (operation) {
				case 1:
			        //Report occupancy by hotel
			        resultSet = reportOccupancyByHotel();
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 2:
					//Report occupancy by room category
			        resultSet = reportOccupancyByRoomCategory();
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 3:
					//Report occupancy by city
			        resultSet = reportOccupancyByCity();
			        if(resultSet != null)
			          	printResultSet(resultSet);
					break;
				case 4:
					//Report occupancy by date range
			        resultSet = reportOccupancyByDateRange();
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 5:
					//Report staff grouped by role
			        resultSet = reportStaffCountByRole();
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 6:
					//Report staff information by role
					resultSet = reportStaffInformationByRole();
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 7:
					//Report staff serving check in 
					resultSet = reportStaffServingCheckIn();
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 8: //Report all revenue within date range
					
					//Scan input
					System.out.println("Enter start date in format yyyy-mm-dd");
					rangeStart = scanner.next();
					scanner.nextLine();
					System.out.println("Enter end date in format yyyy-mm-dd");
					rangeEnd = scanner.next();
					scanner.nextLine();
					
					//Report revenue for all hotels within a date range specified by input
					resultSet = reportAllRevenueDateRange(rangeStart, rangeEnd);
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				case 9: //Report hotel revenue by date range
					
					//Scan input
					System.out.println("Enter hotel id");
					hotelId = scanner.nextInt();
					System.out.println("Enter start date in format yyyy-mm-dd");
					rangeStart = scanner.next();
					scanner.nextLine();
					System.out.println("Enter end date in format yyyy-mm-dd");
					rangeEnd = scanner.next();
					scanner.nextLine();
					
					//Report revenue for a specific hotel within a date range
			        resultSet = reportHotelRevenueDateRange(hotelId, rangeStart, rangeEnd);
			        if(resultSet != null)
			        	printResultSet(resultSet);
					break;
				default:
					break;
				}
				break;
			default:
				//Close relevant objects and perform a clean exit
				System.out.println("Exit");
		        close(resultSet);
		        close(connection);
				System.exit(0);
			}
        }
	}
}