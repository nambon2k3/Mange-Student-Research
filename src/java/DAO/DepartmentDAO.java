/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Department;

/**
 *
 * @author Legion
 */



public class DepartmentDAO extends DBContext{
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public DepartmentDAO() {
        try {
            conn = getConnection();
        } catch (Exception ex) {
            System.out.println("Connect failed");
        }
    }
    
    public Department getDepartmentById(int departmentID) {
        Department department = null;

        try {

            String query = "SELECT DepartmentID, DepartmentName FROM Departments WHERE DepartmentID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, departmentID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("DepartmentID");
                String name = rs.getString("DepartmentName");

                department = new Department(id, name); // Assuming Department class constructor
            }
        } catch (SQLException e) {
            System.out.println("Error in getDepartmentById: " + e.getMessage());
        }

        return department;
    }
}
