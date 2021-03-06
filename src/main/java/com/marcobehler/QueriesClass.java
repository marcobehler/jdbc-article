package com.marcobehler;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueriesClass {

    public static void main(String[] args) {
        DataSource dataSource = createDataSource();

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            // jdbc select from
            PreparedStatement selectStatement = connection.prepareStatement("select * from users where first_name =  ?");
            selectStatement.setString(1, "John");

            // this will return a ResultSet of all users with said name
            ResultSet rs = selectStatement.executeQuery();

            // will traverse through all found rows
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                System.out.println("firstName = " + firstName + "," + "lastName= " + lastName );
            }

            // jdbc insert into
            PreparedStatement statement = connection.prepareStatement("insert into users (first_name, last_name) values (?,?)");
            statement.setString(1, "John");
            statement.setString(2, "Rambo");
            int insertedRows = statement.executeUpdate();
            System.out.println("I just inserted " + insertedRows + " users");

            // jdbc update
            PreparedStatement updateStatement = connection.prepareStatement("update users set first_name = 'unknown' where id = ?");
            updateStatement.setInt(1, 1);
            int updatedRows = updateStatement.executeUpdate();
            System.out.println("I just updated " + updatedRows + " users");

            // jdbc delete
            PreparedStatement deleteStatement = connection.prepareStatement("delete from users where id > ?");
            deleteStatement.setInt(1, 1);
            int deletedRows = deleteStatement.executeUpdate();
            System.out.println("I just deleted " + deletedRows + " users");

            connection.commit(); // commits the database transaction

        } catch (SQLException e) {
            e.printStackTrace();
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
