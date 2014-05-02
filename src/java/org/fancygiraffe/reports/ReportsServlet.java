///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package org.fancygiraffe.reports;

import com.sun.rowset.CachedRowSetImpl;
import company.citymanagerweb.models.DBManager;
import company.citymanagerweb.models.MySQLServerConnectionBehavior;
import company.citymanagerweb.models.ServerConnectionBehavior;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;
import org.fancygiraffe.global.*;
import org.fancygiraffe.global.Connections;
import org.fancygiraffe.models.*;


@WebServlet(name = "ReportsServlet", urlPatterns = {"/reports"})
public class ReportsServlet extends HttpServlet {

   /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
    */
    
    
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//           throws ServletException, IOException {
//
////        response.setContentType("text/html;charset=UTF-8");
////        RequestDispatcher view = request.getRequestDispatcher("reports.jsp");
////        view.forward(request, response);
//    }

//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP
//     * <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        //Get list of locations by name
//        AssetsModel am = new AssetsModel();
//        LocationModel lm = new LocationModel();
//        
//        CachedRowSet assets = am.getAllAssets();
//        CachedRowSet assetTypes = am.getAssetsTypes();
//        CachedRowSet locations = lm.getUniqueLocations();
//        
//        request.setAttribute("assets", assets);
//        request.setAttribute("assetTypes", assetTypes);
//        request.setAttribute("locations", locations);
//        
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP
//     * <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        AssetsModel am = new AssetsModel();
//        LocationModel lm = new LocationModel();
//        
//        CachedRowSet assetTypes = am.getAssetsTypes();
//        CachedRowSet locations = lm.getUniqueLocations();
//        
//        request.setAttribute("assetTypes", assetTypes);
//        request.setAttribute("locations", locations);
//        
//        String assetSearch = "" + request.getParameter("asset_search");
//        String location = "" + request.getParameter("location");
//        String type = "" + request.getParameter("type");
//
//        //PrintWriter out = response.getWriter();
//        
//        if ( !assetSearch.equals("") ) {
//            CachedRowSet crs = am.getAssetsById(assetSearch);
//            request.setAttribute("assets", crs);
//            
//        } else if ( !location.equals("") && !type.equals("") ) {
//            CachedRowSet crs = am.getAssetsByLocationAndType(location, type);
//            request.setAttribute("assets", crs);
//            
//        } else if ( !location.equals("") ) {
//            
//            CachedRowSet crs = am.getAssetsByLocation(location);
//            request.setAttribute("assets", crs);
//        } else if ( !type.equals("") ) {
//            
//            CachedRowSet crs = am.getAssetsByType(type);
//            request.setAttribute("assets", crs);
//            
//        }
//
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
    
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//generate the output in a StringBuilder
		StringBuilder sb = new StringBuilder("<html><body>");
		
		//get db con info from context init params
		String uid = getServletContext().getInitParameter("dbuserid");
		String pwd = getServletContext().getInitParameter("dbuserpwd");
		String cat = getServletContext().getInitParameter("dbinitcat");

		//set the scb for mySQL
		ServerConnectionBehavior scb = new MySQLServerConnectionBehavior(uid, pwd, cat);
		System.out.println(scb.getConnectionDetails());
		System.out.println(scb.getConnectionURL());
		
		//create the manager
		DBManager dbm = new DBManager(scb);
                
                
  CachedRowSet crs = null;
       
        Connection con = null;
        PreparedStatement ps = null;
        CallableStatement cstmt = null;
                
                        String query = "exec rptGetAssetbyLocation ?";
		
		try
		{
                     crs = new CachedRowSetImpl();
                    
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                                String url = "jdbc:sqlserver://petunia.arvixe.com;databaseName=WeberInventory3;selectMethod=cursor";
                                String username = "Weber";
                                String password = "weber";
                                
                                			sb.append("<table id=\"examplee\" border=1 >" 
						+ "<tr><td>ID</td><td>TAG</td><td>NAME</td>"
						+ "<td>MODEL</td><td>SERIAL NUMBER</td></tr>");
                                
                                
                                Connection conn = DriverManager.getConnection(url, username, password);
                                CallableStatement stmt = conn.prepareCall(query);
                                stmt.setString(1, "2122");
                                ResultSet rs = stmt.executeQuery();
                                
                               
                                
                                 
                              
                             
                                
              
                                
                                
			while (rs.next())
			{
				int id = rs.getInt("ID");
				String tag = rs.getString("tag");
				String name = rs.getString("name"); 
				String model = rs.getString("model"); 
				String serNum = rs.getString("serialNumber");
				
				sb.append("<tr><td>" + id + "</td>" 
						+ "<td>" + tag + "</td>" 
						+ "<td>" + name + "</td>" +
						"<td>" + model + "</td>" +
						"<td>" + serNum + "</td></tr>");
			}
			sb.append("</table>");
		}
		catch(Exception e)
		{
			sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
		}
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb);
	}



}
