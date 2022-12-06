package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
        List<Animal> listAnimals = animalRepository.findByName("Snek");
        String result = "";
        for(Animal animal: listAnimals)
        {
            result += animal.toString() + "<br/>";
        }
        return "List of all animals:  <br/> " + result;
    }
}