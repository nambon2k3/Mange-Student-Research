/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.GroupDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Project;
import DAO.ProjectDAO;
import DAO.ProjectStatusDAO;
import java.time.LocalDate;
import model.Advisor;
import model.Group;
import model.ProjectStatus;

/**
 *
 * @author Legion
 */
@WebServlet(name="AdvisorDashboardController", urlPatterns={"/advisor-dashboard"})
public class AdvisorDashboardController extends HttpServlet {
   
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
            out.println("<title>Servlet AdvisorDashboardController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdvisorDashboardController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int advisorID = ((Advisor) request.getSession().getAttribute("advisor")).getAdvisorID();
        int page = 1;
        if(request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        ProjectDAO projectDAO = new ProjectDAO();
        GroupDAO groupDAO = new GroupDAO();
        
        String projectTitle = request.getParameter("projectTitle");
        String statusIDStr = request.getParameter("statusID");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        int recordsPerPage = 10;

        Integer statusID = statusIDStr != null && !statusIDStr.isEmpty() ? Integer.parseInt(statusIDStr) : null;
        LocalDate startDate = startDateStr != null && !startDateStr.isEmpty() ? LocalDate.parse(startDateStr) : null;
        LocalDate endDate = endDateStr != null && !endDateStr.isEmpty() ? LocalDate.parse(endDateStr) : null;

        int noOfRecords = projectDAO.countProjectsByFilters(advisorID, projectTitle, statusID, startDate, endDate);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        List<Project> projects = projectDAO.getProjectsByFilters(advisorID, projectTitle, statusID, startDate, endDate, (page - 1) * recordsPerPage, recordsPerPage);
        List<ProjectStatus> statuses = new ProjectStatusDAO().getAllProjectStatuses();
        
        
        List<Group> groups = groupDAO.getGroupsCreatedByAdvisor(advisorID);
        
        request.setAttribute("projects", projects);
        request.setAttribute("groups", groups);
        request.setAttribute("statuses", statuses);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("projectTitle", projectTitle);
        request.setAttribute("statusID", statusID);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        request.getRequestDispatcher("advisor-dashboard.jsp").forward(request, response);
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
        processRequest(request, response);
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
