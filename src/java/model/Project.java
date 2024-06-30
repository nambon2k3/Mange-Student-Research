/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.AdvisorDAO;
import DAO.ProjectStatusDAO;
import java.util.Date;

/**
 *
 * @author Legion
 */
public class Project {
    private int projectID;
    private String projectTitle;
    private String description;
    private Date startDate;
    private Date endDate;
    private int advisorID;
    private int statusID;
    private int groupID;
    
    private ProjectStatus projectStatus;
    private Advisor advisor;

    public Project() {
    }

    public Project(int projectID, String projectTitle, String description, Date startDate, Date endDate, int advisorID, int statusID, int groupID) {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.advisorID = advisorID;
        this.statusID = statusID;
        this.groupID = groupID;
    }

    public Advisor getAdvisor() {
        return new AdvisorDAO().getAdvisorById(advisorID);
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
    
    

    public ProjectStatus getProjectStatus() {
        return new ProjectStatusDAO().getProjectStatusById(statusID);
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
    
    // Getters and Setters
    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAdvisorID() {
        return advisorID;
    }

    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
