import java.sql.*;

public class Query
{
    public static void main(String[] args) 
    {
        // try (Connection conn = DriverManager.getConnection(
        //         "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "z21", "n7x7cs")) 
        // {
        //     if (conn != null) 
        //     {
        //         System.out.println("Connected to the database!");
        //     } 
        //     else 
        //     {
        //         System.out.println("Failed to make connection!");
        //     }
        //     Statement stmt = conn.createStatement();
        //     String query = "SELECT * FROM animals";
        //     String query1 = "SELECT name FROM animals WHERE leg_count = 4";
        //     //ResultSet rset = stmt.executeQuery(query1);
        //     ResultSet rset = stmt.executeQuery(query);

        //     while(rset.next())
        //     {
        //         int ANIMAL_ID = rset.getInt("ANIMAL_ID");
        //         String NAME = rset.getString("NAME");
        //         String COLOR = rset.getString("COLOR");
        //         int LEG_COUNT = rset.getInt("LEG_COUNT");
        //         System.out.println(ANIMAL_ID + ", " + NAME + ", " + COLOR + ", " + LEG_COUNT);
        //         //System.out.println(NAME);
        //     }
        // } 
        // catch (SQLException e) 
        // {
        //     System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        // } 
        // catch (Exception e) 
        // {
        //     e.printStackTrace();
        // }
    }
}