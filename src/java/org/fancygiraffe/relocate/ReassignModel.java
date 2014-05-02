/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fancygiraffe.relocate;

import org.fancygiraffe.global.Connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

public class ReassignModel {
    
    public int edit(String loc, CachedRowSet crs, Map<String,Integer> locMap) {
        int ret = 0;
        
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            crs.last();
            int rowCount = crs.getRow();
        
            String sql = "EXEC reassignAssets ?, ?";
            
            //This needs to put all the ids into a string with a | sperating the values
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
            
            int locID = locMap.get(loc);
            
            conn = DriverManager.getConnection(Connections.connectionUrl(),
                                               Connections.user(),
                                               Connections.pwd());
            ps = conn.prepareStatement(sql);
            ps.setInt(1, locID);
            //ps.setString(2, sql);
            ret = ps.executeUpdate();
        } 
        catch (SQLException ex) {
            Logger.getLogger(ReassignModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReassignModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ReassignModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return ret;
    }   
}
