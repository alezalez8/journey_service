package org.hillel.dao;

import org.hillel.Journey;

import java.sql.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataConnect {
    //static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/schedule_transfer";
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/schedule_service";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";
    private static final String sql = "SELECT * FROM schedule_service";
    private List<Journey> journeys = new ArrayList<>();

    public DataConnect() {
        System.out.println("basa is ready");
    }

    public List<Journey> getBD() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String stationFrom = resultSet.getString("station_from");
                String stationTo = resultSet.getString("station_to");
                LocalDate departure = LocalDate.parse(resultSet.getString("departure"));
                LocalDate arrival = LocalDate.parse(resultSet.getString("arrival"));
                journeys.add(new Journey(stationFrom, stationTo, departure, arrival));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        return journeys;
    }

    private static void driverOK() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
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
