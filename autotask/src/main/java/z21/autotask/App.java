package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.exit;

@SpringBootApplication
public class App implements CommandLineRunner {

 //   private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    //@Autowired
   // DataSource dataSource;

    @Autowired
    AnimalRepository animalRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

   // @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {
       // System.out.println("DATASOURCE = " + dataSource);
        List<Animal> listAnimals = animalRepository.findAll();
        listAnimals.forEach(System.out :: println);

        /* 
        System.out.println("\n1.findAll()..."); 
        for (Animal animal : animalRepository.findAll()) {
            System.out.println(animal); 
        } 
        
        System.out.println("\n2.findByName(String name)...");
        for (Animal animal : animalRepository.findByName("Snek")) {
            System.out.println(animal);
        } */

        exit(0);
    }

}