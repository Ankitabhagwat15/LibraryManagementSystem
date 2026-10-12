
package com.ankita.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ReturnBookDAO {

    public void returnBook(int issueId) {

        String checkIssue =
                "SELECT book_id FROM issued_books " +
                "WHERE issue_id = ? AND return_date IS NULL";

        String updateIssue =
                "UPDATE issued_books SET return_date = ? " +
                "WHERE issue_id = ? AND return_date IS NULL";

        String updateBook =
                "UPDATE books SET available_copies = available_copies + 1 " +
                "WHERE book_id = ? AND available_copies < total_copies";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                int bookId;

                // Step 1: Check issue record
                try (PreparedStatement ps =
                             con.prepareStatement(checkIssue)) {

                    ps.setInt(1, issueId);

                    try (ResultSet rs = ps.executeQuery()) {

                        if (!rs.next()) {
                            System.out.println(
                                    "Issue record not found or book already returned!");
                            con.rollback();
                            return;
                        }

                        bookId = rs.getInt("book_id");
                    }
                }

                // Step 2: Update return date
                try (PreparedStatement ps =
                             con.prepareStatement(updateIssue)) {

                    ps.setDate(1, new Date(System.currentTimeMillis()));
                    ps.setInt(2, issueId);

                    int rows = ps.executeUpdate();

                    if (rows == 0) {
                        throw new SQLException("Unable to update return date.");
                    }
                }

                // Step 3: Increase available copies
                try (PreparedStatement ps =
                             con.prepareStatement(updateBook)) {

                    ps.setInt(1, bookId);

                    int rows = ps.executeUpdate();

                    if (rows == 0) {
                        throw new SQLException(
                                "Available copies could not be updated.");
                    }
                }

                // Step 4: Save changes
                con.commit();

                System.out.println("Book returned successfully!");

            } catch (SQLException e) {
                con.rollback();
                throw e;

            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println("Unable to return book.");
            e.printStackTrace();
        }
    }
}

