/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Advisor;

/**
 *
 * @author Legion
 */
public class AdvisorDAO extends DBContext{
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public AdvisorDAO() {
        try {
            conn = getConnection();
        } catch (Exception ex) {
            System.out.println("Connect failed");
        }
    }
    public Advisor getAdvisorById(int advisorID) {
        Advisor advisor = null;

        try {

            String query = "SELECT * FROM Advisors WHERE AdvisorID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, advisorID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("AdvisorID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                int departmentID = rs.getInt("DepartmentID");

                advisor = new Advisor(id, firstName, lastName, email, departmentID);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAdvisorById: " + e.getMessage());
        } 

        return advisor;
    }
}

