package com.marcobehler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcQueries {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost/test?serverTimezone=UTC",
                        "myUsername", "myPassword")) {

            PreparedStatement selectStatement = conn.prepareStatement("select * from users");
            ResultSet rs = selectStatement.executeQuery();

            List<User> users = new ArrayList<>();

            while (rs.next()) { // will traverse through all rows
                Integer id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                User user = new User(id, firstName, lastName);
                users.add(user);
            }

        }
    }
}
