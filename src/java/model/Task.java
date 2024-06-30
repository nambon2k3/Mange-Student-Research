/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.ProjectStatusDAO;
import DAO.StudentDAO;
import java.sql.Date;

/**
 *
 * @author Legion
 */
public class Task {
    private int taskID;
    private int phaseID;
    private String taskTitle;
    private String description;
    private Date startDate;
    private Date endDate;
    private int statusID;
    private int assignedTo;
    private String filePath;
    
    private ProjectStatus taskStatus;
    private Student student;

    public Task() {
    }

    public Task(int taskID, int phaseID, String taskTitle, String description, Date startDate, Date endDate, int statusID, int assignedTo) {
        this.taskID = taskID;
        this.phaseID = phaseID;
        this.taskTitle = taskTitle;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusID = statusID;
        this.assignedTo = assignedTo;
    }

    public Task(int taskID, int phaseID, String taskTitle, String description, Date startDate, Date endDate, int statusID, int assignedTo, String filePath) {
        this.taskID = taskID;
        this.phaseID = phaseID;
        this.taskTitle = taskTitle;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusID = statusID;
        this.assignedTo = assignedTo;
        this.filePath = filePath;
        this.taskStatus = taskStatus;
        this.student = student;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    

    public Student getStudent() {
        return new StudentDAO().getStudentByStudentID(assignedTo);
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
    public ProjectStatus getTaskStatus() {
        return new ProjectStatusDAO().getProjectStatusById(statusID);
    }

    public void setTaskStatus(ProjectStatus projectStatus) {
        this.taskStatus = projectStatus;
    }

    // Getters and Setters
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getPhaseID() {
        return phaseID;
    }

    public void setPhaseID(int phaseID) {
        this.phaseID = phaseID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
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

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }
}

