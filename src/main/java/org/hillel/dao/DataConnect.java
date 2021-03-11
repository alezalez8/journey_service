package org.hillel.dao;

import org.hillel.Journey;

import java.sql.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataConnect {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/schedule_transfer";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String sql = "SELECT * FROM schedule_service";
    static final String sqlCity = "SELECT * FROM schedule_service where ";



    public static void main(String[] args) {
        getBD();
    }

    public static Map<String, List<Journey>> getBD() {
        //driverOK();
        //System.out.println("PostgreSQL JDBC Driver successfully connected");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       /* SimpleDateFormat format1 = new SimpleDateFormat();
        format1.applyPattern("yyyy-MM-dd");*/


        Map<String, List<Journey>> storage = new HashMap<>();
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connectedOK(connection);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String from = resultSet.getString("station_from");
                String to = resultSet.getString("station_to");
                LocalDate departure = LocalDate.parse(resultSet.getString("departure"));
                LocalDate arrival = LocalDate.parse(resultSet.getString("arrival"));
                System.out.println(from + "->" + to + "   dep: " + departure + "  arr: " + arrival);



            }
            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            //return;
        }
        return storage;
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
