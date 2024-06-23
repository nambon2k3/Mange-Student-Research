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
import model.*;
import java.util.Date;

/**
 *
 * @author Legion
 */
public class StudentDAO extends DBContext{
    
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public StudentDAO() {
        try {
            conn = getConnection();
        } catch (Exception ex) {
            System.out.println("Connect failed");
        }
    }

    
    public Student getStudentByStudentID(int studentID) {

        Student student = null;

        try {

            String query = "SELECT * FROM Students WHERE StudentID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                Date enrollmentDate = rs.getDate("EnrollmentDate");
                int departmentID = rs.getInt("DepartmentID");

                student = new Student(id, firstName, lastName, email, enrollmentDate, departmentID);
            }
        } catch (SQLException e) {
            System.out.println("getStudentByStudentID: " + e.getMessage());
        }

        return student;
    }
    
}
