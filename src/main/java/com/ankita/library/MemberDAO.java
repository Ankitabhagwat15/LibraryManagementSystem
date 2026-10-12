
package com.ankita.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

    // Add Member
    public void addMember(String name, String email, String phone) {

        String sql = "INSERT INTO members (name, email, phone) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Member added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Unable to add member.");
            e.printStackTrace();
        }
    }

    // Display Members
    public void displayMembers() {

        String sql = "SELECT * FROM members";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("===== LIBRARY MEMBERS =====");

            while (rs.next()) {

                System.out.println("Member ID: "
                        + rs.getInt("member_id"));

                System.out.println("Name: "
                        + rs.getString("name"));

                System.out.println("Email: "
                        + rs.getString("email"));

                System.out.println("Phone: "
                        + rs.getString("phone"));

                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

