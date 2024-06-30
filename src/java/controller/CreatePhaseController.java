/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.ProjectPhaseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.ProjectPhase;

/**
 *
 * @author Legion
 */
@WebServlet(name="CreatePhaseController", urlPatterns={"/create-phase"})
public class CreatePhaseController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreatePhaseController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePhaseController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Retrieve parameters from the form submission
        int projectID = Integer.parseInt(request.getParameter("projectID"));
        String phaseTitle = request.getParameter("phaseTitle");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        int statusID = Integer.parseInt(request.getParameter("statusID"));

        // Convert date strings to Date objects
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            System.out.println("CreatePhaseController: Date");
        }

        // Create a ProjectPhase object with retrieved data
        ProjectPhase phase = new ProjectPhase();
        phase.setProjectID(projectID);
        phase.setPhaseTitle(phaseTitle);
        phase.setStartDate(startDate);
        phase.setEndDate(endDate);
        phase.setStatusID(statusID);

        // Add the project phase to the database
        new ProjectPhaseDAO().addProjectPhase(phase);

        response.sendRedirect("project-detail?projectId=" + projectID);
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
