package org.fancygiraffe.assets;

import java.sql.*;
import java.util.Map;

import java.util.HashMap;

import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;
import org.fancygiraffe.global.*;

/**
 *
 * @author Alexis
 */
public class AssetModel {
    
    
    /*22222222
     * testing
     */
    public AssetModel() {

    }

    //condensed connection closing method
    // Jonathan Tew
    public void closeConnections(Connection con, PreparedStatement ps, ResultSet rs, String method)
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                new ErrorLog().LogError(ex.getMessage(), "rs.close();", method);
            }
        }	//close everything
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                new ErrorLog().LogError(ex.getMessage(), "ps.close();", method);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                new ErrorLog().LogError(ex.getMessage(), "con.close();", method);
            }
        }
    }
   
    //Jonathan Tew
    //Second close connections method.
    // 2-16-2014
    public static void closeConnections(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            //System.out.println("rs.close();");
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            //System.out.println("stmt.close();");
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            //System.out.println("conn.close();");
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
    * Adds a new asset to the database
    * @param Map<String,String> newAsset contains the values for the new asset
    * Map will have the following keys
    * <br/>String    description
    * <br/>String    model
    * <br/>String    serialnumber
    * <br/>String    assettype
    * <br/>String    value
    * <br/>String    location
    * <br/>String    condition
    * <br/>String    assettag
    * <br/>String    notes
    * 
    * @return int with the assetID of the newly created asset
    * 
    * Alexis F. 4-17-13
    */
    public int addAsset(Map<String, String> newAsset) {
        int newAssetID = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // not returning resut set returning int
        int ret = -1;
      //  int rs = 0;
        //String sSql = "EXEC INSERT_ASSET ?, ?, ?, ?, ?, ?, ?, ?, ?";
        //ResultsHelper results = new ResultsHelper(sSql, 0);
        //results.execute();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());

            //prepared stored procedure
            String sSql = "EXEC INSERT_ASSET ?, ?, ?, ?, ?, ?, ?, ?, ?";
            ps = con.prepareStatement(sSql);

            HelperMethods.setParam(newAsset, "locationName", ps, 1, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "inputAssetTag", ps, 2, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "description", ps, 3, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "model", ps, 4, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "sn", ps, 5, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "value", ps, 6, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "condition", ps, 7, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "type", ps, 8, Types.VARCHAR);
            HelperMethods.setParam(newAsset, "notes", ps, 9, Types.VARCHAR);
            
           
            newAssetID = ps.executeUpdate();
           // rs = ps.executeQuery();									//execute query

           // while (rs.next()) {
            //    newAssetID = Integer.parseInt(rs.getString(1));		//get new asset ID from executed query
          //  }

            ps.clearParameters();
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
            new ErrorLog().LogError(e.getMessage(), "AssetModel", "addAsset");	//log errors
            newAssetID = 0;
        } finally {
            closeConnections(con, ps, rs, "addAsset");
        }

        return newAssetID;
    }

    /**
     * Updates the values of an asset in the database
     * @param Map<String,String> newAssetValues contains the edited values
     * Map will have the following keys
     * <br/>String    tag
     * <br/>String    locationid
     * <br/>String    name
     * <br/>String    model
     * <br/>String    serial
     * <br/>String    value
     * <br/>String    condition
     * <br/>String    type
     * 
     * @returns int with the number of rows affected
     * 
     * Alexis F. 4-17-13
     */
    public int editAsset(Map<String, String> newAssetValues) {
        int iRowsAffected = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(),
                    Connections.user(), Connections.pwd());

            //prepared stored procedure
            String sSql = "EXEC EDIT_ASSET ?, ?, ?, ?, ?, ?, ?, ?, ?";
            ps = con.prepareStatement(sSql);

            HelperMethods.setParam(newAssetValues, "locationName", ps, 1, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "inputAssetTag", ps, 2, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "description", ps, 3, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "model", ps, 4, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "sn", ps, 5, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "value", ps, 6, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "condition", ps, 7, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "type", ps, 8, Types.VARCHAR);
            HelperMethods.setParam(newAssetValues, "notes", ps, 9, Types.VARCHAR);

            iRowsAffected = ps.executeUpdate();									//execute query

            ps.clearParameters();
        } catch (ClassNotFoundException | SQLException e) {
            new ErrorLog().LogError(e.getMessage(), "AssetModel", "editAsset");	//log errors
            iRowsAffected = 0;
        } finally {
            closeConnections(con, ps, rs, "editAsset");
        }

        return iRowsAffected;
    }

    /**LogError(ex.getMessage(), "con.close();", "editAsset");
                }
            }
        }

        return iRowsAffected;
    }

     /**
      * Deletes an asset from the database
      * @param String assetTag contains the assetTag of the asset to be deleted
      * 
      * @returns int with the number of rows affected
      * 
      * Alexis F. 4-17-13
      */
    public int deleteAsset(String assetTag) {
        int iRowsAffected = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());

            //prepared stored procedure
            String sSql = "EXEC DELETE_ASSET ? ";
            ps = con.prepareStatement(sSql);

            ps.setString(1, assetTag);								//add assetTag to the query

            iRowsAffected = ps.executeUpdate();						//execute query                      

            ps.clearParameters();
        } catch (ClassNotFoundException | SQLException e) {
            new ErrorLog().LogError(e.getMessage(), "AssetModel", "deleteAsset");	//log errors
            iRowsAffected = 0;
        } finally {
            closeConnections(con, ps, rs, "deleteAsset");
        }

        return iRowsAffected;
    }

    /**
    * Gets unique asset_types to populate drop-down list
    * 
    * @returns CachedRowSet with unique asset_types as strings
    * 
    * Alexis F. 4-21-13
    */
    public CachedRowSet getUniqueTypes() {
        CachedRowSet crs = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;

        String deleteSql = "EXEC DISTINCT_TYPES";

        try {
            crs = new CachedRowSetImpl();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
            ps = con.prepareStatement(deleteSql);

            //Run statement
            rs = ps.executeQuery();
            crs.populate(rs);

        } catch (ClassNotFoundException | SQLException ex) {
            new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getUniqueTypes");
            crs = null;
        } finally {
            closeConnections(con, ps, rs, "getUniqueTypes");
        }

        return crs;
    }

   /**
   * Gets unique locations to populate drop-down list
   * 
   * @returns CachedRowSet with unique asset_types as strings
   * 
   * Alexis F. 4-21-13
   */
/* commented out old unique locations - Jonathan Tew    */
//    public CachedRowSet getUniqueLocations() {
//        CachedRowSet crs = null;
//        ResultSet rs = null;
//        Connection con = null;
//        PreparedStatement ps = null;
//
//        String deleteSql = "EXEC DISTINCT_LOCATIONS";
//
//        // sql "select distinct location_name from locations"
//        
//        try {
//            crs = new CachedRowSetImpl();
//
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
//            ps = con.prepareStatement(deleteSql);
//
//            //Run statement
//            rs = ps.executeQuery();
//            crs.populate(rs);
//     
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getUniqueLocations");
//        } finally {//Close everything
//            closeConnections(con, ps, rs, "getUniqueLocations");
//        }
//
//        return crs;
//    }
    
    
    
    //new code for CS 3750
    //TODO fix it
    public CachedRowSet getUniqueAssets() 
    {
        CachedRowSet crs = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        
        //TODO - fix SQL statement
        String deleteSql = "EXEC getUniqueAssets";

        try {
            crs = new CachedRowSetImpl();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
            ps = con.prepareStatement(deleteSql);

            //Run statement
            rs = ps.executeQuery();
            crs.populate(rs);

        } catch (ClassNotFoundException | SQLException ex) {
            new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getUniqueAssets");
            
            crs = null;
        } finally {//Close everything
            closeConnections(con, ps, rs, "getUniqueAssets");
        }

        return crs;
        //throw new UnsupportedOperationException("getUniqueAssets - not implemented"); //To change body of generated methods, choose Tools | Templates.
    }

    public CachedRowSet getAssetByName(String assetName) 
    {
        CachedRowSet crs = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        
        //TODO - fix SQL statement
        
        //Stored procedure getAssetByName created to replace this DSQL -Steven P
        
        //String deleteSql = " select * where asset_name = " + assetName;
        String deleteSql = "exec getAssetByName ?";
        try {
            crs = new CachedRowSetImpl();

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
            ps = con.prepareStatement(deleteSql);
            
            ps.setString(1, assetName);
            //Run statement
            rs = ps.executeQuery();
            crs.populate(rs);

        } catch (ClassNotFoundException | SQLException ex) {
            new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAssetByName");
            crs = null;
        } finally {
            closeConnections(con, ps, rs, "getAssetByName");
        }

        return crs;
    }
    
//    public ResultSet getUniqueLocations()
//    {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        String sql = "exec getUniqueLocations";
//
//        try {
//            conn = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
//            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                String columnValue = rs.getString("location_name");
//                //System.out.println(columnValue);
//            }
//
//            //rs.last();
//            //System.out.println("Number of rows: " + rs.getRow());
//
//	//System.out.println("Connected!");
//        } catch (SQLException e) {
//            System.err.println(e);
//        } finally {
//            closeConnections(rs, stmt, conn);
//        }
//
//        return rs;
//    }

    public static void main(String[] args) {

        /*
         AssetModel am = new AssetModel();
         Map<String,String> map = new HashMap<String,String>() {};
            
            
         map.put("inputAssetTag", "test2");
         map.put("locationName", "office");
         map.put("model", "model");
         map.put("sn", "sn");
         map.put("type", "typu");
         map.put("value", "2.00");
         map.put("description", "descuriptuon");
         map.put("condition", "condurishion");
         map.put("notes", null );
            
            
         System.out.println( am.editAsset(map) );
         */
        /*AssetModel am = new AssetModel();
         Map<String,String> map = new HashMap<String,String>() {};
            
            
         map.put("inputAssetTag", "tag6");
         map.put("locationName", "office");
         map.put("model", "model");
         map.put("sn", "sn");
         map.put("type", "typu");
         map.put("value", "2.00");
         map.put("description", "descuriptuon");
         map.put("condition", "condurishion");
         map.put("notes", "noto");
            
            
         System.out.println( am.addAsset(map) );*/
    }

}
