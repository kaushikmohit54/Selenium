package qaFramework.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseOperations {
	
	public DataBaseOperations() throws Exception{
		
	}
	
	public void readDataFromDB(String env, String query) throws Exception {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
		String strSqlURL = "jdbc:sqlserver:://"+env+";";
		String strUsername = "admin";
		String strPassword = "Bestinet@2017";
		String strInsertSQL;

		try {
			Class.forName(driverName); // LOAD THE DATABASE DRIVER
			Connection conn = DriverManager.getConnection(strSqlURL,strUsername, strPassword);
			// ESTABLISH THE CONNECTION TO THE DATABASE

			Statement stmt = conn.createStatement(); // GET A STATEMENT FOR THE CONNECTION
			strInsertSQL = "Insert into tblAutomationResults "
					+ "(env,query) values " + "('" + env + "','" + query + "')"; // Insert
			// a
			// record*/

			stmt.executeUpdate(strInsertSQL); // EXECUTE THE SQL QUERY AND STORE IN RESULTS SET
			// CLOSE THE RESULT, STATEMENT AND CONNECTION
			stmt.close();
			conn.close();
			System.out.println("Insert to Table was successful");

		}catch (SQLException se) {//// HANDLE THE SQL EXCEPTION
			System.out.println("SQL Exception:");
			// PRINT TILL ALL THE ECEPTIONS ARE RAISED
			while (se != null) {
				System.out.println("State : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error : " + se.getErrorCode());
				se = se.getNextException();
			}

		}catch (Exception e) {//// CATCH THE CLASS EXCEPTION	
			System.out.println(e);
		}
	}
	
	

}
