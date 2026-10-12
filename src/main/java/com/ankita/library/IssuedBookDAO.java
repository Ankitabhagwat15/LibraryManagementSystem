
package com.ankita.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssuedBookDAO {

    public void displayIssuedBooks() {

        String sql =
            "SELECT ib.issue_id, b.title, m.name, " +
            "ib.issue_date, ib.return_date " +
            "FROM issued_books ib " +
            "JOIN books b ON ib.book_id = b.book_id " +
            "JOIN members m ON ib.member_id = m.member_id " +
            "ORDER BY ib.issue_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("========== ISSUED BOOKS REPORT ==========");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("Issue ID: " +
                        rs.getInt("issue_id"));

                System.out.println("Book Name: " +
                        rs.getString("title"));

                System.out.println("Member Name: " +
                        rs.getString("name"));

                System.out.println("Issue Date: " +
                        rs.getDate("issue_date"));

                System.out.println("Return Date: " +
                        rs.getDate("return_date"));

                System.out.println("-----------------------------------------");
            }

            if (!found) {
                System.out.println("No issued book records found.");
            }

        } catch (SQLException e) {
            System.out.println("Unable to display issued books.");
            e.printStackTrace();
        }
    }
}

