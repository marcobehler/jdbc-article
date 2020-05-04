package com.marcobehler;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MyHikariCpClass {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = createDataSource();

        try (Connection conn = dataSource.getConnection()) {

            // "0" means disabling the timeout, when doing isValid checks
            boolean isValid = conn.isValid(0);
            System.out.println("Do we have a valid db connection? = " + isValid);

            // Do something with the Connection, run some SQL statements
        }
    }

    private static DataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost/test?serverTimezone=UTC");
        ds.setUsername("myUsername");
        ds.setPassword("myPassword");
        return ds;
    }
}
