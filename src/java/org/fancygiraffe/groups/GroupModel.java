/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fancygiraffe.groups;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import org.fancygiraffe.global.Connections;
import org.fancygiraffe.global.ErrorLog;

public class GroupModel {
    
    public int createGroup(CachedRowSet crs) {
        
        int ret = 0;
        int nextID = 0;
        
        Connection conn = null;
        PreparedStatement ps = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try {
			crs.last();
            int rowCount = crs.getRow();
            
            String sql2 = "UPDATE assets SET group_id = ? WHERE id IN (";
            
            crs.beforeFirst();
            for (int i = 1; i < rowCount; i++) {
                crs.next();
                int id = crs.getInt(1);
                sql2 += id;
                sql2 += ", ";
            }  
            crs.next();
            sql2 += crs.getInt(1);
            sql2 += ")";

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(Connections.connectionUrl(),
                                               Connections.user(),
                                               Connections.pwd());
            
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                                             ResultSet.CONCUR_UPDATABLE);
            
            String sql1 = "SELECT MAX(group_id)+1 AS `new_group_id` FROM assets";
            
            rs = statement.executeQuery(sql1);
            
            while(rs.next()) {
				nextID = rs.getInt(1);
				//This line was causing problems when I ran it, so I changed it. - Erik
				//  nextID = Integer.parseInt(rs.getString(1));
            }
            
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, nextID);
            ret = ps.executeUpdate();
    
        } 
        catch (Exception ex) {
            new ErrorLog().LogError(ex.getMessage(), "GroupModel", "edit"); //log errors
        }
        finally {
            try {
               rs.close();
               ps.close();
               conn.close();
            } catch (SQLException ex) {
                new ErrorLog().LogError(ex.getMessage(), "GroupModel", "edit"); //log errors
            }
        }
        
        return ret;
    }   
    
    public int dissolveGroup(CachedRowSet crs) {
        int ret = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            crs.last();
            int rowCount = crs.getRow();
        
            String sql = "UPDATE assets SET group_id = null WHERE id IN (";
            
            crs.beforeFirst();
            for (int i = 1; i < rowCount; i++) {
                crs.next();
                int id = crs.getInt(1);
                sql += id;
                sql += ", ";
            }
            crs.next();
            sql += crs.getInt(1);
            sql += ")";
                        
            conn = DriverManager.getConnection(Connections.connectionUrl(),
                                               Connections.user(),
                                               Connections.pwd());
            ps = conn.prepareStatement(sql);
            ret = ps.executeUpdate();
        } 
        catch (Exception ex) {
            Logger.getLogger(GroupModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                ps.close();
            } catch (Exception ex) {
                Logger.getLogger(GroupModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                conn.close();
            } catch (Exception ex) {
                Logger.getLogger(GroupModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
}
