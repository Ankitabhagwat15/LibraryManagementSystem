
package com.ankita.library;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    public static void main(String[] args) {

        try (Connection con = DBConnection.getConnection()) {
            System.out.println("MySQL Connected Successfully!");
            System.out.println("Database: " + con.getCatalog());

        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}
