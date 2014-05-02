/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fancygiraffe.global;

/**
 *
 * @author Boris
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tables {

	public static void displayData(ResultSet rs) throws SQLException {
		while(rs.next()) {
			String tableFullName =
					rs.getString("assetsId") + ": " + rs.getString("asset_name");
			System.out.println(tableFullName);
		}
	}
	
}
