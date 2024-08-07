/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DAO.GroupDAO;
import DAO.ProjectDAO;
import DAO.ProjectStatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.Advisor;
import model.Group;
import model.Project;
import model.ProjectStatus;

/**
 *
 * @author Legion
 */
@WebServlet(name="AdvisorGroupController", urlPatterns={"/advisor-group"})
public class AdvisorGroupController extends HttpServlet {
   
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
            out.println("<title>Servlet AdvisorGroupController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdvisorGroupController at " + request.getContextPath () + "</h1>");
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
        int advisorID = ((Advisor) request.getSession().getAttribute("advisor")).getAdvisorID();
        String searchName = request.getParameter("searchName");
        if (searchName == null) {
            searchName = "";
        }
        int pageNumber = 1;
        int pageSize = 10; // Number of records per page
        if (request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        
        GroupDAO groupDAO = new GroupDAO();

        List<Group> groups = groupDAO.getGroups(pageNumber, pageSize, searchName, advisorID);
        int totalGroups = groupDAO.countGroups(searchName, advisorID);
        int totalPages = (int) Math.ceil((double) totalGroups / pageSize);

        request.setAttribute("groups", groups);
        request.setAttribute("totalGroups", totalGroups);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("searchName", searchName);

        request.getRequestDispatcher("advisor-group.jsp").forward(request, response);
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
