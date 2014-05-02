/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @ Author Boris
 */
package org.fancygiraffe.global;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;





public class TestingMain {
 
	private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_CONNECTION = "jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";
	private static final String DB_USER = "Weber";
	private static final String DB_PASSWORD = "weber";
 
	public static void main(String[] argv) {
 
		try {
 
			callOracleStoredProcINParameter();
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
	}
 
	private static void callOracleStoredProcINParameter() throws SQLException {
 
		Connection dbConnection = null;
		CallableStatement  stmt = null;
                
 
		String insertStoreProc = "{CALL rptGetAssetbyLocation(?)}";
 
		try {
			dbConnection = getDBConnection();
			 stmt = dbConnection.prepareCall(insertStoreProc);
                         stmt.registerOutParameter(1, java.sql.Types.INTEGER);   
                        
                         
                         
                         
                      
             
                   
    
                         
                   ResultSet rs = stmt.executeQuery();
             
           while(rs.next()){
               
               System.out.println(rs.getInt(1));
               System.out.println(rs.getString(2));
           }
 
		
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
 
	private static Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 
			dbConnection = DriverManager.getConnection(
				DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		return dbConnection;
 
	}
 
	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
 
}