/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fancygiraffe.groups;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;
import org.fancygiraffe.global.ErrorLog;

/**
 *
 * @author Jimmy
 */
public class GroupServlet extends HttpServlet {

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
        
        RequestDispatcher view = request.getRequestDispatcher("groups.jsp");
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
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
            if(action.equals("create"))
                createGroup(request);
            else if (action.equals("dissolve"))
                dissolveGroup(request);
        }
        
        processRequest(request, response);
    }
    
    private void createGroup(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        
        CachedRowSet crs = (CachedRowSet)session.getAttribute("sessionCrs");
        
        GroupModel gm = new GroupModel();
        int count = 0;
        if (crs != null) {
            count = gm.createGroup(crs);
        }
        
		request.setAttribute("activeTab", 1);
        request.setAttribute("count", count);
    }
    
    private void dissolveGroup(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        
        CachedRowSet crs = (CachedRowSet)session.getAttribute("sessionCrs");
        
        GroupModel gm = new GroupModel();
        int count = 0;
        if (crs != null) {
            count = gm.dissolveGroup(crs);
        }
        
		request.setAttribute("activeTab", 3);
        request.setAttribute("count", count);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
