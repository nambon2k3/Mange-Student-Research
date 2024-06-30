/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.ProjectStatusDAO;
import java.util.Date;

/**
 *
 * @author Legion
 */
public class ProjectPhase {
    private int phaseID;
    private int projectID;
    private String phaseTitle;
    private Date startDate;
    private Date endDate;
    private int statusID;
    
    private ProjectStatus projectPhraseStatus;

    public ProjectPhase() {
    }

    public ProjectPhase(int phaseID, int projectID, String phaseTitle, Date startDate, Date endDate, int statusID) {
        this.phaseID = phaseID;
        this.projectID = projectID;
        this.phaseTitle = phaseTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusID = statusID;
    }
    
    
    public ProjectStatus getProjectPhraseStatus() {
        return new ProjectStatusDAO().getProjectStatusById(statusID);
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectPhraseStatus = projectStatus;
    }
    

    // Getters and Setters
    public int getPhaseID() {
        return phaseID;
    }

    public void setPhaseID(int phaseID) {
        this.phaseID = phaseID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getPhaseTitle() {
        return phaseTitle;
    }

    public void setPhaseTitle(String phaseTitle) {
        this.phaseTitle = phaseTitle;
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

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
}
