/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ProjectPhase;

/**
 *
 * @author Legion
 */
public class ProjectPhaseDAO extends DBContext {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public ProjectPhaseDAO() {
        try {
            conn = getConnection();
        } catch (Exception e) {
            System.out.println("Connect fail");
        }
    }

    public List<ProjectPhase> getPhasesByProjectId(int projectID) {
        List<ProjectPhase> phases = new ArrayList<>();

        try {
            String query = "SELECT * FROM [ProjectPhases] WHERE ProjectID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, projectID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int phaseID = rs.getInt("PhaseID");
                String phaseTitle = rs.getString("PhaseTitle");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                int statusID = rs.getInt("StatusID");

                ProjectPhase phase = new ProjectPhase(phaseID, projectID, phaseTitle, startDate, endDate, statusID);
                phases.add(phase);
            }
        } catch (SQLException e) {
            System.out.println("Error in getPhasesByProjectId: " + e.getMessage());
        }

        return phases;
    }
    
    public ProjectPhase getPhaseById(int phaseID) {
        ProjectPhase phase = null;

        try {
            String query = "SELECT ProjectID, PhaseTitle, StartDate, EndDate, StatusID " +
                           "FROM ProjectPhases " +
                           "WHERE PhaseID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, phaseID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int projectID = rs.getInt("ProjectID");
                String phaseTitle = rs.getString("PhaseTitle");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                int statusID = rs.getInt("StatusID");

                phase = new ProjectPhase(phaseID, projectID, phaseTitle, startDate, endDate, statusID);
            }
        } catch (SQLException e) {
            System.out.println("Error in getPhaseById: " + e.getMessage());
        }

        return phase;
    }
    
    public boolean addProjectPhase(ProjectPhase phase) {
        String query = "INSERT INTO ProjectPhases (ProjectID, PhaseTitle, StartDate, EndDate, StatusID) " +
                       "VALUES (?, ?, ?, ?, ?)";
        boolean success = false;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, phase.getProjectID());
            statement.setString(2, phase.getPhaseTitle());
            statement.setDate(3, new java.sql.Date(phase.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(phase.getEndDate().getTime()));
            statement.setInt(5, phase.getStatusID());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("addProjectPhase: " + e.getMessage());
        }

        return success;
    }
    
     public boolean updateProjectPhase(ProjectPhase phase) {
        String query = "UPDATE ProjectPhases SET ProjectID=?, PhaseTitle=?, StartDate=?, EndDate=?, StatusID=? " +
                       "WHERE PhaseID=?";
        boolean success = false;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, phase.getProjectID());
            statement.setString(2, phase.getPhaseTitle());
            statement.setDate(3, new java.sql.Date(phase.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(phase.getEndDate().getTime()));
            statement.setInt(5, phase.getStatusID());
            statement.setInt(6, phase.getPhaseID());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("updateProjectPhase: " + e.getMessage());
        }

        return success;
    }
}
