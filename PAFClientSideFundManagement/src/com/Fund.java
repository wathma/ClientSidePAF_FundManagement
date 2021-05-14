package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Fund {
	
		//A common method to connect to the DB
		private Connection connect()
		{
				Connection con = null;
				
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");

					// DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/funds", "root", "WathmaJay@New");
				}
				catch (Exception e)
				{e.printStackTrace();}
				
				return con;
		}
		public String insertFund(String name, String address, String amount, String email ,String phone, String nic)
		{
				String output = "";
				
				try
				{
						Connection con = connect();
					
						if (con == null)
						{return "Error while connecting to the database for inserting."; }
						
						// create a prepared statement
						String query = " insert into fundsdetails(`id`,`name`,`address`,`amount`,`email`,`phone`,`nic`)"
										+ " values (?, ?, ?, ?, ?, ?, ?)";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
	 
						// binding values
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, name);
						preparedStmt.setString(3, address);
						preparedStmt.setDouble(4, Double.parseDouble(amount));
						preparedStmt.setString(5, email);
						preparedStmt.setString(6, phone);
						preparedStmt.setString(7, nic);
						
						// execute the statement
	
						preparedStmt.execute();
						con.close();
						
						output = "Inserted successfully";
				}
				catch (Exception e)
				{
						output = "Error while inserting the fund.";
						System.err.println(e.getMessage());
				}
				
				return output;
	 }
		
	 public String readFunds()
	 {
		 	String output = "";
	 
		 		try
		 		{
		 				Connection con = connect();
		 				if (con == null)
		 				{return "Error while connecting to the database for reading."; }
	 
		 				// Prepare the html table to be displayed
		 				output = "<table border='1'><tr><th>Name</th><th>Address</th>" + "<th>Amount</th>" 
		 						 + "<th>Email</th>" + "<th>Phone</th>" + "<th>NIC</th>"
		 						 +"<th>Update</th><th>Remove</th></tr>";

		 				String query = "select * from fundsdetails";
		 				java.sql.Statement stmt = con.createStatement();
		 				ResultSet rs = stmt.executeQuery(query);
	 
		 				// iterate through the rows in the result set
		 				while (rs.next())
		 				{
		 						String id = Integer.toString(rs.getInt("id"));
		 						String name = rs.getString("name");
		 						String address = rs.getString("address");
		 						String amount = Double.toString(rs.getDouble("amount"));
		 						String email = rs.getString("email");
		 						String phone = rs.getString("phone");
		 						String nic = rs.getString("nic");
	 
		 						// Add into the html table
		 						output += "<tr><td>" + name + "</td>";
		 						output += "<td>" + address + "</td>";
		 						output += "<td>" + amount + "</td>";
		 						output += "<td>" + email + "</td>";
		 						output += "<td>" + phone + "</td>";
		 						output += "<td>" + nic + "</td>";
	 
		 					// buttons
		 						output += "<td><input name='btnUpdate' type='button' value='Update' "
		 						+ "class='btnUpdate btn btn-secondary' data-fundid='" + id + "'></td>"
		 						+ "<td><input name='btnRemove' type='button' value='Remove' "
		 						+ "class='btnRemove btn btn-danger' data-fundid='" + id + "'></td></tr>"; 
		 						 
		 				}
		 				
		 				con.close();
	 
		 				// Complete the html table
		 				output += "</table>";
		 		}
		 		catch (Exception e)
		 		{
		 				output = "Error while reading the funds.";
		 				System.err.println(e.getMessage());
		 		}
		 	
		 		return output;
	 }
	 
	 public String updateFund(String ID, String name, String address, String amount, String email, String phone, String nic)
	
	 {
		 	String output = "";
	 
		 		try
		 		{
		 				Connection con = connect();
		 				
		 				if (con == null)
		 				{return "Error while connecting to the database for updating."; }
	 
		 				// create a prepared statement
		 				String query = "UPDATE fundsdetails SET name=?,address=?,amount=?,email=?,phone=?,nic=? WHERE id=?";
		 				
		 				PreparedStatement preparedStmt = con.prepareStatement(query);
	
		 				// binding values
		 				preparedStmt.setString(1, name);
		 				preparedStmt.setString(2, address);
		 				preparedStmt.setDouble(3, Double.parseDouble(amount));
		 				preparedStmt.setString(4, email);
		 				preparedStmt.setString(5, phone);
		 				preparedStmt.setString(6, nic);
		 				preparedStmt.setInt(7, Integer.parseInt(ID));
		 				
		 				
		 				
		 				// execute the statement
		 				preparedStmt.execute();
		 				con.close();
		 				
		 				String newFund = readFunds(); 
		 				output = "{\"status\":\"success\", \"data\": \"" + newFund + "\"}"; 
		 				
		 		}
		 		catch (Exception e)
		 		{
		 				output = "{\"status\":\"error\", \"data\": \"Error while updating the fund.\"}"; 
		 				System.err.println(e.getMessage()); 
		 		}
		 	
		 		return output;
	 }
	 
	 public String deleteFund(String id)
	 {
		 	String output = "";
		 	
		 		try
		 		{
		 				Connection con = connect();
		 				
		 				if (con == null)
		 				{return "Error while connecting to the database for deleting."; }
		 				
		 				// create a prepared statement
		 				String query = "delete from fundsdetails where id=?";
		 				PreparedStatement preparedStmt = con.prepareStatement(query);
	 
		 				// binding values
		 				preparedStmt.setInt(1, Integer.parseInt(id));
	 
		 				// execute the statement
		 				preparedStmt.execute();
		 				con.close();
		 				
		 				String newFund = readFunds(); 
		 				output = "{\"status\":\"success\", \"data\": \"" + 
		 				newFund + "\"}"; 
		 		}
		 		catch (Exception e)
		 		{
		 				output = "Error while deleting the fund.";
		 				System.err.println(e.getMessage());
		 		}
		 	
		 		return output;
	 }
	
	
}



