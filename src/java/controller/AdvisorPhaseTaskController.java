/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ProjectDAO;
import DAO.ProjectPhaseDAO;
import DAO.ProjectStatusDAO;
import DAO.StudentDAO;
import DAO.TaskDAO;
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
import model.Task;

/**
 *
 * @author Legion
 */
@WebServlet(name = "AdvisorPhaseTaskController", urlPatterns = {"/phase-task"})
public class AdvisorPhaseTaskController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PhraseTaskController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PhraseTaskController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int phaseID = Integer.parseInt(request.getParameter("phaseID")); // Extract phaseID from URL path
        int projectId = Integer.parseInt(request.getParameter("projectId")); // Extract projectId from URL path
        
        TaskDAO taskDAO = new TaskDAO();
        StudentDAO studentDAO = new StudentDAO();
        ProjectDAO projectDAO = new ProjectDAO();
        
        List<Task> tasks = taskDAO.getTasksByPhaseId(phaseID);
        ProjectPhase phase = new ProjectPhaseDAO().getPhaseById(phaseID);
        
        Project project = projectDAO.getProjectById(projectId);
        List<Student> students = studentDAO.getStudentsByGroupId(project.getGroupID());
        List<ProjectStatus> statuses = new ProjectStatusDAO().getAllProjectStatuses();
        
        
        request.setAttribute("tasks", tasks);
        request.setAttribute("project", project);
        request.setAttribute("statuses", statuses);
        request.setAttribute("students", students);
        request.setAttribute("phase", phase);
        request.setAttribute("phaseID", phaseID); 
        request.setAttribute("isSuccess", request.getParameter("isSuccess")); 
        request.getRequestDispatcher("/advisor-phase-task.jsp").forward(request, response);
    }

    /**
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
        processRequest(request, response);
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
