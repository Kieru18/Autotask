package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.System.exit;

// @SpringBootApplication
// @EnableJpaRepositories
// public class App implements CommandLineRunner {

//     @Autowired
//     AnimalRepository animalRepository;

//     public static void main(String[] args) throws Exception {
//         SpringApplication.run(App.class, args);
//     }

//     @Override
//     public void run(String... args) throws Exception {
//         List<Animal> listAnimals = animalRepository.findAll();
//         listAnimals.forEach(System.out :: println);
       
//         for (Animal animal : animalRepository.findByName("Snek")) {
//             System.out.println(animal);
//         } 
//         exit(0);
//     }

// }

@SpringBootApplication
@RestController
public class App {

    @Autowired
    AnimalRepository animalRepository;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/animals")
    public String animals() {
        List<Animal> listAnimals = animalRepository.findAll();
        String result = "";
        for(Animal animal: listAnimals)
        {
            result += animal.toString() + "<br/>";
        }
        return "List of all animals:  <br/> " + result;
    }
}