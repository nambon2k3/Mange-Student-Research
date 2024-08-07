/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.ProjectDAO;
import DAO.ProjectPhaseDAO;
import DAO.ProjectStatusDAO;
import DAO.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Project;
import model.ProjectPhase;
import model.ProjectStatus;
import model.Student;

/**
 *
 * @author Legion
 */
@WebServlet(name="AdvisorProjectDetailController", urlPatterns={"/project-detail"})
public class AdvisorProjectDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet ProjectDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProjectDetailController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int projectID = Integer.parseInt(request.getParameter("projectId")); // Assuming projectID is passed as a parameter

        ProjectDAO projectDAO = new ProjectDAO();
        ProjectPhaseDAO phaseDAO = new ProjectPhaseDAO();
        StudentDAO studentDAO = new StudentDAO();
        List<ProjectStatus> statuses = new ProjectStatusDAO().getAllProjectStatuses();
        Project project = projectDAO.getProjectById(projectID);
        List<Student> students = studentDAO.getStudentsByGroupId(project.getGroupID());
        List<ProjectPhase> phases = phaseDAO.getPhasesByProjectId(projectID);
        
        request.setAttribute("project", project);
        request.setAttribute("statuses", statuses);
        request.setAttribute("students", students);
        request.setAttribute("phases", phases);
        request.getRequestDispatcher("advisor-project-detail.jsp").forward(request, response);
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
