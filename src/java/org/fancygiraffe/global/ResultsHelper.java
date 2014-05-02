
package org.fancygiraffe.global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Jonathan Tew
 */
public class ResultsHelper {
    
    CachedRowSet crs = null;
    ResultSet rs = null;
    Connection con = null;
    PreparedStatement ps = null;
    String sql = null;
    int newAssetID;
    
    public ResultsHelper(String inputsql){
        sql = inputsql;
    }
    
    public ResultsHelper(String inputsql, int id){
        sql = inputsql;
        newAssetID = id;
    }

    public String getSql(){
        return sql;
    }
    
    public void setSql(String sql){
        this.sql = sql;
    }
    
    public void execute(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());

            //prepared stored procedure
            //String sSql = "EXEC INSERT_ASSET ?, ?, ?, ?, ?, ?, ?, ?, ?";
            ps = con.prepareStatement(this.getSql());

//            HelperMethods.setParam(newAsset, "locationName", ps, 1, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "inputAssetTag", ps, 2, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "description", ps, 3, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "model", ps, 4, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "sn", ps, 5, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "value", ps, 6, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "condition", ps, 7, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "type", ps, 8, Types.VARCHAR);
//            HelperMethods.setParam(newAsset, "notes", ps, 9, Types.VARCHAR);

            rs = ps.executeQuery();									//execute query

            while (rs.next()) {
                newAssetID = Integer.parseInt(rs.getString(1));		//get new asset ID from executed query
            }

            ps.clearParameters();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            new ErrorLog().LogError(e.getMessage(), "AssetModel", "addAsset");	//log errors
            newAssetID = 0;
        } finally {
            
            //closeConnections(con, ps, rs, "addAsset");
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    new ErrorLog().LogError(ex.getMessage(), "rs.close();", "addAsset");
                }
            }	//close everything
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    new ErrorLog().LogError(ex.getMessage(), "ps.close();", "addAsset");
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    new ErrorLog().LogError(ex.getMessage(), "con.close();", "addAsset");
                }
            }
            
        }
    }
 
}

