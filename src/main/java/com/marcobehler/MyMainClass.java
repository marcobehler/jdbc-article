package com.marcobehler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyMainClass {

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=UTC", "myUsername", "myPassword")) {
            boolean isValid = conn.isValid(0); // "0" means disabling the timeout, when doing isValid checks
            System.out.println("Do we have a valid db connection? = " + isValid);

            // Do something with the Connection, run some SQL statements
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
