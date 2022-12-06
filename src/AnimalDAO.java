import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    Connection conn = null;

    // public boolean insertAnimal(Animal animal) {
    //     boolean success = false;
    //     if (animal != null) {
    //         if (conn != null) {
    //             PreparedStatement pstmt = null;
    //             try {
    //                 pstmt = conn.prepareStatement("INSERT INTO animals (name, color, leg_count) VALUES (?, ?, ?)");
    //                 pstmt.setString(0, null);
    //             }
    //         }
    //     }
    // }

    public void setConn(Connection connection) {
        conn = connection;
    }

    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<Animal>();
        if (conn != null) {
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM animals");
                while (rs.next()) {
                    Animal animal = new Animal();
                    animal.setAnimalId(rs.getInt("animal_id"));
                    animal.setName(rs.getString("name"));
                    animal.setColor(rs.getString("color"));
                    animal.setLegCount(rs.getInt("leg_count"));
                    animals.add(animal);
                }
            } catch (SQLException e) {
                //log.error("Unable to create the database table", e);
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } finally {
                if (stmt != null) {
                try { stmt.close();
                } catch (SQLException e) {
                    }
                }
            }
         }
         return animals;
    }
}