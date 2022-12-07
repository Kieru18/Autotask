package z21.autotask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DataService {
    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> getAll(){
        return animalRepository.findAll();
    }

}
