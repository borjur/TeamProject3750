package org.fancygiraffe.assets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import org.fancygiraffe.global.*;

/**
 * This is a test by ME! - Angela 1/22/14
 *
 * @author Alexis
 */
/*
 * Hello World..lol!
 * 
 */
/*
 * Boris testing one two
 * 
 */
@WebServlet("/assets")
public class AssetServlet extends HttpServlet {
    
    public static CachedRowSet locationNames = null;
    public static Connections con = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get list of assets by name
        AssetModel am = new AssetModel();
        CachedRowSet crs = am.getUniqueAssets();
        request.setAttribute("assets", crs);

        RequestDispatcher view = request.getRequestDispatcher("assets.jsp");

        view.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * Handles the HTTP <code>POST</code> method.
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

        if (action != null) {
            switch (action) {
                case "add":
                    addAsset(request);
                    break;
                case "edit":
                    editAsset(request);
                    break;
                case "delete":
                    deleteAsset(request);
                    break;
                case "searchAssetTag":
                    getAssetById(request);
                    break;
                    
            }
        }

        processRequest(request, response);
    }

    /**
     * asks the model to add an asset to the database add results to the request
     * object
     *
     * @param request
     */
    private void addAsset(HttpServletRequest request) {

        AssetModel am = new AssetModel();
        Map<String, String> map = new HashMap<String, String>() {
        };
        Map<String, String[]> requestMap = request.getParameterMap();

        int i = -1;

        //checks to see if an asset with matching key exists.
        if (requestMap.containsKey("inputAssetTag")) {

            if (am.getAssetByName(requestMap.get("inputAssetTag")[0]).size() == 0) {

                map.put("inputAssetTag", requestMap.get("inputAssetTag")[0]);
                if (requestMap.containsKey("description")) {
                    map.put("description", requestMap.get("description")[0]);
                }
                if (requestMap.containsKey("model")) {
                    map.put("model", requestMap.get("model")[0]);
                }
                if (requestMap.containsKey("sn")) {
                    map.put("sn", requestMap.get("sn")[0]);
                }
                if (requestMap.containsKey("value")) {
                    map.put("value", requestMap.get("value")[0]);
                }
                if (requestMap.containsKey("locationName")) {
                    map.put("locationName", requestMap.get("locationName")[0]);
                }
                if (requestMap.containsKey("condition")) {
                    map.put("condition", requestMap.get("condition")[0]);
                }
                if (requestMap.containsKey("inputAssetTag")) {
                    map.put("inputAssetTag", requestMap.get("inputAssetTag")[0]);
                }

                i = am.addAsset(map);
            }
        }

        if (i > 0) {
            request.setAttribute("result", "Asset added successfully.");
        } else {
            request.setAttribute("result", "Couldn't add asset.");
        }

        request.setAttribute("id", i);
    }

    /**
     * asks the model to update an asset in the database add results to the
     * request object
     *
     * @param request
     */
    private void editAsset(HttpServletRequest request) {

        AssetModel lm = new AssetModel();
        Map<String, String> map = new HashMap<String, String>() {
        };
        Map<String, String[]> requestMap = request.getParameterMap();

        int i = -1;
        
        if (requestMap.containsKey("inputAssetTag")) {
            map.put("inputAssetTag", requestMap.get("inputAssetTag")[0]);
        }
        if (requestMap.containsKey("description")) {
            map.put("description", requestMap.get("description")[0]);
        }
        if (requestMap.containsKey("model")) {
            map.put("model", requestMap.get("model")[0]);
        }
        if (requestMap.containsKey("sn")) {
            map.put("sn", requestMap.get("sn")[0]);
        }
        if (requestMap.containsKey("type")) {
            map.put("type", requestMap.get("type")[0]);
        }
        if (requestMap.containsKey("value")) {
            map.put("value", requestMap.get("value")[0]);
        }
        if (requestMap.containsKey("location")) {
            map.put("location_name", requestMap.get("location")[0]);
        }
        if (requestMap.containsKey("condition")) {
            map.put("condition", requestMap.get("condition")[0]);
        }
        if (requestMap.containsKey("location")) {
            map.put("location_name", requestMap.get("location")[0]);

            i = lm.editAsset(map);
        }
        request.setAttribute("rowsAffected", i);
        request.setAttribute("activeTab", 2);

    }

    /**
     * asks the model to delete an asset from the database add results to the
     * request object
     *
     * @param request
     */
    private void deleteAsset(HttpServletRequest request) {

        AssetModel am = new AssetModel();
        String assetTag = request.getParameter("inputAssetTag");//tag of asset to be deleted

        int i = -1;
        
        if (assetTag != null && assetTag.length() > 0) {
            i = am.deleteAsset(assetTag);

        }
        request.setAttribute("rowsAffected", i);
        request.setAttribute("result", "Asset deleted successfully.");
        request.setAttribute("activeTab", 3);
    }
    
    // added by Jonathan Tew
    private void getAssetById(HttpServletRequest request) {
        AssetModel am = new AssetModel();
        String assetTag = request.getParameter("inputAssetTag");//tag of asset to be searched
        String requestType = request.toString();
        System.out.println(requestType);
        
        if (assetTag != null && assetTag.length() > 0) {
            //int i = am.getAssetByName(assetTag);
            //int i = am.deleteAsset(assetTag);
            //request.setAttribute("rowsAffected", i);
            request.setAttribute("result", "Search successfull.");
        }

        request.setAttribute("activeTab", 3);
        
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
