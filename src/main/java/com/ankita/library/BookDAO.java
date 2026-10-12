package com.ankita.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {

    // Display all books
    public void displayBooks() {

        String sql = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("===== LIBRARY BOOKS =====");

            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Total Copies: " + rs.getInt("total_copies"));
                System.out.println("Available Copies: " + rs.getInt("available_copies"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new book
    public void addBook(String title, String author,
                        int totalCopies, int availableCopies) {

        String sql = "INSERT INTO books " +
                "(title, author, total_copies, available_copies) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, totalCopies);
            ps.setInt(4, availableCopies);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search book by title
    public void searchBook(String title) {

        String sql = "SELECT * FROM books WHERE title LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + title + "%");

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println("===== BOOK FOUND =====");
                    System.out.println("Book ID: " + rs.getInt("book_id"));
                    System.out.println("Title: " + rs.getString("title"));
                    System.out.println("Author: " + rs.getString("author"));
                    System.out.println("Total Copies: " + rs.getInt("total_copies"));
                    System.out.println("Available Copies: " + rs.getInt("available_copies"));
                    System.out.println("----------------------");
                }

                if (!found) {
                    System.out.println("Book not found!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Unable to search book.");
            e.printStackTrace();
        }
    }
}

