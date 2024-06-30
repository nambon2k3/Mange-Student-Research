/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public ProjectDAO() {
        try {
            conn = getConnection();
        } catch (Exception e) {
        }
    }

    public List<Project> getProjectsByFilters(int advisorID, String projectTitle, Integer statusID, LocalDate startDate, LocalDate endDate, int start, int total) {
        List<Project> projects = new ArrayList<>();

        try {

            String query
                    = "SELECT "
                    + "    ProjectID, "
                    + "    ProjectTitle, "
                    + "    Description, "
                    + "    StartDate, "
                    + "    EndDate, "
                    + "    AdvisorID, "
                    + "    StatusID, "
                    + "    GroupID "
                    + "FROM Projects "
                    + "WHERE AdvisorID = ? "
                    + (projectTitle != null && !projectTitle.isEmpty() ? "AND ProjectTitle LIKE ? " : "")
                    + (statusID != null ? "AND StatusID = ? " : "")
                    + (startDate != null ? "AND StartDate >= ? " : "")
                    + (endDate != null ? "AND EndDate <= ? " : "")
                    + "ORDER BY ProjectID DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            stmt = conn.prepareStatement(query);
            int paramIndex = 1;
            stmt.setInt(paramIndex++, advisorID);
            if (projectTitle != null && !projectTitle.isEmpty()) {
                stmt.setString(paramIndex++, "%" + projectTitle + "%");
            }
            if (statusID != null) {
                stmt.setInt(paramIndex++, statusID);
            }
            if (startDate != null) {
                stmt.setDate(paramIndex++, java.sql.Date.valueOf(startDate));
            }
            if (endDate != null) {
                stmt.setDate(paramIndex++, java.sql.Date.valueOf(endDate));
            }
            stmt.setInt(paramIndex++, start);
            stmt.setInt(paramIndex++, total);

            rs = stmt.executeQuery();

            while (rs.next()) {
                int projectID = rs.getInt("ProjectID");
                String projectTitleResult = rs.getString("ProjectTitle");
                String description = rs.getString("Description");
                Date projectStartDate = rs.getDate("StartDate");
                Date projectEndDate = rs.getDate("EndDate");
                int advisorId = rs.getInt("AdvisorID");
                int projectStatusID = rs.getInt("StatusID");
                int groupID = rs.getInt("GroupID");

                Project project = new Project(projectID, projectTitleResult, description, projectStartDate, projectEndDate, advisorId, projectStatusID, groupID);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Error in getProjectsByFilters: " + e.getMessage());
        }

        return projects;
    }

    public int countProjectsByFilters(int advisorID, String projectTitle, Integer statusID, LocalDate startDate, LocalDate endDate) {
        int count = 0;

        try {

            String query
                    = "SELECT COUNT(*) "
                    + "FROM Projects "
                    + "WHERE AdvisorID = ? "
                    + (projectTitle != null && !projectTitle.isEmpty() ? "AND ProjectTitle LIKE ? " : "")
                    + (statusID != null ? "AND StatusID = ? " : "")
                    + (startDate != null ? "AND StartDate >= ? " : "")
                    + (endDate != null ? "AND EndDate <= ? " : "");

            stmt = conn.prepareStatement(query);
            int paramIndex = 1;
            stmt.setInt(paramIndex++, advisorID);
            if (projectTitle != null && !projectTitle.isEmpty()) {
                stmt.setString(paramIndex++, "%" + projectTitle.trim() + "%");
            }
            if (statusID != null) {
                stmt.setInt(paramIndex++, statusID);
            }
            if (startDate != null) {
                stmt.setDate(paramIndex++, java.sql.Date.valueOf(startDate));
            }
            if (endDate != null) {
                stmt.setDate(paramIndex++, java.sql.Date.valueOf(endDate));
            }

            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in countProjectsByFilters: " + e.getMessage());
        }

        return count;
    }

    public Project getProjectById(int projectID) {
        Project project = null;

        try {
            String query = "SELECT * FROM Projects WHERE ProjectID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, projectID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String projectTitleResult = rs.getString("ProjectTitle");
                String description = rs.getString("Description");
                Date projectStartDate = rs.getDate("StartDate");
                Date projectEndDate = rs.getDate("EndDate");
                int advisorId = rs.getInt("AdvisorID");
                int projectStatusID = rs.getInt("StatusID");
                int groupID = rs.getInt("GroupID");
                project = new Project(projectID, projectTitleResult, description, projectStartDate, projectEndDate, advisorId, projectStatusID, groupID);
            }
        } catch (SQLException e) {
            System.out.println("Error in getProjectById: " + e.getMessage());
        }
        return project;
    }

    public List<Project> getProjectsByStudentId(int studentId, String search, int offset, int limit) {
        List<Project> projects = new ArrayList<>();
        try  {
            StringBuilder query = new StringBuilder("SELECT p.* FROM Projects p ")
                .append("JOIN GroupMembers gm ON p.GroupID = gm.GroupID ")
                .append("WHERE gm.StudentID = ? ");
            
            if (search != null && !search.trim().isEmpty()) {
                query.append("AND p.ProjectTitle LIKE ? ");
            }
            
            query.append("ORDER BY p.ProjectID ")
                .append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setInt(1, studentId);
            int paramIndex = 2;
            
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
            }
            
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int projectID = rs.getInt("ProjectID");
                String projectTitleResult = rs.getString("ProjectTitle");
                String description = rs.getString("Description");
                Date projectStartDate = rs.getDate("StartDate");
                Date projectEndDate = rs.getDate("EndDate");
                int advisorId = rs.getInt("AdvisorID");
                int projectStatusID = rs.getInt("StatusID");
                int groupID = rs.getInt("GroupID");
                Project project = new Project(projectID, projectTitleResult, description, projectStartDate, projectEndDate, advisorId, projectStatusID, groupID);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Error in getProjectsByStudentId: " + e.getMessage());
        }
        return projects;
    }

    public int getProjectCountByStudentId(int studentId, String search) {
        int count = 0;
        try  {
            StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Projects p ")
                .append("JOIN GroupMembers gm ON p.GroupID = gm.GroupID ")
                .append("WHERE gm.StudentID = ? ");
            
            if (search != null && !search.trim().isEmpty()) {
                query.append("AND p.ProjectTitle LIKE ? ");
            }

            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setInt(1, studentId);
            
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(2, "%" + search + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error in getProjectCountByStudentId: " + e.getMessage());
        }
        return count;
    }
    
    public void insertProject(Project project) {
        String insertSQL = "INSERT INTO Projects (ProjectTitle, Description, StartDate, EndDate, AdvisorID, StatusID, GroupID) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
             PreparedStatement statement = conn.prepareStatement(insertSQL)) {
            
            statement.setString(1, project.getProjectTitle());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            statement.setInt(5, project.getAdvisorID());
            statement.setInt(6, project.getStatusID());
            statement.setInt(7, project.getGroupID());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insertProject: " + e.getMessage());
        }
    }
    
    
    public void updateProject(Project project) {
        String updateSQL = "UPDATE Projects SET ProjectTitle=?, Description=?, StartDate=?, EndDate=?, AdvisorID=?, StatusID=?, GroupID=? " +
                           "WHERE ProjectID=?";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            
            statement.setString(1, project.getProjectTitle());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            statement.setInt(5, project.getAdvisorID());
            statement.setInt(6, project.getStatusID());
            statement.setInt(7, project.getGroupID());
            statement.setInt(8, project.getProjectID());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateProject: " + e.getMessage());
        }
    }
}
