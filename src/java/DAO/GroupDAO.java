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
import java.util.List;
import model.Group;

/**
 *
 * @author Legion
 */
public class GroupDAO extends DBContext {

    Connection conn = null;

    public GroupDAO() {
        try {
            conn = getConnection();
        } catch (Exception e) {
        }
    }

    public List<Group> getGroupsCreatedByAdvisor(int advisorID) {
        String query = "SELECT GroupID, GroupName, createdBy FROM Groups WHERE createdBy = ?";
        List<Group> list = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, advisorID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int groupID = rs.getInt("GroupID");
                String groupName = rs.getString("GroupName");
                int createdBy = rs.getInt("createdBy");
                Group g = new Group(groupID, groupName, createdBy);
                list.add(g);

            }
        } catch (SQLException e) {
            System.out.println("getGroupsCreatedByAdvisor: " + e.getMessage());
        }
        return list;
    }

    public List<Group> getGroups(int pageNumber, int pageSize, String searchName, int advisorID) {
        List<Group> groups = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;

        String sql = "SELECT * FROM ( "
                + "    SELECT ROW_NUMBER() OVER (ORDER BY GroupID DESC) AS RowNum, * "
                + "    FROM Groups "
                + "    WHERE GroupName LIKE ? AND createdBy = ? "
                + ") AS RowConstrainedResult "
                + "WHERE RowNum >= ? AND RowNum < ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchName + "%");
            stmt.setInt(2, advisorID);
            stmt.setInt(3, offset + 1); // Start row number
            stmt.setInt(4, offset + 1 + pageSize); // End row number (exclusive)

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int groupID = rs.getInt("GroupID");
                String groupName = rs.getString("GroupName");
                int createdBy = rs.getInt("createdBy");

                Group group = new Group(groupID, groupName, createdBy);
                groups.add(group);
            }
        } catch (SQLException e) {
            System.out.println("getGroups: " + e.getMessage());
        }

        return groups;
    }

    // Method to count total groups for pagination and filter by advisorID
    public int countGroups(String searchName, int advisorID) {
        String sql = "SELECT COUNT(*) AS Total FROM Groups WHERE GroupName LIKE ? AND createdBy = ?";
        int total = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchName + "%");
            stmt.setInt(2, advisorID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException e) {
            System.out.println("countGroups: " + e.getMessage());
        }

        return total;
    }
    
    public void addGroup(Group group) {
        String query = "INSERT INTO Groups (groupName, createdBy) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, group.getGroupName());
            statement.setInt(2, group.getCreatedBy());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addGroup: " + e.getMessage());
        }
    }

    // Method to update an existing group in the database
    public void updateGroup(Group group) {
        String query = "UPDATE Groups SET groupName = ?, createdBy = ? WHERE groupID = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, group.getGroupName());
            statement.setInt(2, group.getCreatedBy());
            statement.setInt(3, group.getGroupID());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateGroup: " + e.getMessage());
        }
    }
}
