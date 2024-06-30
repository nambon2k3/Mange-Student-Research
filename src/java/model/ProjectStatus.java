/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Legion
 */
public class ProjectStatus {
    private int statusID;
    private String statusName;

    public ProjectStatus() {
    }

    public ProjectStatus(int statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }
    

    // Getters and Setters
    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

