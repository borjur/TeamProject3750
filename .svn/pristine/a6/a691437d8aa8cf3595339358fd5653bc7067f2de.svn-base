package org.fancygiraffe.groups;

import org.fancygiraffe.global.Connections;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import org.fancygiraffe.global.ErrorLog;

public class GroupQueryModel {
    
    public CachedRowSet rowset(String id) {
       
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        CachedRowSet crs = null;
        PreparedStatement ps = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(Connections.connectionUrl(), 
                                               Connections.user(), 
                                               Connections.pwd());
            
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                                             ResultSet.CONCUR_UPDATABLE);
         
            String sql = "EXEC getAssetbyTag ?";
          
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery(sql);          
            
            crs = new com.sun.rowset.CachedRowSetImpl();
            
            
            crs.populate(rs);
        } 
        catch (Exception ex) {
            new ErrorLog().LogError(ex.getMessage(), "GroupQueryModel", "rowset");
        }
        finally {
            try {
                rs.close();
                statement.close();
                conn.close();
            } 
            catch (Exception ex) {
                new ErrorLog().LogError(ex.getMessage(), "GroupQueryModel", "rowset");
            }
        }
        
        return crs;
    }
}
