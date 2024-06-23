/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Legion
 */
public class Account {
    private int accountID;
    private String username;
    private String password;
    private String email;
    private String userType; // 'Student' or 'Advisor'
    private Integer studentID; // Nullable
    private Integer advisorID; // Nullable

    public Account() {
    }

    public Account(int accountID, String username, String password, String email, String userType, int studentID, int advisorID) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.studentID = studentID;
        this.advisorID = advisorID;
    }
    
    

    // Getters and Setters
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordHash(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public Integer getAdvisorID() {
        return advisorID;
    }

    public void setAdvisorID(Integer advisorID) {
        this.advisorID = advisorID;
    }
}
