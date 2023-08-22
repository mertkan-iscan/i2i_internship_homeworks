package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {
        // Register JDBC driver
        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

        // Open the JDBC connection
        try (Connection conn = DriverManager.getConnection("jdbc:ignite:thin://localhost/", "mert","1234");
             Statement stmt = conn.createStatement()) {

            // Query the 'SUBSCRIBER' table and print the results
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM SUBSCRIBER")) {
                while (rs.next())
                    System.out.println("Subscriber [ID=" + rs.getInt("SUBSC_ID") +
                            ", Name=" + rs.getString("SUBSC_NAME") +
                            ", Surname=" + rs.getString("SUBSC_SURNAME") +
                            ", MSISDN=" + rs.getString("MSISDN") +
                            ", TariffID=" + rs.getInt("TARIFF_ID") +
                            ", StartDate=" + rs.getDate("START_DATE") + ']');
            }
        }
    }
}
