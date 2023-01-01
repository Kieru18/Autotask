package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import z21.autotask.entities.Animal;
import z21.autotask.repositories.AnimalRepository;

import java.util.Date;
import java.util.List;

@Service
public class DataService {
    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> getAll() {
        return animalRepository.findAll();
    }
    
    public void addAnimal(String name,  Integer locationId, Integer speciesId, Float weight, Date birthDate) {
        animalRepository.insertAnimal(name, locationId, speciesId, weight, birthDate);
    }
}
