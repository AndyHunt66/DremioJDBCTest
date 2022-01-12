package uk.me.andrewhunt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main2 {

    public static void main(String[] args)
    {
        final String DB_URL = "jdbc:dremio:direct=localhost:31010;";

        final String USER = "dremio";
        final String PASS = "dremio123";
        final String QUERY = "SELECT * FROM \"noaa-gsod-pds\".\"noaa-gsod-pds\".\"1929\"";

        Properties props = new Properties();
        props.setProperty("user",USER);
        props.setProperty("password",PASS);

        Connection conn = null;
        Statement stmt = null;

        try {

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, props);

            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                System.out.println(rs.getString("STATION"));

            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
