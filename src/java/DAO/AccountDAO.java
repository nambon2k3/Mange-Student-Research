/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import model.Account;


/**
 *
 * @author Legion
 */
public class AccountDAO extends DBContext{
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    public AccountDAO() {
        try {
            con = getConnection();
        } catch (Exception ex) {
            System.out.println("Connect failed");
        }
    }

    
    
    
    public Account authenticateUser(String username, String password) {
        Account account = null;

        try {
            // Prepare SQL query to check username and hashed password
            String query = "SELECT * FROM Accounts WHERE Username = ? AND PasswordHash = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute query
            rs = stmt.executeQuery();

            // Check if a row was returned
            if (rs.next()) {
                // Retrieve account details from ResultSet
                int accountID = rs.getInt("AccountID");
                String email = rs.getString("Email");
                String userType = rs.getString("UserType");
                int studentID = rs.getInt("StudentID");
                int advisorID = rs.getInt("AdvisorID");

                // Create Account object
                account = new Account(accountID, username, password, email, userType, studentID, advisorID);
            }
        } catch (SQLException e) {
            System.out.println("authenticateUser: " + e.getMessage());
        }

        return account;
    }
    
}
