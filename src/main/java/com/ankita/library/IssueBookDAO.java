
package com.ankita.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class IssueBookDAO {

    public void issueBook(int bookId, int memberId) {

        String checkBook =
                "SELECT available_copies FROM books WHERE book_id = ?";

        String insertIssue =
                "INSERT INTO issued_books " +
                "(book_id, member_id, issue_date) VALUES (?, ?, ?)";

        String updateBook =
                "UPDATE books SET available_copies = available_copies - 1 " +
                "WHERE book_id = ? AND available_copies > 0";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                // Step 1: Check book availability
                try (PreparedStatement ps =
                             con.prepareStatement(checkBook)) {

                    ps.setInt(1, bookId);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next()) {
                            System.out.println("Book ID not found!");
                            con.rollback();
                            return;
                        }

                        if (rs.getInt("available_copies") <= 0) {
                            System.out.println("Book is not available!");
                            con.rollback();
                            return;
                        }
                    }
                }

                // Step 2: Check member exists
                String checkMember =
                        "SELECT member_id FROM members WHERE member_id = ?";

                try (PreparedStatement ps =
                             con.prepareStatement(checkMember)) {

                    ps.setInt(1, memberId);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next()) {
                            System.out.println("Member ID not found!");
                            con.rollback();
                            return;
                        }
                    }
                }

                // Step 3: Insert issue record
                try (PreparedStatement ps =
                             con.prepareStatement(insertIssue)) {

                    ps.setInt(1, bookId);
                    ps.setInt(2, memberId);
                    ps.setDate(3, new Date(System.currentTimeMillis()));

                    ps.executeUpdate();
                }

                // Step 4: Reduce available copies
                try (PreparedStatement ps =
                             con.prepareStatement(updateBook)) {

                    ps.setInt(1, bookId);

                    int rows = ps.executeUpdate();

                    if (rows == 0) {
                        throw new SQLException("Book availability changed.");
                    }
                }

                con.commit();
                System.out.println("Book issued successfully!");

            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println("Unable to issue book.");
            e.printStackTrace();
        }
    }
}

