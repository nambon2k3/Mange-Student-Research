/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.TaskDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Task;

/**
 *
 * @author Legion
 */
@WebServlet(name="UpdateTaskController", urlPatterns={"/update-task"})
public class UpdateTaskController extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateTaskController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateTaskController at " + request.getContextPath () + "</h1>");
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
        Task task = new Task();
        task.setTaskID(Integer.parseInt(request.getParameter("taskID")));
        task.setPhaseID(Integer.parseInt(request.getParameter("phaseID")));
        task.setTaskTitle(request.getParameter("taskTitle"));
        task.setDescription(request.getParameter("description"));
        task.setStartDate(Date.valueOf(request.getParameter("startDate")));
        task.setEndDate(Date.valueOf(request.getParameter("endDate")));
        task.setStatusID(Integer.parseInt(request.getParameter("statusID")));
        task.setAssignedTo(Integer.parseInt(request.getParameter("assignedTo")));
        
        String projectId = request.getParameter("projectId");
        
        TaskDAO taskDAO = new TaskDAO();
        boolean isSuccess = taskDAO.updateTask(task);
        response.sendRedirect("phase-task?isSuccess="+isSuccess+"&phaseID=" + task.getPhaseID() + "&projectId=" + projectId);
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
        doGet(request, response);
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
