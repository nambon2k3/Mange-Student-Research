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
import java.util.ArrayList;
import model.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Legion
 */
public class StudentDAO extends DBContext {
    
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
    
    public List<Student> getStudentsByGroupId(int groupID) {
        List<Student> students = new ArrayList<>();
        
        try {
            String query = "SELECT s.StudentID, s.FirstName, s.LastName, s.Email, s.EnrollmentDate, s.DepartmentID\n"
                    + "FROM Students s\n"
                    + "INNER JOIN GroupMembers gm ON s.StudentID = gm.StudentID\n"
                    + "WHERE gm.GroupID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, groupID);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int studentID = rs.getInt("StudentID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                Date enrollmentDate = rs.getDate("EnrollmentDate");
                int departmentID = rs.getInt("DepartmentID");
                
                Student student = new Student(studentID, firstName, lastName, email, enrollmentDate, departmentID);
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.out.println("Error in getStudentsByGroupId: " + e.getMessage());
        }
        
        return students;
    }
    
    public List<Student> getStudentsNotInGroupByGroupID(int groupID) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students WHERE groupID IS NULL OR groupID = ?";
        
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, groupID);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("studentID"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                // Set other attributes as needed

                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("getStudentsNotInGroupByGroupID: " + e.getMessage());
        }
        
        return students;
    }
    
    public List<Student> getStudentsNotInGroup(int groupID) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT StudentID, FirstName, LastName, Email, EnrollmentDate, DepartmentID FROM Students WHERE StudentID NOT IN (SELECT StudentID FROM GroupMembers where [GroupID] = ?)";
        
        try (PreparedStatement statement = conn.prepareStatement(query);) {
            statement.setInt(1, groupID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("StudentID"));
                student.setFirstName(resultSet.getString("FirstName"));
                student.setLastName(resultSet.getString("LastName"));
                student.setEmail(resultSet.getString("Email"));
                student.setEnrollmentDate(resultSet.getDate("EnrollmentDate"));
                student.setDepartmentID(resultSet.getInt("DepartmentID"));
                
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("getStudentsNotInGroup: " + e.getMessage());
        }
        
        return students;
    }

    public void assignStudentToGroup(int studentID, int groupID) {
        String query = "INSERT INTO GroupMembers (GroupID, StudentID) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, groupID);
            statement.setInt(2, studentID);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("assignStudentToGroup: " + e.getMessage());
        }
    }
    
}
