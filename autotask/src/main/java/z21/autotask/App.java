package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String animals(@RequestParam(value = "name", defaultValue = "null") String name) {
        List<Animal> listAnimals = null;
        
        listAnimals = animalRepository.findByName(name);
        
        String result = "";
        for(Animal animal: listAnimals)
        {
            result += animal.toString() + "<br/>";
        }
        return "List of animals:  <br/> " + result;
    }

    @GetMapping("/legcolor")
    public String legcolor() {
        List<Animal> listAnimals = null;
        
        listAnimals = animalRepository.findLegsColor(4, "black");
        
        String result = "";
        for(Animal animal: listAnimals)
        {
            result += animal.toString() + "<br/>";
        }
        return "List of animals:  <br/> " + result;
    }
}