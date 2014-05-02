/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fancygiraffe.models;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import org.fancygiraffe.global.ErrorLog;
import org.fancygiraffe.global.HelperMethods;
import org.fancygiraffe.global.Connections;

/**
 *
 * @author Crackitybones
 */
public class AssetsModel {
	
	/**
	 * Gets all active assets
	 * @return CachedRowSet with assets rows
	 */
	//Nathan - 4/20/13
	public CachedRowSet getAllAssets() {
		CachedRowSet crs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		//String sql = "SELECT * FROM assets WHERE status = 1";
                
		String sql = "EXEC getAllAssets";
                
		try {
			crs = new CachedRowSetImpl();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
			ps = con.prepareStatement(sql);
			
			//Run statement
			rs = ps.executeQuery();
			crs.populate(rs);
						
		} catch (Exception ex) {
			new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAllAssets");
		} finally {//Close everything
			if(rs != null) {try {rs.close();} catch (SQLException ex) { }}
			if(ps != null) {try {ps.close();} catch (SQLException ex) { }}
			if(con != null) {try {con.close();} catch (SQLException ex) { }}
		}
		
		return crs;
	}
        
        /**
	 * Gets all active assets
	 * @return CachedRowSet with assets rows
	 */
	//Nathan - 4/20/13
	public CachedRowSet getAssetsByLocation(String locationId) {
		CachedRowSet crs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		//String sql = "SELECT * FROM assets WHERE status = 1 AND location_id = " + locationId;
		
                String sql = "EXEC getAssetsbyLocation ?";
                
		try {
			crs = new CachedRowSetImpl();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
			ps = con.prepareStatement(sql);
			
                        ps.setString(1, locationId);
                        
			//Run statement
			rs = ps.executeQuery();
			crs.populate(rs);
						
		} catch (Exception ex) {
			new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAssetsByLocation");
		} finally {//Close everything
			if(rs != null) {try {rs.close();} catch (SQLException ex) { }}
			if(ps != null) {try {ps.close();} catch (SQLException ex) { }}
			if(con != null) {try {con.close();} catch (SQLException ex) { }}
		}
		
		return crs;
	}
        
        /**
	 * Gets all active assets
	 * @return CachedRowSet with assets rows
	 */
	//Nathan - 4/20/13
	public CachedRowSet getAssetsByType(String asset_type) {
		CachedRowSet crs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		//String sql = "SELECT * FROM assets WHERE status = 1 AND asset_type = ?";
		
                String sql = "EXEC getAssetsbyType ?";
                
		try {
			crs = new CachedRowSetImpl();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
			ps = con.prepareStatement(sql);
			
                        ps.setString(1, asset_type);
                        
			//Run statement
			rs = ps.executeQuery();
			crs.populate(rs);
						
		} catch (Exception ex) {
			new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAssetsByType");
		} finally {//Close everything
			if(rs != null) {try {rs.close();} catch (SQLException ex) { }}
			if(ps != null) {try {ps.close();} catch (SQLException ex) { }}
			if(con != null) {try {con.close();} catch (SQLException ex) { }}
		}
		
		return crs;
	}
        
        /**
	 * Gets all active assets
	 * @return CachedRowSet with assets rows
	 */
	//Nathan - 4/20/13
	public CachedRowSet getAssetsById(String asset_id) {
		CachedRowSet crs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		//String sql = "SELECT * FROM assets WHERE status = 1 AND id = ?";
		
                String sql = "EXEC getAssetsbyId ?";
                
                System.out.print(sql);
                
		try {
			crs = new CachedRowSetImpl();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
			ps = con.prepareStatement(sql);
			
                        ps.setString(1, asset_id);
                        
			//Run statement
			rs = ps.executeQuery();
			crs.populate(rs);
						
		} catch (Exception ex) {
			new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAssetsById");
		} finally {//Close everything
			if(rs != null) {try {rs.close();} catch (SQLException ex) { }}
			if(ps != null) {try {ps.close();} catch (SQLException ex) { }}
			if(con != null) {try {con.close();} catch (SQLException ex) { }}
		}
		
		return crs;
	}
        
        public CachedRowSet getAssetsByLocationAndType(String location, String assetType) {
		CachedRowSet crs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		
                String sql = "EXEC getAssetsbyLocationAndType ?, ?";
                
                System.out.print(sql);
                
		try {
			crs = new CachedRowSetImpl();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
			ps = con.prepareStatement(sql);
			
                        ps.setString(1, location);
                        ps.setString(2, assetType);
                        
			//Run statement
			rs = ps.executeQuery();
			crs.populate(rs);
						
		} catch (Exception ex) {
			new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAssetsByLocationAndType");
		} finally {//Close everything
			if(rs != null) {try {rs.close();} catch (SQLException ex) { }}
			if(ps != null) {try {ps.close();} catch (SQLException ex) { }}
			if(con != null) {try {con.close();} catch (SQLException ex) { }}
		}
		
		return crs;
	}
        
        public CachedRowSet getAssetsTypes() {
		CachedRowSet crs = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
                
		String sql = "EXEC DISTINCT_TYPES";
		
		try {
			crs = new CachedRowSetImpl();
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");		
			con = DriverManager.getConnection(Connections.connectionUrl(), Connections.user(), Connections.pwd());
			ps = con.prepareStatement(sql);
			
			//Run statement
			rs = ps.executeQuery();
			crs.populate(rs);
						
		} catch (Exception ex) {
			new ErrorLog().LogError(ex.getMessage(), "AssetModel", "getAssetsTypes");
		} finally {//Close everything
			if(rs != null) {try {rs.close();} catch (SQLException ex) { }}
			if(ps != null) {try {ps.close();} catch (SQLException ex) { }}
			if(con != null) {try {con.close();} catch (SQLException ex) { }}
		}
		
		return crs;
	}
}
