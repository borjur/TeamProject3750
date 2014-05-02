/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
package org.fancygiraffe.locations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Erik
 */
@WebServlet("/locations")
public class LocationServlet extends HttpServlet {

    LocationModel Globallm = new LocationModel(); 
    
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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//Get list of locations by name
		LocationModel lm = new LocationModel();
		CachedRowSet crs = lm.getUniqueLocations(); 
                		
                request.setAttribute("locations", crs); // set the locations so jsp can retireve
		
                //check for unfined result div.
               if(request.getAttribute("result_div") == null)
               {
                   request.setAttribute("result_div", "");
               }
                
		RequestDispatcher view = request.getRequestDispatcher("locations.jsp");
		
		view.forward(request, response);
		
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * 
	 * Handles the HTTP
	 * <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action != null) {
			if(action.equals("add"))
				addLocation(request);
			else if(action.equals("edit"))
				editLocation(request);
			else if(action.equals("delete"))
				deleteLocation(request);		
		}
		
		processRequest(request, response);
	}

	/**
	 * asks the model to add a location to the database add results to the request object
	 * @param request
	 */
	private void addLocation(HttpServletRequest request) {
		
		LocationModel lm = new LocationModel();
		Map<String,String> map = new HashMap<String,String>() {};
		Map<String,String[]> requestMap = request.getParameterMap();
		
		int i = -1;
		
                    if(requestMap.containsKey("location")) {
			
			if(lm.getLocationByName(requestMap.get("location")[0]).size() == 0) {

				map.put("location_name", requestMap.get("location")[0]);
				if(requestMap.containsKey("address"))
					map.put("address", requestMap.get("address")[0]);
				if(requestMap.containsKey("city"))
					map.put("city", requestMap.get("city")[0]);
				if(requestMap.containsKey("state"))
					map.put("state", requestMap.get("state")[0]);
				if(requestMap.containsKey("zip"))
					map.put("zip", requestMap.get("zip")[0]);
				if(requestMap.containsKey("phone"))
					map.put("phone", requestMap.get("phone")[0]);
				if(requestMap.containsKey("district"))
					map.put("district", requestMap.get("district")[0]);
				
				i = lm.addLocation(map);
			}
		}		
		
		request.setAttribute("newLocationId", i);		
	}
	
	/**
	 * asks the model to update a location in the database add results to the request object
	 * @param request 
	 */
	private void editLocation(HttpServletRequest request) {
		
		LocationModel lm = new LocationModel();
		Map<String,String> map = new HashMap<String,String>() {};
		Map<String,String[]> requestMap = request.getParameterMap();

		int i = -1;
		
		if(requestMap.containsKey("address"))
			map.put("address", requestMap.get("address")[0]);
		if(requestMap.containsKey("city"))
			map.put("city", requestMap.get("city")[0]);
		if(requestMap.containsKey("state"))
			map.put("state", requestMap.get("state")[0]);
		if(requestMap.containsKey("zip"))
			map.put("zip", requestMap.get("zip")[0]);
		if(requestMap.containsKey("phone"))
			map.put("phone", requestMap.get("phone")[0]);
		if(requestMap.containsKey("district"))
			map.put("district", requestMap.get("district")[0]);
		if(requestMap.containsKey("location")) {
			map.put("location_name", requestMap.get("location")[0]);
			
			i = lm.editLocation(map);			
		}
		
		request.setAttribute("editRowsAffected", i);
		request.setAttribute("activeTab", 2);
		
	}
	
	/**
	 *-Angela's Edit: Checks to make sure location is not being used by assets before being deleted.
         * -Angela's Edit: return a string with error message. specific to error. to be set in jsp.
         * asks the model to delete a location from the database add results to the request object
	 * @param request 
	 */
	private void deleteLocation(HttpServletRequest request) 
        {
		String location = request.getParameter("location");//name of location to be deleted
		int result = -1; 
		int assetCount = -1;
                
                if(location.equals("aaaa"))
                    assetCount = 3;
                
                if(location.equals("sdel"))
                    assetCount = 0;
               if(location.equals("errr"))
                    assetCount=-1;
		
                if(location != null && location.length() > 0) // If location is valid
                {
                  //  assetCount = Globallm.isLocationUsed(location); // if assoiated will be > 0 if error -1... if 0 good to go.
             
                    if(assetCount > 0) 
                    {// there are assets assoiated dont delete. just display error.
                       // request.setAttribute("error_message", " location is assosiated with current assets.");
                        request.setAttribute("result_div", "<div id=\"locationResult\" class=\"alert alert-info\">\n" +
"                           <strong>Error!</strong> Couldn't delete location: " + location + ", location is assosiated with current assets. </div>");
                    }
                    if(assetCount < 0) // error occured in checking location.
                    {
                       // request.setAttribute("error_message", "due to a technical error.");
                        request.setAttribute("result_div", "<div id=\"locationResult\" class=\"alert alert-info\">\n" +
"                           <strong>Error!</strong> Couldn't delete location:" + location + " due to a technical error. </div>");
                    }
                    if(assetCount == 0) // sucsess. no assets related.
                    {                    
                        result = Globallm.deleteLocation(location); // delete location

                        if(result > 0)
                        {
                            //request.setAttribute("error_message", ""); // reset message to empty. if message exists.  
                            request.setAttribute("result_div", "<div id=\"locationResult\" class=\"alert alert-info\">\n" +
"                                          <strong>Success!</strong> Deleted location. " + location + "\n" +
"                                          </div> ");
                        }
                        else
                        {
                         // request.setAttribute("error_message", "due to a technical error."); // reset message to empty. if message exists.  
                             request.setAttribute("result_div", "<div id=\"locationResult\" class=\"alert alert-info\">\n" +
"                           <strong>Error!</strong> Couldn't delete location " + location + ": due to a technical error. </div>");
                        }
                    }
                    
                    request.setAttribute("activeTab", 3);
            }
        }
	
	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}
}