package org.hillel.dao;

import org.hillel.Journey;

import java.sql.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataConnect {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/schedule_transfer";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String sql = "SELECT * FROM schedule_service";
    private Map<String, List<Journey>> storage = new HashMap<>();

    public static void main(String[] argv) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connectedOK(connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String str = resultSet.getString(2) + "  " + resultSet.getString(3)
                        + "  " + resultSet.getString(4)
                        + "  " + resultSet.getString(5);
                System.out.println(str);
            }
            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }


    }

    private static void connectedOK(Connection connection) {
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
}
