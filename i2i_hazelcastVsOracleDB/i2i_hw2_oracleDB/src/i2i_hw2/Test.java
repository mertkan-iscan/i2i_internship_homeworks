package i2i_hw2;

import java.sql.*;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
    	
        String url = "jdbc:oracle:thin:@localhost:1521:TEST";
        String user = "sys as sysdba";
        String password = "sys";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
        	
            // Prepare SQL statement for inserting random numbers
        	PreparedStatement stmtInsert = conn.prepareStatement("INSERT INTO random_numbers (ID, random_number) VALUES (?, ?)");

            // Use Java's Random utility to generate random numbers
            Random random = new Random();
            
            //int numberOfRandomNumbers = 20000;
            int numberOfRandomNumbers = 100000;

            // Start the timer
            long startTimeInsert = System.currentTimeMillis();
            
            // Loop to insert random numbers
            for (int i = 1; i <= numberOfRandomNumbers; i++) {
            	
            	stmtInsert.setInt(1, i); // Assuming ID = 1
            	stmtInsert.setInt(2, random.nextInt());
            	stmtInsert.addBatch();
            	stmtInsert.executeBatch();
                
            }

            // Stop the timer and print the time taken
            long endTimeInsert = System.currentTimeMillis();
            
            System.out.println("Time taken to insert " + numberOfRandomNumbers + " random numbers: " + (endTimeInsert - startTimeInsert) + "ms");
            
            
            
            
            
            PreparedStatement stmtSelect = conn.prepareStatement("SELECT * FROM random_numbers WHERE id = (?)");

            // Start the timer
            long startTimeSelect = System.currentTimeMillis();

            for(int i = 0 ; i< numberOfRandomNumbers; i++){

            	stmtSelect.setInt(1, i);
                ResultSet rs = stmtSelect.executeQuery();

                rs.close();
           }
           

            long endTimeSelect = System.currentTimeMillis();

            System.out.println("Time taken to insert " + numberOfRandomNumbers + " random numbers: " + (endTimeSelect - startTimeSelect) + "ms");
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
        }
    }
}
