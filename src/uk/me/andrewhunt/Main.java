package uk.me.andrewhunt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args)
    {
        final String DB_URL = "jdbc:dremio:direct=localhost:31010;";

        final String USER = "dremio";
        final String PASS = "dremio123";
        final String QUERY = "SELECT * FROM \"noaa-gsod-pds\".\"noaa-gsod-pds\".\"1929\"";

        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("STATION: " + rs.getString("STATION"));
                System.out.println(", DEWP: " + rs.getString("DEWP"));
//                System.out.print(", First: " + rs.getString("first"));
//                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
