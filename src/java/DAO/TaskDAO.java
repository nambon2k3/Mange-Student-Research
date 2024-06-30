/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Task;

/**
 *
 * @author Legion
 */
public class TaskDAO extends DBContext {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public TaskDAO() {
        try {
            conn = getConnection();
        } catch (Exception ex) {
            System.out.println("Connect failed");
        }
    }

    public List<Task> getTasksByPhaseId(int phaseID) {
        List<Task> tasks = new ArrayList<>();

        try {

            String query = "SELECT TaskID, PhaseID, TaskTitle, Description, StartDate, EndDate, StatusID, AssignedTo, filePath "
                    + "FROM Tasks "
                    + "WHERE PhaseID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, phaseID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int taskID = rs.getInt("TaskID");
                String taskTitle = rs.getString("TaskTitle");
                String description = rs.getString("Description");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                int statusID = rs.getInt("StatusID");
                int assignedTo = rs.getInt("AssignedTo");
                String filePath = rs.getString("filePath");

                Task task = new Task(taskID, phaseID, taskTitle, description, startDate, endDate, statusID, assignedTo,filePath);
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error in getTasksByPhaseId: " + e.getMessage());
        }

        return tasks;
    }
    
    public List<Task> getTasksByPhaseIdAndStudnetId(int phaseID, int studentId) {
        List<Task> tasks = new ArrayList<>();

        try {

            String query = "SELECT TaskID, PhaseID, TaskTitle, Description, StartDate, EndDate, StatusID, AssignedTo, filePath "
                    + "FROM Tasks "
                    + "WHERE PhaseID = ? and AssignedTo = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, phaseID);
            stmt.setInt(2, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int taskID = rs.getInt("TaskID");
                String taskTitle = rs.getString("TaskTitle");
                String description = rs.getString("Description");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                int statusID = rs.getInt("StatusID");
                int assignedTo = rs.getInt("AssignedTo");
                String filePath = rs.getString("filePath");

                Task task = new Task(taskID, phaseID, taskTitle, description, startDate, endDate, statusID, assignedTo, filePath);
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Error in getTasksByPhaseId: " + e.getMessage());
        }

        return tasks;
    }

    public boolean createNewTask(Task task) {
        try {
            String sql = "INSERT INTO Tasks (PhaseID, TaskTitle, Description, StartDate, EndDate, StatusID, AssignedTo) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, task.getPhaseID());
            stmt.setString(2, task.getTaskTitle());
            stmt.setString(3, task.getDescription());
            stmt.setDate(4, task.getStartDate());
            stmt.setDate(5, task.getEndDate());
            stmt.setInt(6, task.getStatusID());
            stmt.setInt(7, task.getAssignedTo());

            int row = stmt.executeUpdate();

            if (row > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in createNewTask: " + e.getMessage());
        }
        return false;
    }
    
    
    public boolean updateTask(Task task) {
        try {
            String sql = "UPDATE Tasks SET PhaseID=?, TaskTitle=?, Description=?, StartDate=?, EndDate=?, StatusID=?, AssignedTo=? WHERE TaskID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, task.getPhaseID());
            stmt.setString(2, task.getTaskTitle());
            stmt.setString(3, task.getDescription());
            stmt.setDate(4, task.getStartDate());
            stmt.setDate(5, task.getEndDate());
            stmt.setInt(6, task.getStatusID());
            stmt.setInt(7, task.getAssignedTo());
            stmt.setInt(8, task.getTaskID());
            int row = stmt.executeUpdate();

            if (row > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in createNewTask: " + e.getMessage());
        }
        return false;
    }
    
    public void uploadFile(Path filePath, int taskId, String applicationPath) {
        String updateSQL = "UPDATE Tasks SET FilePath = ? WHERE TaskID = ?";

        try (
             PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            statement.setString(1, filePath.toString().replace(applicationPath, ""));
            statement.setInt(2, taskId);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("uploadFile: " + e.getMessage());
        }
    }
    
    public void autoCancelExpiredTasks() {
        String updateSQL = "UPDATE Tasks SET StatusID = 3 WHERE EndDate < GETDATE()";

        try (PreparedStatement statement = conn.prepareStatement(updateSQL)) {
            int rowsAffected = statement.executeUpdate();
            System.out.println("Auto-canceled " + rowsAffected + " expired tasks.");
        } catch (SQLException e) {
            System.out.println("autoCancelExpiredTasks: " + e.getMessage());
        }
    }
}
