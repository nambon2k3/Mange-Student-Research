/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Legion
 */
public class Group {
    private int groupID;
    private String groupName;
    private int createdBy;

    public Group() {
    }

    public Group(int groupID, String groupName, int createdBy) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.createdBy = createdBy;
    }
    
    

    // Getters and Setters
    
    
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

