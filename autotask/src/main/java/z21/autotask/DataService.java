package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import z21.autotask.orm.Animal;
import z21.autotask.repositories.AnimalRepository;
import java.util.List;

@Service
public class DataService {
    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> getAll(){
        return animalRepository.findAll();
    }
    
    public void addAnimal(String name, String color, Integer legCount) {
        animalRepository.insertAnimal(name, color, legCount);
    }
}
