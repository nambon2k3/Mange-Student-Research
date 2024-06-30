/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.ProjectStatus;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Legion
 */
public class ProjectStatusDAO extends DBContext {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public ProjectStatusDAO() {
        try {
            conn = getConnection();
        } catch (Exception e) {
            System.out.println("Connect fail");
        }
    }

    public List<ProjectStatus> getAllProjectStatuses() {

        List<ProjectStatus> statuses = new ArrayList<>();

        try {
            String query = "SELECT StatusID, StatusName FROM ProjectStatuses ORDER BY StatusID";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int statusID = rs.getInt("StatusID");
                String statusName = rs.getString("StatusName");

                ProjectStatus status = new ProjectStatus(statusID, statusName);
                statuses.add(status);
            }
        } catch (SQLException e) {
            System.out.println("Error in getAllProjectStatuses: " + e.getMessage());
        }
        return statuses;
    }
    
    public ProjectStatus getProjectStatusById(int statusID) {
        ProjectStatus status = null;
        try {
            String query = "SELECT StatusID, StatusName FROM ProjectStatuses WHERE StatusID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, statusID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String statusName = rs.getString("StatusName");

                status = new ProjectStatus(statusID, statusName);
            }
        } catch (SQLException e) {
            System.out.println("Error in getProjectStatusById: " + e.getMessage());
        }

        return status;
    }
}
