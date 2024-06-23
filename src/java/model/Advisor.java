/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Legion
 */
public class Advisor {
    private int advisorID;
    private String firstName;
    private String lastName;
    private String email;
    private int departmentID;

    public Advisor() {
    }

    public Advisor(int advisorID, String firstName, String lastName, String email, int departmentID) {
        this.advisorID = advisorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentID = departmentID;
    }
    
    

    // Getters and Setters
    public int getAdvisorID() {
        return advisorID;
    }

    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}

