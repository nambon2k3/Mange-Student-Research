/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Project;
/**
 *
 * @author Legion
 */
public class ProjectDAO extends DBContext {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    private int noOfRecords = 0;

    public ProjectDAO() {
        try {
            conn = getConnection();
        } catch (Exception e) {
        }
    }

    public List<Project> getAllProjectsByAdvisorId(int advisorID) {

        List<Project> projects = new ArrayList<>();

        try {
            String query = "SELECT [ProjectID], [ProjectTitle], [Description], [StartDate], [EndDate], [AdvisorID], [StatusID], [GroupID] "
                    + "FROM [StudentResearchDB].[dbo].[Projects] WHERE [AdvisorID] = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, advisorID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int projectID = rs.getInt("ProjectID");
                String projectTitle = rs.getString("ProjectTitle");
                String description = rs.getString("Description");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                int advisorId = rs.getInt("AdvisorID");
                int statusID = rs.getInt("StatusID");
                int groupID = rs.getInt("GroupID");

                Project project = new Project(projectID, projectTitle, description, startDate, endDate, advisorId, statusID, groupID);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAllProjectsByAdvisorId: " + e.getMessage());
        } 
        return projects;
    }
    
    public List<Project> getProjectsByAdvisorId(int advisorID, int start, int total) {
        List<Project> projects = new ArrayList<>();

        try {

            String query = "SELECT SQL_CALC_FOUND_ROWS * FROM Projects WHERE AdvisorID = ? LIMIT ?, ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, advisorID);
            stmt.setInt(2, start);
            stmt.setInt(3, total);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int projectID = rs.getInt("ProjectID");
                String projectTitle = rs.getString("ProjectTitle");
                String description = rs.getString("Description");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                int advisorId = rs.getInt("AdvisorID");
                int statusID = rs.getInt("StatusID");
                int groupID = rs.getInt("GroupID");

                Project project = new Project(projectID, projectTitle, description, startDate, endDate, advisorId, statusID, groupID);
                projects.add(project);
            }

            rs.close();

            stmt = conn.prepareStatement("SELECT FOUND_ROWS()");
            rs = stmt.executeQuery();
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in getProjectsByAdvisorId: " + e.getMessage());
        } 

        return projects;
    }
    
    public int getNoOfRecords(int advisorID) {
        return noOfRecords;
    }

}
