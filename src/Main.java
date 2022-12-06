import java.sql.SQLException;
import java.util.List;

public class Main {
    static private AnimalDAO dao = null;
    static private DBContext dbcon = null;
    public static void main(String[] argv) throws SQLException{
        dbcon = new DBContext();
        dao = new AnimalDAO();
        dao.setConn(dbcon.getConnection());
        
        Animal animal = new Animal();
        animal.setName("Kitku");
        animal.setLegCount(4);
        animal.setColor("rainbow");
        //dao.insertAnimal(animal);
        
        List<Animal> animals = dao.getAllAnimals();
        for (Animal an : animals){
            System.out.println(an.toString() + "\n");
        }

    }
}
