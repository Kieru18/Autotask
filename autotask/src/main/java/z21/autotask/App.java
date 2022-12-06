package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static java.lang.System.exit;

@SpringBootApplication
@EnableJpaRepositories
public class App implements CommandLineRunner {

    @Autowired
    AnimalRepository animalRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Animal> listAnimals = animalRepository.findAll();
        listAnimals.forEach(System.out :: println);
       
        for (Animal animal : animalRepository.findByName("Snek")) {
            System.out.println(animal);
        } 
        exit(0);
    }

}